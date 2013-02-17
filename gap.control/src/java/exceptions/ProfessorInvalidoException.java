/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author avsilva
 */
public class ProfessorInvalidoException extends GAPBusinessException {

    private static final long serialVersionUID = -8475564005661088183L;
    private static final String msn = "Professor Inválido.\n";

    public ProfessorInvalidoException(String mensagem) {
        super(msn + mensagem);
    }
}
