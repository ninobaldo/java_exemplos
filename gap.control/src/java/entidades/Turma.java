/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author avsilva
 */
@Entity
@Table(name = "turma", catalog = "k22", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Turma.findAll", query = "SELECT t FROM Turma t"),
    @NamedQuery(name = "Turma.findById", query = "SELECT t FROM Turma t WHERE t.turmaPK.id = :id"),
    @NamedQuery(name = "Turma.findByProfessorId", query = "SELECT t FROM Turma t WHERE t.turmaPK.professorId = :professorId")})
public class Turma implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TurmaPK turmaPK;
    @JoinTable(name = "turma_has_cliente", joinColumns = {
        @JoinColumn(name = "turma_id", referencedColumnName = "id", nullable = false),
        @JoinColumn(name = "turma_professor_id", referencedColumnName = "professor_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "cliente_id", referencedColumnName = "id", nullable = false)})
    @ManyToMany
    private Set<Cliente> alunos;
    @JoinColumn(name = "professor_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Professor professor;

    public Turma() {
    }

    public Turma(TurmaPK turmaPK) {
        this.turmaPK = turmaPK;
    }

    public Turma(long id, long professorId) {
        this.turmaPK = new TurmaPK(id, professorId);
    }

    public TurmaPK getTurmaPK() {
        return turmaPK;
    }

    public void setTurmaPK(TurmaPK turmaPK) {
        this.turmaPK = turmaPK;
    }

    @XmlTransient
    public Set<Cliente> getAlunos() {
        return alunos;
    }

    public void setAlunos(Set<Cliente> alunos) {
        this.alunos = alunos;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (turmaPK != null ? turmaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Turma)) {
            return false;
        }
        Turma other = (Turma) object;
        if ((this.turmaPK == null && other.turmaPK != null) || (this.turmaPK != null && !this.turmaPK.equals(other.turmaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Turma[ turmaPK=" + turmaPK + " ]";
    }
    
}
