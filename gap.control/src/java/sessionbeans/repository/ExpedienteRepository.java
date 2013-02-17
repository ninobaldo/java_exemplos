/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans.repository;

import entidades.Expediente;
import enums.DiaSemana;
import exceptions.ExpedienteInvalidoException;
import exceptions.ExpedienteNaoEncontradoException;
import exceptions.GAPBusinessException;
import java.text.ParseException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.joda.time.DateTime;

/**
 *
 * @author avsilva
 */
@Stateless
@Remote(ExpedienteRepositoryRemote.class)
public class ExpedienteRepository implements ExpedienteRepositoryRemote {

    @PersistenceContext
    EntityManager manager;

    @Override
    public Expediente adicionar(Expediente expediente) throws GAPBusinessException {
        validarExpediente(expediente, false);

        manager.persist(expediente);
        return expediente;
    }

    @Override
    public void remover(Expediente expediente) throws GAPBusinessException {
        validarExpediente(expediente, true);

        /*@todo: fazer rotina para verificar se nessa vigência não foi gerado 
         * nenhuma reserva ou mensalista antes de remover
         */

        manager.remove(manager.merge(expediente));
    }

    @Override
    public Expediente alterar(Expediente expediente) throws GAPBusinessException {
        validarExpediente(expediente, true);
        manager.merge(expediente);
        return expediente;
    }

    @Override
    public List<Expediente> findAll() throws GAPBusinessException {
        TypedQuery<Expediente> query = this.manager.createNamedQuery(
                "Expediente.findAll", Expediente.class);

        List<Expediente> expedientes = query.getResultList();

        return expedientes;
    }

    @Override
    public Expediente findById(Long id) throws GAPBusinessException {

        Expediente e = this.manager.find(Expediente.class, id);

        if (e == null || e.getId() == null || e.getId() == 0l) {
            throw new ExpedienteNaoEncontradoException();
        }
        return e;
    }

    private void validarExpediente(Expediente expediente, boolean validaId) throws ExpedienteInvalidoException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);

        if (expediente == null) {
            throw new ExpedienteInvalidoException("Expediente está nulo!");
        } else if (validaId && (expediente.getId() == null || expediente.getId() == 0l)) {
            throw new ExpedienteInvalidoException("O expediente não possuie id!");
        } else if (expediente.getDiaSemana() == null || !DiaSemana.contains(expediente.getDiaSemana())) {
            throw new ExpedienteInvalidoException("O dia da Semana inválido!");
        } else if (expediente.getPeriodoBaseMinutos() == null || expediente.getPeriodoBaseMinutos() < (short) 1 || expediente.getPeriodoBaseMinutos() > (short) 1440) {
            throw new ExpedienteInvalidoException("Valor base deve está entre 1 e 1440 minutos!");
        } else if (expediente.getInicioExpediente().after(expediente.getFimExpediente())) {
            throw new ExpedienteInvalidoException("O inicio de expediente tem que ser antes do fim do mesmo!");
        } else if (expediente.getVigencia().before(calendar.getTime())) {
            throw new ExpedienteInvalidoException("A data de vigencia não pode ser menor que a data de hoje!");
        }

    }
    
    @Override
    public Expediente findExpedienteValido(DiaSemana diaSemana) throws GAPBusinessException{
        TypedQuery<Expediente> queryExpediente = manager.createNamedQuery("Expediente.findVigenciasValidas", Expediente.class);
        queryExpediente.setParameter("dia_semana", diaSemana.getValor());

        Expediente expediente = null;
        try {
            expediente = queryExpediente.getSingleResult();
        } catch (NoResultException e) {
            throw new ExpedienteNaoEncontradoException(e);
        }
        
        return expediente;
    }

    @Override
    public Expediente findExpedienteValido(Date data) throws GAPBusinessException {
        DateTime dt = new DateTime(data);
        try {
            return findExpedienteValido(DiaSemana.valueOf(dt.getDayOfWeek()));
        } catch (ParseException ex) {
            throw new GAPBusinessException(ex.getMessage(), ex);
        }
    }
    
    /**
     * Recupera horários válidos para o expediente
     *
     * @param expediente que terá os horários validos gerados
     * @return lista de horários validos
     */
    @Override
    public List<DateTime> getHorariosValidos(Expediente expediente) throws GAPBusinessException {

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
    
    @Override
    public List<DateTime> getHorariosValidos(Date data)throws GAPBusinessException
    {
        Expediente expediente = findExpedienteValido(data);
        return getHorariosValidos(expediente);
    }

    @Override
    public List<DateTime> getHorariosValidos(DiaSemana diaSemana) throws GAPBusinessException {
        Expediente expediente = findExpedienteValido(diaSemana);
        return getHorariosValidos(expediente);
    }
}
