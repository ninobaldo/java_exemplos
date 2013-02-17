/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans.repository;

import entidades.Expediente;
import enums.DiaSemana;
import exceptions.GAPBusinessException;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTime;

/**
 *
 * @author avsilva
 */
public interface ExpedienteRepositoryRemote {

    Expediente adicionar(Expediente expediente)throws GAPBusinessException ;

    Expediente alterar(Expediente expediente)throws GAPBusinessException ;
    
    void remover(Expediente expediente)throws GAPBusinessException ;

    List<Expediente> findAll()throws GAPBusinessException ;

    Expediente findById(Long id)throws GAPBusinessException ;

    public Expediente findExpedienteValido(DiaSemana diaSemana) throws GAPBusinessException;

    public Expediente findExpedienteValido(Date data) throws GAPBusinessException;

    public List<org.joda.time.DateTime> getHorariosValidos(entidades.Expediente expediente)throws GAPBusinessException;

    public java.util.List<org.joda.time.DateTime> getHorariosValidos(enums.DiaSemana diaSemana) throws exceptions.GAPBusinessException;

    public List<DateTime> getHorariosValidos(java.util.Date data) throws exceptions.GAPBusinessException;
}
