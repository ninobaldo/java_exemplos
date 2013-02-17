package managedbeans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import sessionbeans.LancadorDeDadoBean;

@ManagedBean
public class DadoMB {

	@EJB
	private LancadorDeDadoBean lancadorDeDadoBean;
	
	private int resultado;
	
	public void lancaDado(){
		this.resultado = this.lancadorDeDadoBean.lanca();
	}

	public LancadorDeDadoBean getLancadorDeDadoBean() {
		return lancadorDeDadoBean;
	}

	public void setLancadorDeDadoBean(LancadorDeDadoBean lancadorDeDadoBean) {
		this.lancadorDeDadoBean = lancadorDeDadoBean;
	}

	public int getResultado() {
		return resultado;
	}

	public void setResultado(int resultado) {
		this.resultado = resultado;
	}	
}
