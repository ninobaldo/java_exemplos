/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author avsilva
 */
public class UsuarioInativoException extends LoginException {
    private static final long serialVersionUID = -3958808717044699924L;

    /**
     * Creates a new instance of
     * <code>UsuarioInativoException</code> without detail message.
     */
    public UsuarioInativoException() {
        super("Usuário está inátivo!");
    }

    /**
     * Constructs an instance of
     * <code>UsuarioInativoException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public UsuarioInativoException(String msg) {
        super(msg);
    }
}
