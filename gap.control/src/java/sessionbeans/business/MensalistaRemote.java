/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans.business;

import entidades.Mensalista;
import entidades.Quadra;
import enums.DiaSemana;
import exceptions.GAPBusinessException;
import java.util.Date;
import java.util.List;
import retorno.HorarioMensalista;

/**
 *
 * @author avsilva
 */
public interface MensalistaRemote {

    Mensalista criarMensalista(Mensalista mensalista) throws GAPBusinessException;

    List<HorarioMensalista> getHorarioMensalista(DiaSemana diaSemana, Quadra quadra) throws GAPBusinessException;

    Mensalista getMensalidade(DiaSemana diaSemana, Quadra quadra, Date hora) throws exceptions.GAPBusinessException;

    Mensalista alteraMensalidade(Mensalista mensalista) throws exceptions.GAPBusinessException;

    java.util.List<entidades.Mensalista> getMensalidade(enums.DiaSemana diaSemana) throws exceptions.GAPBusinessException;
}
