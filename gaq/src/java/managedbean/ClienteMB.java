
package managedbean;

import entidades.Cliente;
import entidades.Telefone;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sessionbeans.ClienteRepository;

/**
 *
 * @author avsilva
 */
@ManagedBean
@SessionScoped
public class ClienteMB {

    @EJB
    private ClienteRepository clienteRepository;
    private Cliente cliente = new Cliente();
    private List<Cliente> clientes;
    private Telefone telefone = new Telefone();
    private List<Telefone> telefones;

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public List<Telefone> getTelefones() {

        if (telefones == null) {
            telefones = new ArrayList<Telefone>(this.cliente.getTelefones());
        }

        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getClientes() {

        if (clientes == null) {
            clientes = this.clienteRepository.findAll();
        }

        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void save() {
        if (this.getCliente().getId() == null) {
            this.clienteRepository.adicionar(this.getCliente());
        } else {
            this.clienteRepository.alterar(this.getCliente());
        }
        this.cliente = new Cliente();
        this.clientes = null;
    }

    public void delete(Cliente c) {
        this.clienteRepository.remover(c);
        this.clientes = null;
    }

    public void prepareEdit(Long id) {
        this.cliente = this.clienteRepository.findById(id);
    }

    public void saveTelefone() {
        if(this.cliente.getTelefones() == null)
            this.cliente.setTelefones(new ArrayList<Telefone>());
        
        this.cliente.getTelefones().add(telefone);
        this.telefone = new Telefone();
        this.telefones = null;
    }
}