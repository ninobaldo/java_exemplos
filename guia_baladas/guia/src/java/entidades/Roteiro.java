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
@Table(name = "roteiro", catalog = "guia", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Roteiro.findAll", query = "SELECT r FROM Roteiro r"),
    @NamedQuery(name = "Roteiro.findById", query = "SELECT r FROM Roteiro r WHERE r.id = :id"),
    @NamedQuery(name = "Roteiro.findByHora", query = "SELECT r FROM Roteiro r WHERE r.hora = :hora"),
    @NamedQuery(name = "Roteiro.findByDescricao", query = "SELECT r FROM Roteiro r WHERE r.descricao = :descricao")})
public class Roteiro implements Serializable {
    private static final long serialVersionUID = 8707106535679619738L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date hora;
    @Size(max = 100)
    @Column(name = "descricao", length = 100)
    private String descricao;
    @JoinColumn(name = "Lugar_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Lugar lugarid;

    public Roteiro() {
    }

    public Roteiro(Integer id) {
        this.id = id;
    }

    public Roteiro(Integer id, Date hora) {
        this.id = id;
        this.hora = hora;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Lugar getLugarid() {
        return lugarid;
    }

    public void setLugarid(Lugar lugarid) {
        this.lugarid = lugarid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roteiro)) {
            return false;
        }
        Roteiro other = (Roteiro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Roteiro[ id=" + id + " ]";
    }
    
}
