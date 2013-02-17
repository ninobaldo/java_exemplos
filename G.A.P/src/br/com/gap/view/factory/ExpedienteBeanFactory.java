/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gap.view.factory;

import br.com.gap.view.util.ServiceLocator;
import entidades.Expediente;
import java.util.List;
import sessionbeans.repository.ExpedienteRepositoryRemote;

/**
 *
 * @author mjustino
 */
public class ExpedienteBeanFactory {
    public static Expediente findById(long id) throws Exception {
        ExpedienteRepositoryRemote bean = ServiceLocator.getBean(ExpedienteRepositoryRemote.class, "ExpedienteRepository");
        return bean.findById(id);
    }
    
    public static List<Expediente> findAll() throws Exception{
        ExpedienteRepositoryRemote bean = ServiceLocator.getBean(ExpedienteRepositoryRemote.class, "ExpedienteRepository");
        return bean.findAll();
    }

    public static void remover(Expediente obj) throws Exception {        
        ExpedienteRepositoryRemote bean = ServiceLocator.getBean(ExpedienteRepositoryRemote.class, "ExpedienteRepository");
        bean.remover(obj);
    }
    
    public static Expediente alterar(Expediente obj) throws Exception{
        ExpedienteRepositoryRemote bean = ServiceLocator.getBean(ExpedienteRepositoryRemote.class, "ExpedienteRepository");
        return bean.alterar(obj);
    }
    
    public static Expediente adicionar(Expediente obj) throws Exception{
        ExpedienteRepositoryRemote bean = ServiceLocator.getBean(ExpedienteRepositoryRemote.class, "ExpedienteRepository");
        return bean.adicionar(obj);
    }
}
