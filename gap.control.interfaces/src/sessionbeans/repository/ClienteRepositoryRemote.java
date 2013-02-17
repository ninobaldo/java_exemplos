/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans.repository;

import entidades.Cliente;
import java.util.List;

/**
 *
 * @author avsilva
 */
public interface ClienteRepositoryRemote  {

    void adicionar(Cliente cliente);

    void alterar(Cliente cliente);

    List<Cliente> findAll();

    Cliente findById(Long id);

    void remover(Cliente cliente);
    
}
