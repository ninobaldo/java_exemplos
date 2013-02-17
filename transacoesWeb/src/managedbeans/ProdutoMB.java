package managedbeans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import entidades.Produto;

import sessionbeans.ProdutoRepositorio;

@ManagedBean
public class ProdutoMB {
	
	@EJB
	private ProdutoRepositorio repositorio;
	private Produto produto = new Produto();

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	private List<Produto> produtosCache;

	public void adiciona() {
		this.repositorio.adiciona(produto);
		this.produto = new Produto();
		this.produtosCache = null;
	}

	public List<Produto> getProdutos() {
		if (this.produtosCache == null)
			this.produtosCache = this.repositorio.getProdutos();

		return produtosCache;
	}

}
