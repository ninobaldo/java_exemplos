package managedbeans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import sessionbeans.Calculadora;

@ManagedBean
public class CalculadoraMB {
	
	@EJB
	private Calculadora calculadora;
	private double a;
	public Calculadora getCalculadora() {
		return calculadora;
	}

	public void setCalculadora(Calculadora calculadora) {
		this.calculadora = calculadora;
	}

	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

	public double getResultado() {
		return resultado;
	}

	public void setResultado(double resultado) {
		this.resultado = resultado;
	}

	private double b;
	private double resultado;
	
	public void soma() {
		this.resultado = this.calculadora.soma(a, b);
	}
}
