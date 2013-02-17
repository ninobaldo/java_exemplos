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
    @NamedQuery(name = "Cliente.findByNomeOrDocumento", query = "SELECT c FROM Cliente c WHERE c.nome like :nome or c.documento like :documento"),
    @NamedQuery(name = "Cliente.findById", query = "SELECT c FROM Cliente c WHERE c.id = :id")})
public class Cliente extends Pessoa {

    private static final long serialVersionUID = 8571164112196449720L;
    @ManyToMany(mappedBy = "alunos")
    private Set<Turma> turmas;
    @OneToMany
    @JoinTable(name = "Responsavel_Cliente")
    private Set<Cliente> responsaveis;

    public Cliente() {
    }

    @XmlTransient
    public Set<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(Set<Turma> turmaSet) {
        this.turmas = turmaSet;
    }

    public Set<Cliente> getResponsaveis() {
        return responsaveis;
    }

    public void setResponsaveis(Set<Cliente> responsaveis) {
        this.responsaveis = responsaveis;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
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