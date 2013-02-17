/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author avsilva
 */
@Entity
@Table(name = "cartaz", catalog = "guia", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cartaz.findAll", query = "SELECT c FROM Cartaz c"),
    @NamedQuery(name = "Cartaz.findById", query = "SELECT c FROM Cartaz c WHERE c.id = :id"),
    @NamedQuery(name = "Cartaz.findByNome", query = "SELECT c FROM Cartaz c WHERE c.nome = :nome"),
    @NamedQuery(name = "Cartaz.findByVigenciaInicio", query = "SELECT c FROM Cartaz c WHERE c.vigenciaInicio = :vigenciaInicio"),
    @NamedQuery(name = "Cartaz.findByVigenciaFim", query = "SELECT c FROM Cartaz c WHERE c.vigenciaFim = :vigenciaFim")})
public class Cartaz implements Serializable {
    private static final long serialVersionUID = 6456837795674072823L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    private Long id;
    @Size(max = 45)
    @Column(name = "nome", length = 45)
    private String nome;
    @Column(name = "vigencia_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vigenciaInicio;
    @Column(name = "vigencia_fim")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vigenciaFim;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cartazid")
    private List<Horario> horarioList;
    @JoinColumn(name = "Lugar_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Lugar lugarid;

    public Cartaz() {
    }

    public Cartaz(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getVigenciaInicio() {
        return vigenciaInicio;
    }

    public void setVigenciaInicio(Date vigenciaInicio) {
        this.vigenciaInicio = vigenciaInicio;
    }

    public Date getVigenciaFim() {
        return vigenciaFim;
    }

    public void setVigenciaFim(Date vigenciaFim) {
        this.vigenciaFim = vigenciaFim;
    }

    @XmlTransient
    public List<Horario> getHorarioList() {
        return horarioList;
    }

    public void setHorarioList(List<Horario> horarioList) {
        this.horarioList = horarioList;
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
        if (!(object instanceof Cartaz)) {
            return false;
        }
        Cartaz other = (Cartaz) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Cartaz[ id=" + id + " ]";
    }
    
}
