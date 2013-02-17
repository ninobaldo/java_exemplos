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
public class ResponsavelClientePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cliente_id", nullable = false)
    private long clienteid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "responsaveis_id", nullable = false)
    private long responsaveisId;

    public ResponsavelClientePK() {
    }

    public ResponsavelClientePK(long clienteid, long responsaveisId) {
        this.clienteid = clienteid;
        this.responsaveisId = responsaveisId;
    }

    public long getClienteid() {
        return clienteid;
    }

    public void setClienteid(long clienteid) {
        this.clienteid = clienteid;
    }

    public long getResponsaveisId() {
        return responsaveisId;
    }

    public void setResponsaveisId(long responsaveisId) {
        this.responsaveisId = responsaveisId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) clienteid;
        hash += (int) responsaveisId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResponsavelClientePK)) {
            return false;
        }
        ResponsavelClientePK other = (ResponsavelClientePK) object;
        if (this.clienteid != other.clienteid) {
            return false;
        }
        if (this.responsaveisId != other.responsaveisId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ResponsavelClientePK[ clienteid=" + clienteid + ", responsaveisId=" + responsaveisId + " ]";
    }
    
}
