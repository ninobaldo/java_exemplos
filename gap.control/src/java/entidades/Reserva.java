/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author avsilva
 */
@Entity
@Table(name = "reserva", catalog = "k22", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reserva.findOcupadasByData", query = "SELECT r FROM Reserva r where r.reservaPK.dataReserva = :dataReserva"),
    @NamedQuery(name = "Reserva.findAll", query = "SELECT r FROM Reserva r"),
    @NamedQuery(name = "Reserva.findByClienteId", query = "SELECT r FROM Reserva r WHERE r.reservaPK.clienteId = :clienteId"),
    @NamedQuery(name = "Reserva.findByQuadraId", query = "SELECT r FROM Reserva r WHERE r.reservaPK.quadraId = :quadraId"),
    @NamedQuery(name = "Reserva.findByHora", query = "SELECT r FROM Reserva r WHERE r.reservaPK.horaReserva = :horaReserva"),
    @NamedQuery(name = "Reserva.findByDataCadastro", query = "SELECT r FROM Reserva r WHERE r.dataCadastro = :dataCadastro"),
    @NamedQuery(name = "Reserva.findByObservacao", query = "SELECT r FROM Reserva r WHERE r.observacao = :observacao")})
public class Reserva implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReservaPK reservaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_cadastro", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;
    @Size(max = 150)
    @Column(name = "observacao", length = 150)
    private String observacao;
    @Transient
    private Date horaFim;

    public Reserva() {
    }

    public Reserva(ReservaPK reservaPK) {
        this.reservaPK = reservaPK;
    }

    public Reserva(ReservaPK reservaPK, Date dataCadastro) {
        this.reservaPK = reservaPK;
        this.dataCadastro = dataCadastro;
    }

    public Reserva(long clienteId, long quadraId, Date horaReserva, Date dataReserva) {
        this.reservaPK = new ReservaPK(clienteId, quadraId, horaReserva, dataReserva);
    }

    public ReservaPK getReservaPK() {
        return reservaPK;
    }

    public void setReservaPK(ReservaPK reservaPK) {
        this.reservaPK = reservaPK;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(Date horaFim) {
        this.horaFim = horaFim;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservaPK != null ? reservaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Reserva)) {
            return false;
        }
        Reserva other = (Reserva) object;
        if ((this.reservaPK == null && other.reservaPK != null) || (this.reservaPK != null && !this.reservaPK.equals(other.reservaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Reserva[ reservaPK=" + reservaPK + " ]";
    }
    
}
