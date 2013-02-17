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
@Table(name = "professor", catalog = "k22", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Professor.findAll", query = "SELECT p FROM Professor p"),
    @NamedQuery(name = "Professor.findById", query = "SELECT p FROM Professor p WHERE p.id = :id")})
public class Professor extends Pessoa{
    private static final long serialVersionUID = 5235541731604829457L;
   
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "professor")
    private Set<Turma> turmaSet;
   

    public Professor() {
    }

    @XmlTransient
    public Set<Turma> getTurmaSet() {
        return turmaSet;
    }

    public void setTurmaSet(Set<Turma> turmaSet) {
        this.turmaSet = turmaSet;
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
        if (!(object instanceof Professor)) {
            return false;
        }
        Professor other = (Professor) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Professor[ id=" + getId() + " ]";
    }
}