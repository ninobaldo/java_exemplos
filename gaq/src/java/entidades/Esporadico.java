/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author avsilva
 */
@Entity
@Table(name = "esporadico", catalog = "k22", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Esporadico.findAll", query = "SELECT e FROM Esporadico e"),
    @NamedQuery(name = "Esporadico.findByQuadraId", query = "SELECT e FROM Esporadico e WHERE e.esporadicoPK.quadraId = :quadraId"),
    @NamedQuery(name = "Esporadico.findByClienteId", query = "SELECT e FROM Esporadico e WHERE e.esporadicoPK.clienteId = :clienteId"),
    @NamedQuery(name = "Esporadico.findByData", query = "SELECT e FROM Esporadico e WHERE e.esporadicoPK.data = :data"),
    @NamedQuery(name = "Esporadico.findByObservacao", query = "SELECT e FROM Esporadico e WHERE e.observacao = :observacao")})
public class Esporadico implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EsporadicoPK esporadicoPK;
    @Size(max = 150)
    @Column(name = "observacao", length = 150)
    private String observacao;
    @JoinColumn(name = "cliente_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cliente cliente;
    @JoinColumn(name = "quadra_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Quadra quadra;

    public Esporadico() {
    }

    public Esporadico(EsporadicoPK esporadicoPK) {
        this.esporadicoPK = esporadicoPK;
    }

    public Esporadico(long quadraId, long clienteId, Date data) {
        this.esporadicoPK = new EsporadicoPK(quadraId, clienteId, data);
    }

    public EsporadicoPK getEsporadicoPK() {
        return esporadicoPK;
    }

    public void setEsporadicoPK(EsporadicoPK esporadicoPK) {
        this.esporadicoPK = esporadicoPK;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Quadra getQuadra() {
        return quadra;
    }

    public void setQuadra(Quadra quadra) {
        this.quadra = quadra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (esporadicoPK != null ? esporadicoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Esporadico)) {
            return false;
        }
        Esporadico other = (Esporadico) object;
        if ((this.esporadicoPK == null && other.esporadicoPK != null) || (this.esporadicoPK != null && !this.esporadicoPK.equals(other.esporadicoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Esporadico[ esporadicoPK=" + esporadicoPK + " ]";
    }
    
}
