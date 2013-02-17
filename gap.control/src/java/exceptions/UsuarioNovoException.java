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
public class UsuarioNovoException extends LoginException{
    private static final long serialVersionUID = 2651939630255879373L;

    public UsuarioNovoException() {
        super("Usuário novo, favor cadastrar nova senha!");
    }
    
    public UsuarioNovoException(String msn){
        super(msn);
    }
    
}
