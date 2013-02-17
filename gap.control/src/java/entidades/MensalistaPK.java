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
public class MensalistaPK implements Serializable {
    private static final long serialVersionUID = -4788524660048180461L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pessoa_id", nullable = false)
    private long pessoaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quadra_id", nullable = false)
    private long quadraId;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private Date horario;
    @Column(name = "data_inicio_vigencia", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInicioVigencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dia_semana", nullable = false)
    private short diaSemana;

    public MensalistaPK() {
    }

    public MensalistaPK(long pessoaId, long quadraId, Date horario, Date dataInicioVigencia, short diaSemana) {
        this.pessoaId = pessoaId;
        this.quadraId = quadraId;
        this.horario = horario;
        this.dataInicioVigencia = dataInicioVigencia;
        this.diaSemana = diaSemana;
    }
    
    public long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(long pessoaId) {
        this.pessoaId = pessoaId;
    }

    public long getQuadraId() {
        return quadraId;
    }

    public void setQuadraId(long quadraId) {
        this.quadraId = quadraId;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public Date getDataInicioVigencia() {
        return dataInicioVigencia;
    }

    public void setDataInicioVigencia(Date dataVigencia) {
        this.dataInicioVigencia = dataVigencia;
    }

    public short getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(short diaSemana) {
        this.diaSemana = diaSemana;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (int) (this.pessoaId ^ (this.pessoaId >>> 32));
        hash = 89 * hash + (int) (this.quadraId ^ (this.quadraId >>> 32));
        hash = 89 * hash + (this.horario != null ? this.horario.hashCode() : 0);
        hash = 89 * hash + (this.dataInicioVigencia != null ? this.dataInicioVigencia.hashCode() : 0);
        hash = 89 * hash + this.diaSemana;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MensalistaPK other = (MensalistaPK) obj;
        if (this.pessoaId != other.pessoaId) {
            return false;
        }
        if (this.quadraId != other.quadraId) {
            return false;
        }
        if (this.horario != other.horario && (this.horario == null || !this.horario.equals(other.horario))) {
            return false;
        }
        if (this.dataInicioVigencia != other.dataInicioVigencia && (this.dataInicioVigencia == null || !this.dataInicioVigencia.equals(other.dataInicioVigencia))) {
            return false;
        }
        if (this.diaSemana != other.diaSemana) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MensalistaPK{" + "pessoaId=" + pessoaId + ", quadraId=" + quadraId + ", horario=" + horario + ", dataVigencia=" + dataInicioVigencia + ", diaSemana=" + diaSemana + '}';
    }
}
