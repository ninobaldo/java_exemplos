/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans.business;

import entidades.Reserva;
import exceptions.GAPBusinessException;
import java.util.Date;
import java.util.List;
import retorno.HorarioReserva;

/**
 *
 * @author avsilva
 */
public interface AlugaQuadraRemote {

    List<HorarioReserva> getHorariosLivres(Date data)throws GAPBusinessException;

    Reserva reservarHorario(Reserva reserva) throws GAPBusinessException;
    
}
