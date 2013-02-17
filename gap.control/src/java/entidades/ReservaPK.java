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
public class ReservaPK implements Serializable {
    
    private static final long serialVersionUID = -9219258453527245775L;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "cliente_id", nullable = false)
    private long clienteId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quadra_id", nullable = false)
    private long quadraId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horaReserva", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date horaReserva;
    @Temporal(TemporalType.DATE)
    private Date dataReserva;

    public ReservaPK() {
    }

    public ReservaPK(long clienteId, long quadraId, Date horaReserva, Date dataReserva) {
        this.clienteId = clienteId;
        this.quadraId = quadraId;
        this.horaReserva = horaReserva;
        this.dataReserva = dataReserva;
    }

    public long getClienteId() {
        return clienteId;
    }

    public void setClienteId(long clienteId) {
        this.clienteId = clienteId;
    }

    public Date getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
    }

    public Date getHoraReserva() {
        return horaReserva;
    }

    public void setHoraReserva(Date hora) {
        this.horaReserva = hora;
    }

    public long getQuadraId() {
        return quadraId;
    }

    public void setQuadraId(long quadraId) {
        this.quadraId = quadraId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReservaPK other = (ReservaPK) obj;
        if (this.clienteId != other.clienteId) {
            return false;
        }
        if (this.quadraId != other.quadraId) {
            return false;
        }
        if (this.horaReserva != other.horaReserva && (this.horaReserva == null || !this.horaReserva.equals(other.horaReserva))) {
            return false;
        }
        if (this.dataReserva != other.dataReserva && (this.dataReserva == null || !this.dataReserva.equals(other.dataReserva))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (int) (this.clienteId ^ (this.clienteId >>> 32));
        hash = 83 * hash + (int) (this.quadraId ^ (this.quadraId >>> 32));
        hash = 83 * hash + (this.horaReserva != null ? this.horaReserva.hashCode() : 0);
        hash = 83 * hash + (this.dataReserva != null ? this.dataReserva.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "ReservaPK{" + "clienteId=" + clienteId + ", quadraId=" + quadraId + ", hora=" + horaReserva + ", dataReserva=" + dataReserva + '}';
    }
}
