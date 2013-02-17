/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import java.io.Serializable;

/**
 *
 * @author avsilva 
 * 
 */
public class UsuarioNaoEncontradoException extends LoginException{
    private static final long serialVersionUID = -2159339959715037439L;

    /**
     * Creates a new instance of
     * <code>UsuarioNaoEncontradoException</code> without detail message.
     */
    public UsuarioNaoEncontradoException() {
        super("Usuário ou senha errados.");
    }

    /**
     * Constructs an instance of
     * <code>UsuarioNaoEncontradoException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public UsuarioNaoEncontradoException(String msg) {
        super(msg);
    }
}
