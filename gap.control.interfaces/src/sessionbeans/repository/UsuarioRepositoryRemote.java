/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans.repository;

import entidades.Usuario;
import exceptions.UsuarioJaExistenteException;
import java.util.List;

/**
 *
 * @author avsilva
 */
public interface UsuarioRepositoryRemote {

    /**
     * Adiciona um novo usuário ao sistema
     * @param usuario usuario a ser adicionado com pelo menos o login e no máximo login, nome definidos
     * @return retorna o usuário persistido
     * @throws UsuarioJaExistenteException 
     */
    Usuario adicionar(Usuario usuario) throws UsuarioJaExistenteException;

    Usuario alterar(Usuario usuario);

    void remover(Usuario usuario);
    
    List<Usuario> findAll();

    Usuario findById(Long id);

    List<Usuario> findByLoginOrNome(String login, String nome);
}
