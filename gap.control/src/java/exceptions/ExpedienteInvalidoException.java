/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author avsilva
 */
public class ExpedienteInvalidoException extends GAPBusinessException {
    private static final long serialVersionUID = -8475564005661088183L;
    private static final String msn = "Expediente Inválido.\n";
    public ExpedienteInvalidoException(String mensagem) {
        super(msn + mensagem);
    }
}
