/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans.business;

import entidades.Expediente;
import entidades.Mensalista;
import entidades.MensalistaPK;
import entidades.Quadra;
import enums.DiaSemana;
import exceptions.ExpedienteNaoEncontradoException;
import exceptions.GAPBusinessException;
import exceptions.MensalidadeNaoEncontradoException;
import exceptions.MensalidadeNaoValidaException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.*;
import org.joda.time.DateTime;
import retorno.HorarioMensalista;

/**
 *
 * @author avsilva
 */
@Stateless
@Remote(MensalistaRemote.class)
public class MensalistaBean implements MensalistaRemote {

    @PersistenceContext
    EntityManager manager;

    @Override
    public List<HorarioMensalista> getHorarioMensalista(DiaSemana diaSemana, Quadra quadra) throws GAPBusinessException {

        //@todo validar para ver se a quadra é valida na base
        //<editor-fold defaultstate="collapsed" desc="<<: Horários Validos :>>">
        //@todo: fazer find para recuperar vigencia da data passada! Colocar esse metodo num unico lugar
        TypedQuery<Expediente> queryExpediente = manager.createNamedQuery("Expediente.findVigenciasValidas", Expediente.class);
        queryExpediente.setParameter("dia_semana", diaSemana.getValor());

        Expediente expediente = null;
        try {
            expediente = queryExpediente.getSingleResult();
        } catch (NoResultException e) {
            throw new ExpedienteNaoEncontradoException(e);
        }


        List<DateTime> horariosValidos = getHorariosValidos(expediente);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="<<: Mensalidades cadastradas :>>">
        TypedQuery<Mensalista> qMensalidade =
                manager.createNamedQuery(Mensalista.FIND_BY_DIA_SEMANA_VALIDOS_E_QUADRA, Mensalista.class);

        qMensalidade.setParameter("diaSemana", diaSemana.getValor());
        qMensalidade.setParameter("quadraId", quadra.getId());

        List<Mensalista> mensalistas = null;

        try {
            mensalistas = qMensalidade.getResultList();
        } catch (NoResultException e) {
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="<<: Lista Horários :>>">
        List<HorarioMensalista> horarios = new ArrayList<HorarioMensalista>();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        for (DateTime dt : horariosValidos) {

            HorarioMensalista hm = new HorarioMensalista(dt.toDate(), diaSemana, quadra.getNome(), false, quadra);

            if (mensalistas != null) {
                for (Mensalista m : mensalistas) {
                    String hora = sdf.format(m.getMensalistaPK().getHorario().getTime());
                    if (dt.toString("HH:mm").equals(hora)) {
                        hm.setIsOcupado(true);
                        break;
                    }
                }
            }
            horarios.add(hm);
        }
        //</editor-fold>

        return horarios;
    }
    
    @Override
    public Mensalista getMensalidade(DiaSemana diaSemana, Quadra quadra, Date hora) throws GAPBusinessException {

        TypedQuery<Mensalista> qMensalidade =
                manager.createNamedQuery(Mensalista.FIND_MENSALIDADE, Mensalista.class);

        qMensalidade.setParameter("diaSemana", diaSemana.getValor());
        qMensalidade.setParameter("quadraId", quadra.getId());
        qMensalidade.setParameter("horario", hora);

        Mensalista mensalista = null;

        try {
            mensalista = qMensalidade.getSingleResult();
        } catch (Exception e) {
            throw new MensalidadeNaoEncontradoException(e);
        }

        if(mensalista == null)
            throw new MensalidadeNaoEncontradoException();

        return mensalista;
    }
    
    @Override
    public List<Mensalista> getMensalidade(DiaSemana diaSemana) throws GAPBusinessException 
    {

        TypedQuery<Mensalista> qMensalidade =
                manager.createNamedQuery(Mensalista.FIND_BY_DIA_SEMANA_VALIDOS, Mensalista.class);

        qMensalidade.setParameter("diaSemana", diaSemana.getValor());

        List<Mensalista> mensalistas = null;

        try {
            mensalistas = qMensalidade.getResultList();
        } catch (Exception e) {
            throw new MensalidadeNaoEncontradoException(e);
        }

        if(mensalistas.isEmpty())
            throw new MensalidadeNaoEncontradoException();

        return mensalistas;
    }

    @Override
    public Mensalista criarMensalista(Mensalista mensalista) throws GAPBusinessException {
        Mensalista m = null;
        try {
            m = findMensalista(mensalista.getMensalistaPK(), true);
        } catch (NoResultException e) {
        }

        if (m != null) {
            throw new MensalidadeNaoValidaException("Já existe um mensalista cadastrado nessa data, quadra e dia da semana informados!");
        }
        
        TypedQuery<Expediente> queryExpediente = manager.createNamedQuery("Expediente.findVigenciasValidas", Expediente.class);
        queryExpediente.setParameter("dia_semana", (mensalista.getMensalistaPK().getDiaSemana()));

        Expediente expediente = null;
        try {
            expediente = queryExpediente.getSingleResult();
        } catch (NoResultException e) {
            throw new ExpedienteNaoEncontradoException("Não existe expediente cadastrado para esse dia da semana.", e);
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if(expediente.getVigencia().after(mensalista.getMensalistaPK().getDataInicioVigencia()))
            throw new MensalidadeNaoValidaException("Mensalidade não válida, pois o inicio da mensalidade "
                    + "é maior que a vigencia do expediente.\n"
                    + "vigencia do expediente: " + sdf.format(expediente.getVigencia()) + "\n"
                    + "vigencia inicial do mensalista: "  + sdf.format(mensalista.getMensalistaPK().getDataInicioVigencia()));
        
        //@todo: avisar que existem dias em que a quadra já está reservada para avulsos;

        //seta valores default
        mensalista.setDataCadastro(new Date());
        mensalista.setIsAtivo(true);

        //persiste
         manager.persist(mensalista);
         return mensalista;
    }
    
    @Override
    public Mensalista alteraMensalidade(Mensalista mensalista) throws GAPBusinessException
    {
         validarMensalista(mensalista, true);

         manager.merge(manager.merge(mensalista));
         return mensalista;
    }

    /**
     * Recupera mensalista ativo, sem contar com data de vigencia ou cliente que
     * utiliza
     *
     * @param pk primary key do mensalista
     * @param isAtivo se deve ser retornado apenas ativos ou inativos
     * @return mensalista com o mesmo dia da semana, quadra e horário passados
     */
    private Mensalista findMensalista(MensalistaPK pk, boolean isAtivo) {
        Mensalista m;

        String sql = "SELECT * FROM mensalista m"
                + "  WHERE m.dia_semana = :dia_semana"
                + "      AND is_ativo = :isAtivo"
                + "      AND m.quadra_id = :quadra_id"
                + "      AND m.horario = :horario";
        //  + ":data_vigencia BETWEEN  date(m.data_vigencia) AND DATE_ADD(date(m.data_vigencia), INTERVAL m.periodo_meses MONTH)"

        Query qMensalista = manager.createNativeQuery(sql, Mensalista.class);
        qMensalista.setParameter("isAtivo", isAtivo);
        qMensalista.setParameter("dia_semana", pk.getDiaSemana());
        qMensalista.setParameter("quadra_id", pk.getQuadraId());
        qMensalista.setParameter("horario", pk.getHorario());
        m = (Mensalista) qMensalista.getSingleResult();

        return m;
    }

    //@todo colocar esse método em um unico lugar
    /**
     * Recupera horários válidos para o expediente
     *
     * @param expediente que terá os horários validos gerados
     * @return lista de horários validos
     */
    private List<DateTime> getHorariosValidos(Expediente expediente) {
        final DateTime dtInicioExpediente = new DateTime(expediente.getInicioExpediente());
        final DateTime dtFimExpediente = new DateTime(expediente.getFimExpediente());

        List<DateTime> horarios = new ArrayList<DateTime>();

        DateTime dtTemp = new DateTime(dtInicioExpediente);
        System.out.println("Inicio Expediente: " + expediente.getInicioExpediente());
        System.out.println("Fim Expediente: " + expediente.getFimExpediente());
        System.out.println("Tempo Base: " + expediente.getPeriodoBaseMinutos());
        while (dtTemp.isBefore(dtFimExpediente)) {
            horarios.add(dtTemp);
            System.out.println(dtTemp.toString());
            dtTemp = dtTemp.plusMinutes(expediente.getPeriodoBaseMinutos());
        }

        Collections.sort(horarios);

        return horarios;
    }
    //<editor-fold defaultstate="collapsed" desc="Outros não implementados">
    //    /**
    ////@todo      * Procura dias ocupados por reservas avulsas dentro de um possivel mensalista
    //     * @param pk
    //     * @return
    //     */
    //    private Mensalista findDiasOcupadosAvulso(Mensalista pk) {
    //        Mensalista m;
    //        String sql = "select * from reserva r"
    //                + " where r.hora = :dataHora"
    //                + " and r.quadra_id = quadra_id"
    //                + " AND r.hora BETWEEN date(m.data_vigencia) AND DATE_ADD(date(m.data_vigencia), INTERVAL m.periodo_meses MONTH)";
    //
    //        Query qMensalista = manager.createNativeQuery(sql, Mensalista.class);
    //        qMensalista.setParameter("data", pk.getDataVigencia());
    //        qMensalista.setParameter("dia_semana", pk.getDiaSemana());
    //        qMensalista.setParameter("quadra_id", pk.getQuadraId());
    //        qMensalista.setParameter("horario", pk.getHorario());
    //        m = (Mensalista) qMensalista.getSingleResult();
    //
    //        return m;
    //    }
    //    @Override
    //    public List<Mensalista> getMensalidades(Date date) throws GAPBusinessException {
    //        String sql = "SELECT * FROM mensalista m"
    //                + "     WHERE :data_vigencia BETWEEN "
    //                + "     date(m.data_vigencia) AND DATE_ADD(date(m.data_vigencia), INTERVAL m.periodo_meses MONTH)"
    //                + "     AND m.is_ativo = TRUE";
    //
    //        Query qMensalista = manager.createNativeQuery(sql, Mensalista.class);
    //        qMensalista.setParameter("data_vigencia", date);
    //
    //        List<Mensalista> mensalistas = null;
    //
    //        try {
    //            mensalistas = qMensalista.getResultList();
    //        } catch (NoResultException e) {
    //            throw new GAPBusinessException("Não existem mensalidades cadastradas nessa data.");
    //        }
    //
    //        return mensalistas;
    //    }
    //
    //    @Override
    //    public List<Mensalista> getMensalidades(short diaSemana) throws GAPBusinessException {
    //        String sql = "SELECT * FROM mensalista m"
    //                + "     WHERE :data BETWEEN "
    //                + "     date(m.data_vigencia) AND DATE_ADD(date(m.data_vigencia), INTERVAL m.periodo_meses MONTH)"
    //                + "     AND m.is_ativo = TRUE";
    //
    //        Query q = manager.createNativeQuery(sql, Mensalista.class);
    //
    //        q.setParameter("data", new Date());
    //        q.setParameter("diaSemana", diaSemana);
    //
    //        List<Mensalista> mensalistas = null;
    //
    //        try {
    //            mensalistas = q.getResultList();
    //        } catch (NoResultException e) {
    //            throw new GAPBusinessException("Não existem mensalidades cadastradas nesse dia da semana.");
    //        }
    //
    //        return mensalistas;
    //    }
    //</editor-fold>    

    private void validarMensalista(Mensalista mensalista, boolean validaId) throws MensalidadeNaoValidaException {
    if (mensalista == null) {
            throw new MensalidadeNaoValidaException("Mensalista está nulo");
        } else if (validaId && ((mensalista.getMensalistaPK() == null ))) {
            throw new MensalidadeNaoValidaException("O Mensalista não possuie id");
        }
    }
}