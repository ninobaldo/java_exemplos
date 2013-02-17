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
public class GAPBusinessException extends Exception implements Serializable{
    private static final long serialVersionUID = -6010658477090882805L;

    /**
     * Creates a new instance of
     * <code>GAPBusinessException</code> without detail message.
     */
    public GAPBusinessException() {
    }

    /**
     * Constructs an instance of
     * <code>GAPBusinessException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public GAPBusinessException(String msg) {
        super(msg);
    }
    
    public GAPBusinessException(String msg, Exception ex) {
        super(msg, ex);
    }
}
