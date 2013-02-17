/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author avsilva
 */
@Entity
@Table(name = "pessoa", catalog = "k22", schema = "")
@XmlRootElement
@Inheritance(strategy= InheritanceType.JOINED)
@NamedQueries({
    @NamedQuery(name = "Pessoa.findAll", query = "SELECT p FROM Pessoa p"),
    @NamedQuery(name = "Pessoa.findById", query = "SELECT p FROM Pessoa p WHERE p.id = :id"),
    @NamedQuery(name = "Pessoa.findByNome", query = "SELECT p FROM Pessoa p WHERE p.nome = :nome")})
public class Pessoa implements Serializable {
    private static final long serialVersionUID = 1759444001276029206L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Size(max = 50)
    @Column(name = "nome", length = 50)
    private String nome;
    
    @OneToMany(cascade = CascadeType.ALL, fetch= FetchType.EAGER)
    private List<Telefone> telefones; 
    @OneToMany(cascade = CascadeType.ALL)
    private List<Email> emails;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private List<Mensalista> mensalistas;

    public Pessoa() {
    }

    public Pessoa(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public List<Mensalista> getMensalistas() {
        return mensalistas;
    }

    public void setMensalistas(List<Mensalista> mensalistas) {
        this.mensalistas = mensalistas;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Pessoa)) {
            return false;
        }
        Pessoa other = (Pessoa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Pessoa[ id=" + id + " ]";
    }
}
