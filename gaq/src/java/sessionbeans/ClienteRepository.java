/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entidades.Cliente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author avsilva
 */
@Stateless
public class ClienteRepository {

    @PersistenceContext
    EntityManager manager;

    public void adicionar(Cliente cliente) {
        manager.persist(cliente);
    }

    public void remover(Cliente cliente) {
        manager.remove(manager.merge(cliente));
    }

    public void alterar(Cliente cliente) {
        manager.merge(cliente);
    }

    public List<Cliente> findAll() {
        TypedQuery<Cliente> query = this.manager.createQuery(
                "select x from Cliente x", Cliente.class);
        return query.getResultList();
    }

    public Cliente findById(Long id) {
        return this.manager.find(Cliente.class, id);
    }
}
