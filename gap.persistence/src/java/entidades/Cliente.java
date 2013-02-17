/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author avsilva
 */
@Entity
@Table(name = "cliente", catalog = "k22", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findById", query = "SELECT c FROM Cliente c WHERE c.id = :id")})
public class Cliente extends Pessoa {
    private static final long serialVersionUID = 8571164112196449720L;
    
    @ManyToMany(mappedBy = "alunos")
    private Set<Turma> turmaSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private Set<Esporadico> esporadicoSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private Set<ResponsavelCliente> responsavelClienteSet;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "cliente1")
    private ResponsavelCliente responsavelCliente;

    public Cliente() {
    }

    @XmlTransient
    public Set<Turma> getTurmaSet() {
        return turmaSet;
    }

    public void setTurmaSet(Set<Turma> turmaSet) {
        this.turmaSet = turmaSet;
    }

    @XmlTransient
    public Set<Esporadico> getEsporadicoSet() {
        return esporadicoSet;
    }

    public void setEsporadicoSet(Set<Esporadico> esporadicoSet) {
        this.esporadicoSet = esporadicoSet;
    }

    @XmlTransient
    public Set<ResponsavelCliente> getResponsavelClienteSet() {
        return responsavelClienteSet;
    }

    public void setResponsavelClienteSet(Set<ResponsavelCliente> responsavelClienteSet) {
        this.responsavelClienteSet = responsavelClienteSet;
    }

    public ResponsavelCliente getResponsavelCliente() {
        return responsavelCliente;
    }

    public void setResponsavelCliente(ResponsavelCliente responsavelCliente) {
        this.responsavelCliente = responsavelCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Cliente[ id=" + getId() + " ]";
    }    
}