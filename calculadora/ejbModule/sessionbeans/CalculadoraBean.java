/**
 * 
 */
package sessionbeans;

import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 * @author avsilva
 * 
 */
@Stateless
@Local(Calculadora.class)
public class CalculadoraBean implements Calculadora {

	/*
	 * (non-Javadoc)
	 * 
	 * @see sessionbeans.Calculadora#soma(double, double)
	 */
	@Override
	public double soma(double a, double b) {
		return a + b;
	}

}
