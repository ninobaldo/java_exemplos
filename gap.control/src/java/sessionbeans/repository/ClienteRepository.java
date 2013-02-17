/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans.repository;

import entidades.Cliente;
import exceptions.ClienteInvalidoException;
import exceptions.ClienteNaoEncontradoException;
import exceptions.GAPBusinessException;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author avsilva
 */
@Stateless
@Remote(ClienteRepositoryRemote.class)
public class ClienteRepository implements ClienteRepositoryRemote {

    @PersistenceContext
    EntityManager manager;

    @Override
    public Cliente adicionar(Cliente cliente) throws GAPBusinessException {
        validarCliente(cliente, false);

        manager.persist(cliente);
        return cliente;
    }

    @Override
    public void remover(Cliente cliente) throws GAPBusinessException {
        validarCliente(cliente, true);
        
        manager.remove(manager.merge(cliente));
    }

    @Override
    public Cliente alterar(Cliente cliente) throws GAPBusinessException {
        validarCliente(cliente, true);

        manager.merge(cliente);
        return cliente;
    }

    @Override
    public List<Cliente> findAll() throws GAPBusinessException {
        TypedQuery<Cliente> query = this.manager.createNamedQuery("Cliente.findAll", Cliente.class);
                
        List<Cliente> clientes = query.getResultList();

        // força o carregamento EAGER, para evitar o LazyInitializationException
        for (Cliente cliente : clientes) {
            manager.refresh(cliente);
        }

        return clientes;
    }

    @Override
    public Cliente findById(Long id) throws GAPBusinessException {
        Cliente c = this.manager.find(Cliente.class, id);
        
        if (c == null || c.getId() == null || c.getId() == 0l) {
            throw new ClienteNaoEncontradoException();
        }
        
        // força o carregamento EAGER, para evitar o LazyInitializationException
        manager.refresh(c);
        return c;
    }

    @Override
    public List<Cliente> findByNomeOrDocumento(String nome, String documento) throws GAPBusinessException {

        TypedQuery<Cliente> query = this.manager.createNamedQuery(
                "Cliente.findByNomeOrDocumento", Cliente.class);
        documento = documento == null || documento.trim().equals("") ? documento : "%" + documento + "%";
        nome = nome == null || nome.trim().equals("") ? nome : "%" + nome + "%";

        query.setParameter("nome", nome);
        query.setParameter("documento", documento);

        List<Cliente> clientes = query.getResultList();

        if (clientes.isEmpty()) {
            throw new ClienteNaoEncontradoException();
        }
        
        // força o carregamento EAGER, para evitar o LazyInitializationException
        for (Cliente cliente : clientes) {
            manager.refresh(cliente);
        }

        return clientes;
    }

    private void validarCliente(Cliente cliente, boolean validaId) throws ClienteInvalidoException {
        if (cliente == null) {
            throw new ClienteInvalidoException("Cliente está nulo");
        } else if (validaId && ((cliente.getId() == null || cliente.getId() == 0l))) {
            throw new ClienteInvalidoException("O cliente não possuie id");
        } else if (cliente.getNome() == null || cliente.getNome().equals("")) {
            throw new ClienteInvalidoException("O nome do cliente deve ser preenchido");
        } else if (cliente.getDocumento() == null || cliente.getDocumento().equals("")) {
            throw new ClienteInvalidoException("O documento do cliente deve ser preenchido");
        }
    }
}
