/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author avsilva
 */
@Embeddable
public class EsporadicoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "quadra_id", nullable = false)
    private long quadraId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cliente_id", nullable = false)
    private long clienteId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    public EsporadicoPK() {
    }

    public EsporadicoPK(long quadraId, long clienteId, Date data) {
        this.quadraId = quadraId;
        this.clienteId = clienteId;
        this.data = data;
    }

    public long getQuadraId() {
        return quadraId;
    }

    public void setQuadraId(long quadraId) {
        this.quadraId = quadraId;
    }

    public long getClienteId() {
        return clienteId;
    }

    public void setClienteId(long clienteId) {
        this.clienteId = clienteId;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) quadraId;
        hash += (int) clienteId;
        hash += (data != null ? data.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EsporadicoPK)) {
            return false;
        }
        EsporadicoPK other = (EsporadicoPK) object;
        if (this.quadraId != other.quadraId) {
            return false;
        }
        if (this.clienteId != other.clienteId) {
            return false;
        }
        if ((this.data == null && other.data != null) || (this.data != null && !this.data.equals(other.data))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EsporadicoPK[ quadraId=" + quadraId + ", clienteId=" + clienteId + ", data=" + data + " ]";
    }
    
}
