/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author avsilva
 */
public class MensalidadeNaoValidaException extends GAPBusinessException {

    private static final long serialVersionUID = -5412291819289657818L;

    public MensalidadeNaoValidaException() {
        super("Mensalidade não valida!");
    }

    public MensalidadeNaoValidaException(String msn) {
        super(msn);
    }

    public MensalidadeNaoValidaException(String msn, Exception ex) {
        super(msn, ex);
    }
}