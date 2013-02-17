/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author avsilva
 */
@Entity
@Table(name = "responsavel_cliente", catalog = "k22", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"responsaveis_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResponsavelCliente.findAll", query = "SELECT r FROM ResponsavelCliente r"),
    @NamedQuery(name = "ResponsavelCliente.findByClienteid", query = "SELECT r FROM ResponsavelCliente r WHERE r.responsavelClientePK.clienteid = :clienteid"),
    @NamedQuery(name = "ResponsavelCliente.findByResponsaveisId", query = "SELECT r FROM ResponsavelCliente r WHERE r.responsavelClientePK.responsaveisId = :responsaveisId")})
public class ResponsavelCliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ResponsavelClientePK responsavelClientePK;
    @JoinColumn(name = "Cliente_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cliente cliente;
    @JoinColumn(name = "responsaveis_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Cliente cliente1;

    public ResponsavelCliente() {
    }

    public ResponsavelCliente(ResponsavelClientePK responsavelClientePK) {
        this.responsavelClientePK = responsavelClientePK;
    }

    public ResponsavelCliente(long clienteid, long responsaveisId) {
        this.responsavelClientePK = new ResponsavelClientePK(clienteid, responsaveisId);
    }

    public ResponsavelClientePK getResponsavelClientePK() {
        return responsavelClientePK;
    }

    public void setResponsavelClientePK(ResponsavelClientePK responsavelClientePK) {
        this.responsavelClientePK = responsavelClientePK;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente1() {
        return cliente1;
    }

    public void setCliente1(Cliente cliente1) {
        this.cliente1 = cliente1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (responsavelClientePK != null ? responsavelClientePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResponsavelCliente)) {
            return false;
        }
        ResponsavelCliente other = (ResponsavelCliente) object;
        if ((this.responsavelClientePK == null && other.responsavelClientePK != null) || (this.responsavelClientePK != null && !this.responsavelClientePK.equals(other.responsavelClientePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ResponsavelCliente[ responsavelClientePK=" + responsavelClientePK + " ]";
    }
    
}
