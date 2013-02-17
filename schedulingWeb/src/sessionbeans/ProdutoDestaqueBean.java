package sessionbeans;

import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

import entidades.Produto;

@Singleton
public class ProdutoDestaqueBean {

    @EJB
    private ProdutoRepositorio repositorio;
    private Produto produtoDestaque;

    public Produto getProdutoDestaque() {
        return produtoDestaque;
    }

    public void setProdutoDestaque(Produto produtoDestaque) {
        this.produtoDestaque = produtoDestaque;
    }

    @Schedule(second = "5")
    public void trocaProdutoDestaque() {
        Random gerador = new Random();
        List<Produto> produtos = this.repositorio.getProdutos();
        int i = gerador.nextInt(produtos.size());
        this.produtoDestaque = produtos.get(i);
    }
}
