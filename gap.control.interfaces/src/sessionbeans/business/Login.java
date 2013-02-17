/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans.business;

import entidades.Usuario;
import exceptions.UsuarioNaoEncontradoException;
import exceptions.UsuarioNovoException;

/**
 *
 * @author avsilva
 * @version 0.1
 */
public interface Login {
    
   /**
    * Utilizado para realizar o login no sistema
    * @param usuario login
    * @param senha
    * @return usuario valido no sistema.
    * @throws UsuarioNaoEncontradoException
    * @throws UsuarioNovoException 
    */
    Usuario login(String usuario, String senha) throws UsuarioNaoEncontradoException, UsuarioNovoException;
    
    /**
     * Faz logout no sistema
     * @param usuario usuario para logout
     */
    void logout(Usuario usuario);
    
    /**
     * Troca a senha por outra no sistema
     * @param usuario login do usuario
     * @param senhaAntiga senha antiga
     * @param senhaNova nova senha
     * @return usuario valido no sistema
     */
    void trocarSenha(String usuario, String senhaAntiga, String senhaNova) throws UsuarioNaoEncontradoException;
}
