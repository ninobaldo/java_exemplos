/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans.repository;

import entidades.Quadra;
import exceptions.GAPBusinessException;
import exceptions.QuadraInvalidoException;
import exceptions.QuadraNaoEncontradoException;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author avsilva
 */
@Stateless
@Remote(QuadraRepositoryRemote.class)
public class QuadraRepository implements QuadraRepositoryRemote {
//@todo fazer validações

    @PersistenceContext
    EntityManager manager;

    @Override
    public Quadra adicionar(Quadra q) throws GAPBusinessException {
        validarQuadra(q, false);

        manager.persist(q);
        return q;
    }

    @Override
    public void remover(Quadra q) throws GAPBusinessException {
        validarQuadra(q, true);

        Quadra merge = manager.merge(q);
        manager.remove(merge);
    }

    @Override
    public Quadra alterar(Quadra q) throws GAPBusinessException {
        validarQuadra(q, true);
        manager.merge(q);
        return q;
    }

    @Override
    public List<Quadra> findAll() {

        TypedQuery<Quadra> query = this.manager.createNamedQuery(
                "Quadra.findAll", Quadra.class);

        List<Quadra> quadras = query.getResultList();

        return quadras;
    }

    @Override
    public Quadra findById(Long id) throws GAPBusinessException {
        Quadra quadra = this.manager.find(Quadra.class, id);

        if (quadra == null || quadra.getId() == null || quadra.getId() == 0l) {
            throw new QuadraNaoEncontradoException();
        }
        return quadra;
    }

    private void validarQuadra(Quadra q, boolean validaId) throws GAPBusinessException {

        if (q == null) {
            throw new QuadraInvalidoException("A quadra está nula!");
        } else if (validaId && (q.getId() == null || q.getId() == 0l)) {
            throw new QuadraInvalidoException("A quadra está sem id!");
        } else if (q.getNome() == null || q.getNome().equals("")) {
            throw new QuadraInvalidoException("A quadra está sem nome!");
        }
    }
}
