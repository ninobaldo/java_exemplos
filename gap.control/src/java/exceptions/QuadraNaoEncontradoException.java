/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author avsilva
 */
public class QuadraNaoEncontradoException extends GAPBusinessException {
    private static final long serialVersionUID = 2670444254883377140L;

    public QuadraNaoEncontradoException() {
        super("Quadra(s) não encontrada(s)");
    }

    public QuadraNaoEncontradoException(String msg) {
        super(msg);
    }
}
