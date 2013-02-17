/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author avsilva
 */
@Entity
@Table(name = "mensalista", catalog = "k22", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mensalista.findAll", query = "SELECT m FROM Mensalista m"),
    @NamedQuery(name = "Mensalista.findByQuadraId", query = "SELECT m FROM Mensalista m WHERE m.mensalistaPK.quadraId = :quadraId"),
    @NamedQuery(name = "Mensalista.findByPessoaId", query = "SELECT m FROM Mensalista m WHERE m.mensalistaPK.pessoaId = :pessoaId"),
    @NamedQuery(name = "Mensalista.findById", query = "SELECT m FROM Mensalista m WHERE m.mensalistaPK.id = :id"),
    @NamedQuery(name = "Mensalista.findByDataCadastro", query = "SELECT m FROM Mensalista m WHERE m.dataCadastro = :dataCadastro"),
    @NamedQuery(name = "Mensalista.findByDataVigenciaInicio", query = "SELECT m FROM Mensalista m WHERE m.dataVigenciaInicio = :dataVigenciaInicio"),
    @NamedQuery(name = "Mensalista.findByDataVigenciaFim", query = "SELECT m FROM Mensalista m WHERE m.dataVigenciaFim = :dataVigenciaFim"),
    @NamedQuery(name = "Mensalista.findByDiaSemana", query = "SELECT m FROM Mensalista m WHERE m.diaSemana = :diaSemana"),
    @NamedQuery(name = "Mensalista.findByIsAtivo", query = "SELECT m FROM Mensalista m WHERE m.isAtivo = :isAtivo")})
public class Mensalista implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MensalistaPK mensalistaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_cadastro", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_vigencia_inicio", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataVigenciaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_vigencia_fim", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataVigenciaFim;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dia_semana", nullable = false)
    private short diaSemana;
    @Column(name = "is_ativo")
    private Boolean isAtivo;
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pessoa pessoa;
    @JoinColumn(name = "quadra_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Quadra quadra;

    public Mensalista() {
    }

    public Mensalista(MensalistaPK mensalistaPK) {
        this.mensalistaPK = mensalistaPK;
    }

    public Mensalista(MensalistaPK mensalistaPK, Date dataCadastro, Date dataVigenciaInicio, Date dataVigenciaFim, short diaSemana) {
        this.mensalistaPK = mensalistaPK;
        this.dataCadastro = dataCadastro;
        this.dataVigenciaInicio = dataVigenciaInicio;
        this.dataVigenciaFim = dataVigenciaFim;
        this.diaSemana = diaSemana;
    }

    public Mensalista(long quadraId, long pessoaId, long id) {
        this.mensalistaPK = new MensalistaPK(quadraId, pessoaId, id);
    }

    public MensalistaPK getMensalistaPK() {
        return mensalistaPK;
    }

    public void setMensalistaPK(MensalistaPK mensalistaPK) {
        this.mensalistaPK = mensalistaPK;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataVigenciaInicio() {
        return dataVigenciaInicio;
    }

    public void setDataVigenciaInicio(Date dataVigenciaInicio) {
        this.dataVigenciaInicio = dataVigenciaInicio;
    }

    public Date getDataVigenciaFim() {
        return dataVigenciaFim;
    }

    public void setDataVigenciaFim(Date dataVigenciaFim) {
        this.dataVigenciaFim = dataVigenciaFim;
    }

    public short getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(short diaSemana) {
        this.diaSemana = diaSemana;
    }

    public Boolean getIsAtivo() {
        return isAtivo;
    }

    public void setIsAtivo(Boolean isAtivo) {
        this.isAtivo = isAtivo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
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
        hash += (mensalistaPK != null ? mensalistaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mensalista)) {
            return false;
        }
        Mensalista other = (Mensalista) object;
        if ((this.mensalistaPK == null && other.mensalistaPK != null) || (this.mensalistaPK != null && !this.mensalistaPK.equals(other.mensalistaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Mensalista[ mensalistaPK=" + mensalistaPK + " ]";
    }
}
