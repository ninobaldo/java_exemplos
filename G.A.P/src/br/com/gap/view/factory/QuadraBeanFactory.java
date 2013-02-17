/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gap.view.factory;

import br.com.gap.view.util.ServiceLocator;
import entidades.Quadra;
import java.util.List;
import sessionbeans.repository.QuadraRepositoryRemote;

/**
 *
 * @author mjustino
 */
public class QuadraBeanFactory {
    public static Quadra findById(long id) throws Exception {
        QuadraRepositoryRemote bean = ServiceLocator.getBean(QuadraRepositoryRemote.class, "QuadraRepository");
        return bean.findById(id);
    }
    
    public static List<Quadra> findAll() throws Exception{
        QuadraRepositoryRemote bean = ServiceLocator.getBean(QuadraRepositoryRemote.class, "QuadraRepository");
        return bean.findAll();
    }

    public static void remover(Quadra obj) throws Exception {        
        QuadraRepositoryRemote bean = ServiceLocator.getBean(QuadraRepositoryRemote.class, "QuadraRepository");
        bean.remover(obj);
    }
    
    public static Quadra alterar(Quadra obj) throws Exception{
        QuadraRepositoryRemote bean = ServiceLocator.getBean(QuadraRepositoryRemote.class, "QuadraRepository");
        return bean.alterar(obj);
    }
    
    public static Quadra adicionar(Quadra obj) throws Exception{
        QuadraRepositoryRemote bean = ServiceLocator.getBean(QuadraRepositoryRemote.class, "QuadraRepository");
        return bean.adicionar(obj);
    }
}
