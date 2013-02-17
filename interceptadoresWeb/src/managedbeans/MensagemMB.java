package managedbeans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import entidades.Mensagem;

import sessionbeans.MensagemRepositorio;

@ManagedBean
public class MensagemMB {

	@EJB
	private MensagemRepositorio repositorio;

	private Mensagem mensagem = new Mensagem();

	private List<Mensagem> mensagensCache;

	public void adiciona() {
		this.repositorio.adiciona(this.mensagem);
		this.mensagem = new Mensagem();
		this.mensagensCache = null;
	}

	public List<Mensagem> getMensagens() {
		if (this.mensagensCache == null) {
			this.mensagensCache = this.repositorio.getMensagens();
		}
		return this.mensagensCache;
	}

	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}
}
