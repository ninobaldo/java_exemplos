/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author avsilva
 */
public class SituacaoUsuarioInvalidaException extends GAPBusinessException {
    private static final long serialVersionUID = 8127554128608564801L;
    private static final String msn = "Situação de Usuário Inválida!";

    
    public SituacaoUsuarioInvalidaException() {
        super(msn);
    }
    
    public SituacaoUsuarioInvalidaException(Exception e) {
        super(msn, e);
    }
    
}
