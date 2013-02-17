/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entidades.Quadra;
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
public class QuadraRepository {

    @PersistenceContext
    EntityManager manager;

    public void adicionar(Quadra q) {
        manager.persist(q);
    }

    public void remover(Quadra q) {
        Quadra merge = manager.merge(q);
        manager.remove(merge);
    }

    public void alterar(Quadra q) {
        manager.merge(q);
    }

    public List<Quadra> findAll() {
        TypedQuery<Quadra> query = this.manager.createQuery(
                "select x from Quadra x", Quadra.class);
        return query.getResultList();
    }

    public Quadra findById(Long id) {
        return this.manager.find(Quadra.class, id);
    }
}
