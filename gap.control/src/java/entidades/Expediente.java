/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import enums.DiaSemana;
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
@Table(name = "expediente", catalog = "k22", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Expediente.findVigenciaAtual", query = "SELECT e FROM Expediente e WHERE e.vigencia = (SELECT MAX(e2.vigencia) FROM Expediente e2 WHERE e2.vigencia < CURRENT_TIMESTAMP AND e2.diaSemana = :dia_semana) AND e.diaSemana = :dia_semana"),
    @NamedQuery(name = "Expediente.findVigenciasValidas", query = "SELECT e FROM Expediente e WHERE e.vigencia = (SELECT MAX(e2.vigencia) FROM Expediente e2 WHERE e2.diaSemana = :dia_semana) AND e.diaSemana = :dia_semana"),
    @NamedQuery(name = "Expediente.findAll", query = "SELECT e FROM Expediente e"),
    @NamedQuery(name = "Expediente.findById", query = "SELECT e FROM Expediente e WHERE e.id = :id"),
    @NamedQuery(name = "Expediente.findByInicioExpediente", query = "SELECT e FROM Expediente e WHERE e.inicioExpediente = :inicioExpediente"),
    @NamedQuery(name = "Expediente.findByFimExpediente", query = "SELECT e FROM Expediente e WHERE e.fimExpediente = :fimExpediente"),
    @NamedQuery(name = "Expediente.findByPeriodoBaseMinutos", query = "SELECT e FROM Expediente e WHERE e.periodoBaseMinutos = :periodoBaseMinutos"),
    @NamedQuery(name = "Expediente.findByVigencia", query = "SELECT e FROM Expediente e WHERE e.vigencia = :vigencia"),    
    @NamedQuery(name = "Expediente.findByDiaSemana", query = "SELECT e FROM Expediente e WHERE e.diaSemana = :diaSemana")})
public class Expediente implements Serializable {
    private static final long serialVersionUID = -7063219449808747074L;
    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "inicio_expediente", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date inicioExpediente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fim_expediente", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date fimExpediente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "periodo_base_minutos", nullable = false)
    private Short periodoBaseMinutos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vigencia", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date vigencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dia_semana", nullable = false)
    private Short diaSemana;
    @Transient
    private String diaSemanaExtenso;

    public Expediente() {
    }

    public Expediente(Integer id) {
        this.id = id;
    }

    public Expediente(Integer id, Date inicioExpediente, Date fimExpediente, short periodoBaseMinutos, Date vigencia, short diaSemana) {
        this.id = id;
        this.inicioExpediente = inicioExpediente;
        this.fimExpediente = fimExpediente;
        this.periodoBaseMinutos = periodoBaseMinutos;
        this.vigencia = vigencia;
        this.diaSemana = diaSemana;
    }

    public String getDiaSemanaExtenso() {
        diaSemanaExtenso = DiaSemana.getNome(diaSemana);
        return diaSemanaExtenso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInicioExpediente() {
        return inicioExpediente;
    }

    public void setInicioExpediente(Date inicioExpediente) {
        this.inicioExpediente = inicioExpediente;
    }

    public Date getFimExpediente() {
        return fimExpediente;
    }

    public void setFimExpediente(Date fimExpediente) {
        this.fimExpediente = fimExpediente;
    }

    public Short getPeriodoBaseMinutos() {
        return periodoBaseMinutos;
    }

    public void setPeriodoBaseMinutos(short periodoBaseMinutos) {
        this.periodoBaseMinutos = periodoBaseMinutos;
    }

    public Date getVigencia() {
        return vigencia;
    }

    public void setVigencia(Date vigencia) {
        this.vigencia = vigencia;
    }

    public Short getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(Short diaSemana) {
        this.diaSemana = diaSemana;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Expediente)) {
            return false;
        }
        Expediente other = (Expediente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Expediente[ id=" + id + " ]";
    }
}
