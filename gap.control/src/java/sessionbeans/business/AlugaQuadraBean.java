package sessionbeans.business;

import entidades.Expediente;
import entidades.Mensalista;
import entidades.Quadra;
import entidades.Reserva;
import enums.DiaSemana;
import exceptions.ExpedienteNaoEncontradoException;
import exceptions.GAPBusinessException;
import exceptions.ReservaNaoValidaException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.joda.time.DateTime;
import retorno.HorarioReserva;
import sessionbeans.repository.ExpedienteRepositoryRemote;
import sessionbeans.repository.QuadraRepositoryRemote;

/**
 *
 * @author avsilva
 */
@Stateless
@Remote(AlugaQuadraRemote.class)
public class AlugaQuadraBean implements AlugaQuadraRemote {

    @PersistenceContext
    EntityManager manager;
    @EJB
    private QuadraRepositoryRemote qrr;
    @EJB
    ExpedienteRepositoryRemote expedienteRepositoryRemote;
    @EJB
    MensalistaRemote mensalistaRemote;

    @Override
    public List<HorarioReserva> getHorariosLivres(Date data) throws GAPBusinessException {

        List<Quadra> quadras = getQuadras();
        if (quadras.isEmpty()) {
            throw new GAPBusinessException("Não foram encontradas quadras no sistema.\n"
                    + "Favor cadastrar ou habilitar pelo menos uma quadra.");
        }

        DateTime dt = new DateTime(data);
        DiaSemana diaSemana;
        try {
            diaSemana = DiaSemana.valueOf(dt.getDayOfWeek());
        } catch (ParseException ex) {
            throw new GAPBusinessException(ex.getMessage(), ex);
        }

        List<DateTime> horariosValidos = expedienteRepositoryRemote.getHorariosValidos(data);

        List<Reserva> reservas = getReservas(data);

        List<Mensalista> mensalistas = getMensalista(diaSemana);

        List<HorarioReserva> horarioReservas = new ArrayList<HorarioReserva>();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        //monta lista com horários para reserva       
        for (DateTime h : horariosValidos) {
            for (Quadra q : quadras) {
                HorarioReserva horarioReserva = new HorarioReserva(data, h.toDate(), q);
                horarioReservas.add(horarioReserva);
            }
        }

        List<HorarioReserva> horasOcupadas = new ArrayList<HorarioReserva>();
        //limpa as mensalidades
        for (Mensalista mensalista : mensalistas) {
            for (HorarioReserva h : horarioReservas) {
                String hora = sdf.format(mensalista.getMensalistaPK().getHorario());
                if (hora.equals(h.getHoraExtenso()) && h.getQuadra().getId() == mensalista.getQuadra().getId()) {
                    horasOcupadas.add(h);
                    break;
                }
            }
        }

        //limpa as reservas
        for (Reserva r : reservas) {
            for (HorarioReserva h : horarioReservas) {
                String hora = sdf.format(r.getReservaPK().getHoraReserva());
                if (hora.equals(h.getHoraExtenso()) && h.getQuadra().getId() == r.getReservaPK().getQuadraId()) {
                    horasOcupadas.add(h);
                    break;
                }
            }
        }

        //remove
        for (HorarioReserva h : horasOcupadas) {
            horarioReservas.remove(h);
        }

        if (horarioReservas.isEmpty()) {
            throw new GAPBusinessException("Não Existe nenhum horário Válido para a data informada");
        }
        
        
        return horarioReservas;
    }

    private List<Reserva> getReservas(Date data) {
        TypedQuery<Reserva> query = this.manager.createNamedQuery("Reserva.findOcupadasByData", Reserva.class);
        query.setParameter("dataReserva", data);

        List<Reserva> reservas = query.getResultList();

        return reservas;
    }

    private List<Mensalista> getMensalista(DiaSemana diaSemana) {
        try {
            return mensalistaRemote.getMensalidade(diaSemana);
        } catch (GAPBusinessException ex) {
            return new ArrayList<Mensalista>();
        }
    }

    private List<Reserva> getHorariosData(Date data) {

        //recupera data inicio expediente e fim expediente
        Expediente expediente = null;

        //recupera reservas para o dia
        List<Reserva> reservas = null;

        List<Reserva> horarios = geraHorario(expediente);

        for (Reserva reserva : horarios) {
            Date hora = reserva.getReservaPK().getHoraReserva();
            for (Reserva r : reservas) {
                Date h = r.getReservaPK().getHoraReserva();
                if (hora.equals(h)) {
                    reserva = r;
                }
            }
        }

        return horarios;
    }

    private List<Quadra> getQuadras() {
        return qrr.findAll();
    }

    @Override
    public Reserva reservarHorario(Reserva reserva) throws GAPBusinessException {

        //<editor-fold defaultstate="collapsed" desc="<<: Verificar se o horário pode ser usado :>>">
        //@todo: fazer find para recuperar vigencia da data passada!
        TypedQuery<Expediente> queryExpediente = manager.createNamedQuery("Expediente.findVigenciaAtual", Expediente.class);
        queryExpediente.setParameter("dia_semana", (short) (new DateTime(reserva.getReservaPK().getHoraReserva()).getDayOfWeek()));

        Expediente expediente = null;
        try {
            expediente = queryExpediente.getSingleResult();
        } catch (NoResultException e) {
            throw new ExpedienteNaoEncontradoException(e);
        }

        List<DateTime> horariosValidos = getHorariosValidos(expediente);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        boolean isHorarioValido = false;
        for (DateTime dateTime : horariosValidos) {
            if (dateTime.toString("HH:mm").equals(sdf.format(reserva.getReservaPK().getHoraReserva().getTime()))) {
                isHorarioValido = true;
                break;
            }
        }

        if (!isHorarioValido) {
            throw new ReservaNaoValidaException("Horário não corresponde a nenhum horário válido do sistema.");
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="<<: Verifica se o horário já não está sendo usado :>>">
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String sql = "SELECT r FROM Reserva r WHERE r.reservaPK.hora = '"
                + sdf.format(reserva.getReservaPK().getHoraReserva()) + "'";
        TypedQuery<Reserva> qReserva = manager.createQuery(sql, Reserva.class);

        Reserva ultimaReserva = null;
        try {
            ultimaReserva = qReserva.getSingleResult();
        } catch (NoResultException e) { // não achar resultado deve ser esperado como fluxo principal
        }

        if (ultimaReserva != null) {
            throw new ReservaNaoValidaException("Já existe reserva para esse Horário!");
        }
        //</editor-fold>

        manager.persist(reserva);
        
        return reserva;
    }

    private List<Reserva> geraHorario(Expediente expediente) {
        //@todo refazer metodo com joda time
        long horaInicio = expediente.getInicioExpediente().getTime();
        long horaFim = expediente.getFimExpediente().getTime();

        long horasDiferenca = horaFim - horaInicio;
        List<Reserva> horarios = new ArrayList<Reserva>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(expediente.getInicioExpediente());

        for (int i = 0; i < horasDiferenca / (expediente.getPeriodoBaseMinutos() * 60.0); i++) {
            cal.add(Calendar.MINUTE, expediente.getPeriodoBaseMinutos());

            Reserva r = new Reserva();
//            r.setReservaPK(new ReservaPK(0, 0, cal.getTime()));

            horarios.add(r);
        }

        return horarios;
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
}
