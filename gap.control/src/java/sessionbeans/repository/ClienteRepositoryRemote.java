/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans.repository;

import entidades.Cliente;
import exceptions.GAPBusinessException;
import java.util.List;

/**
 *
 * @author avsilva
 */
public interface ClienteRepositoryRemote {

    Cliente adicionar(Cliente cliente) throws GAPBusinessException;

    Cliente alterar(Cliente cliente) throws GAPBusinessException;

    List<Cliente> findAll() throws GAPBusinessException;

    Cliente findById(Long id) throws GAPBusinessException;

    void remover(Cliente cliente) throws GAPBusinessException;

    public List<Cliente> findByNomeOrDocumento(String nome, String documento) throws GAPBusinessException;
}
