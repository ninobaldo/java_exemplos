/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author avsilva
 */
public class ExpedienteNaoEncontradoException extends GAPBusinessException {

    private static final String MSN = "Nenhum expediente foi encontrado.";
    private static final long serialVersionUID = 1129961272927349730L;
    /**
     * Creates a new instance of
     * <code>ExpedienteNaoEncontradoException</code> without detail message.
     */
    public ExpedienteNaoEncontradoException() {
        super(ExpedienteNaoEncontradoException.MSN);
    }

    /**
     * Constructs an instance of
     * <code>ExpedienteNaoEncontradoException</code> with the specified detail
     * message.
     *
     * @param msg the detail message.
     */
    public ExpedienteNaoEncontradoException(String msg) {
        super(msg);
    }
    
    public ExpedienteNaoEncontradoException(String msg, Exception ex) {
        super(msg, ex);
    }
    
    public ExpedienteNaoEncontradoException(Exception ex) {
        super(ExpedienteNaoEncontradoException.MSN, ex);
    }
}
