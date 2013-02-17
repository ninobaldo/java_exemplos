package sessionbeans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entidades.Mensagem;

@Stateless
public class MensagemRepositorio {

	@PersistenceContext
	private EntityManager entityManager;

	public void adiciona(Mensagem mensagem) {
		System.out.print("Bean chamado!");
		this.entityManager.persist(mensagem);
	}

	public List<Mensagem> getMensagens() {
		TypedQuery<Mensagem> query = entityManager.createQuery(
				"select m from Mensagem m", Mensagem.class);
		
		return query.getResultList();
	}
}
