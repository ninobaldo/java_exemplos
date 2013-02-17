/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author avsilva
 */
public class QuadraInvalidoException extends GAPBusinessException {

    private static final long serialVersionUID = -2395198025892828505L;
    private static final String msn = "Quadra está inválido.\n";

    /**
     * Creates a new instance of
     * <code>QuadraInvalidoException</code> without detail message.
     */
    public QuadraInvalidoException() {
        super(msn);
    }

    /**
     * Constructs an instance of
     * <code>QuadraInvalidoException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public QuadraInvalidoException(String mensagem) {
        super(msn + mensagem);
    }
    
     public QuadraInvalidoException(Exception e) {
        super(msn, e);
    }
}
