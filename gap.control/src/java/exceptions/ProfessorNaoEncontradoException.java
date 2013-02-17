/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author avsilva
 */
public class ProfessorNaoEncontradoException extends GAPBusinessException {

    private static final String MSN = "Nenhum professor foi encontrado.";
    private static final long serialVersionUID = 1129961272927349730L;
    /**
     * Creates a new instance of
     * <code>ExpedienteNaoEncontradoException</code> without detail message.
     */
    public ProfessorNaoEncontradoException() {
        super(MSN);
    }

    /**
     * Constructs an instance of
     * <code>ExpedienteNaoEncontradoException</code> with the specified detail
     * message.
     *
     * @param msg the detail message.
     */
    public ProfessorNaoEncontradoException(String msg) {
        super(msg);
    }
    
    public ProfessorNaoEncontradoException(String msg, Exception ex) {
        super(msg, ex);
    }
    
    public ProfessorNaoEncontradoException(Exception ex) {
        super(MSN, ex);
    }
}
