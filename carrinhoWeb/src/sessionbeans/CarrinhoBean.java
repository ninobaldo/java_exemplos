/**
 * 
 */
package sessionbeans;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 * @author avsilva
 * 
 */
@Stateful
public class CarrinhoBean {

	private Set<String> produtos = new HashSet<String>();

	public void adiciona(String produto) {
		this.produtos.add(produto);
	}

	public void remove(String produto) {
		this.produtos.remove(produto);
	}

	public Set<String> getProdutos() {
		return produtos;
	}

	@Remove
	public void finalizaCompra() {

	}

	// Callbacks
	@PostConstruct
	public void inicializando() {
		System.out.println("Mais um carrinho criado...");
	}

	@PreDestroy
	public void destruindo() {
		System.out.println("Mais um carrinho será destruído...");
	}
	
	@PrePassivate
	public void passivando(){
		System.out.println("Mais um carrinho será passivado...");
	}
	
	@PostActivate
	public void ativando(){
		System.out.println("Mais um carrinho foi ativado...");
	}
}
