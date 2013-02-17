/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gap.view.factory;

import br.com.gap.view.util.ServiceLocator;
import entidades.Mensalista;
import entidades.Quadra;
import enums.DiaSemana;
import java.util.List;
import retorno.HorarioMensalista;
import sessionbeans.business.MensalistaRemote;

/**
 *
 * @author mjustino
 */
public class MensalistaBeanFactory {

    public static List<HorarioMensalista> getMensalidades(DiaSemana diaSemana, Quadra quadra) throws Exception {
        MensalistaRemote bean = ServiceLocator.getBean(MensalistaRemote.class, "MensalistaBean");
        return bean.getHorarioMensalista(diaSemana, quadra);
    }
    
    public void alterarMensalidade(Mensalista obj) throws Exception{
        MensalistaRemote bean = ServiceLocator.getBean(MensalistaRemote.class, "MensalistaBean");
        bean.alteraMensalidade(obj);
    }

    public static Mensalista criar(Mensalista obj) throws Exception {
        MensalistaRemote bean = ServiceLocator.getBean(MensalistaRemote.class, "MensalistaBean");
        return bean.criarMensalista(obj);
    }
}
