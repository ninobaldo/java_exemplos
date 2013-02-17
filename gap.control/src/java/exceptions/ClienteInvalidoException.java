/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author avsilva
 */
public class ClienteInvalidoException extends GAPBusinessException {

    private static final long serialVersionUID = -2395198025892828505L;
    private static final String msn = "Cliente está é inválido.\n";

    /**
     * Creates a new instance of
     * <code>ClienteInvalidoException</code> without detail message.
     */
    public ClienteInvalidoException() {
        super(msn);
    }

    /**
     * Constructs an instance of
     * <code>ClienteInvalidoException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ClienteInvalidoException(String mensagem) {
        super(msn + mensagem);
    }
    
     public ClienteInvalidoException(Exception e) {
        super(msn, e);
    }
}
