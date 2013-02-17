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
public class ReservaNaoValidaException extends GAPBusinessException{
    private static final long serialVersionUID = -3932706466411436824L;

    /**
     * Creates a new instance of
     * <code>ReservaNaoValidaException</code> without detail message.
     */
    public ReservaNaoValidaException() {
        super("Reserva não válida.");
    }

    /**
     * Constructs an instance of
     * <code>ReservaNaoValidaException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ReservaNaoValidaException(String msg) {
        super(msg);
    }
    
    /**
     * Constructs an instance of
     * <code>ReservaNaoValidaException</code> with the specified detail message.
     * @param msg
     * @param ex 
     */
    public ReservaNaoValidaException(String msg, Exception ex) {
        super(msg, ex);
    }    
}
