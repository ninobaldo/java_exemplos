/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author avsilva
 */
@Entity
@Table(name = "quadra", catalog = "k22", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Quadra.findAll", query = "SELECT q FROM Quadra q"),
    @NamedQuery(name = "Quadra.findById", query = "SELECT q FROM Quadra q WHERE q.id = :id"),
    @NamedQuery(name = "Quadra.findByIsDisponivel", query = "SELECT q FROM Quadra q WHERE q.isDisponivel = :isDisponivel"),
    @NamedQuery(name = "Quadra.findByNome", query = "SELECT q FROM Quadra q WHERE q.nome = :nome"),
    @NamedQuery(name = "Quadra.findByDescricao", query = "SELECT q FROM Quadra q WHERE q.descricao = :descricao")})
public class Quadra implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic(optional = false)
    @NotNull(message = "Informe se está ou não disponível")
    @Column(name = "isDisponivel", nullable = false)
    private boolean isDisponivel;
    @Basic(optional = false)
    @NotNull(message = "Entre com o nome")
    @Size(min = 1, max = 50)
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;
    @Size(max = 100)
    @Column(name = "descricao", length = 100)
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quadra")
    private Set<Mensalista> mensalistaSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quadra")
    private Set<Esporadico> esporadicoSet;

    public Quadra() {
    }

    public Quadra(Long id) {
        this.id = id;
    }

    public Quadra(Long id, boolean isDisponivel, String nome) {
        this.id = id;
        this.isDisponivel = isDisponivel;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getIsDisponivel() {
        return isDisponivel;
    }

    public void setIsDisponivel(boolean isDisponivel) {
        this.isDisponivel = isDisponivel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public Set<Mensalista> getMensalistaSet() {
        return mensalistaSet;
    }

    public void setMensalistaSet(Set<Mensalista> mensalistaSet) {
        this.mensalistaSet = mensalistaSet;
    }

    @XmlTransient
    public Set<Esporadico> getEsporadicoSet() {
        return esporadicoSet;
    }

    public void setEsporadicoSet(Set<Esporadico> esporadicoSet) {
        this.esporadicoSet = esporadicoSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Quadra)) {
            return false;
        }
        Quadra other = (Quadra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Quadra[ id=" + id + " ]";
    }
    
}
