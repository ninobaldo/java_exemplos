package sessionbeans;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import entidades.Produto;

@Stateless
public class ProdutoRepositorio {

    @PersistenceContext
    private EntityManager entityManager;
    @Resource
    private SessionContext context;

    public void adiciona(Produto produto) {
        this.entityManager.persist(produto);

        if (produto.getPreco() < 0) {
            this.context.setRollbackOnly();
        }
    }

    public List<Produto> getProdutos() {
        TypedQuery<Produto> query = this.entityManager.createQuery(
                "select x from Produto x", Produto.class);

        return query.getResultList();
    }
}
