/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import java.io.Serializable;

/**
 *
 * @author avsilva
 */
public class UsuarioJaExistenteException extends GAPBusinessException{
    private static final long serialVersionUID = -6010639552736192848L;

    /**
     * Creates a new instance of
     * <code>UsuarioJaExistenteException</code> without detail message.
     */
    public UsuarioJaExistenteException() {
        super("Usuário já existe!");
    }

    /**
     * Constructs an instance of
     * <code>UsuarioJaExistenteException</code> with the specified detail
     * message.
     *
     * @param msg the detail message.
     */
    public UsuarioJaExistenteException(String msg) {
        super(msg);
    }
}
