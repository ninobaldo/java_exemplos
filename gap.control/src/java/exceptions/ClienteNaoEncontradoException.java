/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author avsilva
 */
public class ClienteNaoEncontradoException extends GAPBusinessException {
    private static final long serialVersionUID = 2172684763548873962L;

    /**
     * Creates a new instance of
     * <code>ClienteNaoEncontradoException</code> without detail message.
     */
    public ClienteNaoEncontradoException() {
        super("Cliente(s) não encontrado(s)");
    }

    /**
     * Constructs an instance of
     * <code>ClienteNaoEncontradoException</code> with the specified detail
     * message.
     *
     * @param msg the detail message.
     */
    public ClienteNaoEncontradoException(String msg) {
        super(msg);
    }
}
