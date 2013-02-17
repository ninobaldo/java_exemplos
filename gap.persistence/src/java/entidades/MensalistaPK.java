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
public class MensalistaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "quadra_id", nullable = false)
    private long quadraId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pessoa_id", nullable = false)
    private long pessoaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    private long id;

    public MensalistaPK() {
    }

    public MensalistaPK(long quadraId, long pessoaId, long id) {
        this.quadraId = quadraId;
        this.pessoaId = pessoaId;
        this.id = id;
    }

    public long getQuadraId() {
        return quadraId;
    }

    public void setQuadraId(long quadraId) {
        this.quadraId = quadraId;
    }

    public long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(long pessoaId) {
        this.pessoaId = pessoaId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) quadraId;
        hash += (int) pessoaId;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MensalistaPK)) {
            return false;
        }
        MensalistaPK other = (MensalistaPK) object;
        if (this.quadraId != other.quadraId) {
            return false;
        }
        if (this.pessoaId != other.pessoaId) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.MensalistaPK[ quadraId=" + quadraId + ", pessoaId=" + pessoaId + ", id=" + id + " ]";
    }
    
}
