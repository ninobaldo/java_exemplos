/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.svbr.control;

import br.com.svbr.model.Usuario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Scope;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author avsilva
 */
@Stateless
@Remote(Login.class)
public class LoginBean implements Login {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Usuario login(String usuario, String senha) {
        String sql = "SELECT u FROM Usuario u "
                    + "WHERE u.login = :login and "
                    + "u.senha = :senha";

        TypedQuery<Usuario> query = manager.createQuery(sql, Usuario.class);
            query.setParameter("login", usuario);
            query.setParameter("senha", senha);

        List<Usuario> usuarios = query.getResultList();
        for (Usuario u : usuarios) {
            System.out.println(u.getLogin());
        }
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
//        } catch (Exception e) {
//            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, e);
//             return null;       
//        }
    }
}
