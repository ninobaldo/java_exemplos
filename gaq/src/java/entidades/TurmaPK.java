/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author avsilva
 */
@Embeddable
public class TurmaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    private long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "professor_id", nullable = false)
    private long professorId;

    public TurmaPK() {
    }

    public TurmaPK(long id, long professorId) {
        this.id = id;
        this.professorId = professorId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(long professorId) {
        this.professorId = professorId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) professorId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TurmaPK)) {
            return false;
        }
        TurmaPK other = (TurmaPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.professorId != other.professorId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TurmaPK[ id=" + id + ", professorId=" + professorId + " ]";
    }
    
}
