/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author avsilva
 */
public class LoginException extends GAPBusinessException {

    private static final long serialVersionUID = 3846454057748663919L;

    /**
     * Creates a new instance of
     * <code>LoginException</code> without detail message.
     */
    public LoginException() {
    }

    /**
     * Constructs an instance of
     * <code>LoginException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public LoginException(String msg) {
        super(msg);
    }

    public LoginException(String msg, Exception ex) {
        super(msg, ex);
    }
}
