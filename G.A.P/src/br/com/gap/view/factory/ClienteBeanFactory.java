/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gap.view.factory;

import br.com.gap.view.util.ServiceLocator;
import entidades.Cliente;
import java.util.List;
import sessionbeans.repository.ClienteRepositoryRemote;

/**
 *
 * @author mjustino
 */
public class ClienteBeanFactory {
    public static List<Cliente> findAll() throws Exception{
        ClienteRepositoryRemote bean = ServiceLocator.getBean(ClienteRepositoryRemote.class, "ClienteRepository");
        return bean.findAll();
    }
    
    public static List<Cliente> findByNomeOrDocumento(String nome, String documento) throws Exception {
        ClienteRepositoryRemote bean = ServiceLocator.getBean(ClienteRepositoryRemote.class, "ClienteRepository");
        return bean.findByNomeOrDocumento(nome, documento);
    }

    public static void remover(Cliente obj) throws Exception {        
        ClienteRepositoryRemote bean = ServiceLocator.getBean(ClienteRepositoryRemote.class, "ClienteRepository");
        bean.remover(obj);
    }
    
    public static Cliente alterar(Cliente obj) throws Exception{
        ClienteRepositoryRemote bean = ServiceLocator.getBean(ClienteRepositoryRemote.class, "ClienteRepository");
        return bean.alterar(obj);
    }
    
    public static Cliente adicionar(Cliente obj) throws Exception{
        ClienteRepositoryRemote bean = ServiceLocator.getBean(ClienteRepositoryRemote.class, "ClienteRepository");
        return bean.adicionar(obj);
    }
}
