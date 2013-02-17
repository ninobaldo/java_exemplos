/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gap.view.factory;

import br.com.gap.view.util.ServiceLocator;
import entidades.Usuario;
import java.util.List;
import sessionbeans.business.LoginRemote;
import sessionbeans.repository.UsuarioRepositoryRemote;

/**
 *
 * @author mjustino
 */
public class UsuarioBeanFactory {

    public static Usuario login(String login, String pwd) throws Exception {
        LoginRemote bean = ServiceLocator.getBean(LoginRemote.class, "LoginBean");
        return bean.login(login, pwd);
    }

    public static List<Usuario> findByLoginOrNome(String login, String name) throws Exception {
        UsuarioRepositoryRemote bean = ServiceLocator.getBean(UsuarioRepositoryRemote.class, "UsuarioRepository");
        return bean.findByLoginOrNome(login, name);
    }

    public static void remover(Usuario usuarioAtual) throws Exception {        
        UsuarioRepositoryRemote bean = ServiceLocator.getBean(UsuarioRepositoryRemote.class, "UsuarioRepository");
        bean.remover(usuarioAtual);
    }
    
    public static Usuario alterar(Usuario usuarioAtual) throws Exception{
        UsuarioRepositoryRemote bean = ServiceLocator.getBean(UsuarioRepositoryRemote.class, "UsuarioRepository");
        return bean.alterar(usuarioAtual);
    }
    
    public static Usuario adicionar(Usuario usuarioAtual) throws Exception{
        UsuarioRepositoryRemote bean = ServiceLocator.getBean(UsuarioRepositoryRemote.class, "UsuarioRepository");
        return bean.adicionar(usuarioAtual);
    }
}
