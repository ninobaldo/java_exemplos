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
    @NamedQuery(name = "Mensalista.findByDiaSemanaValidosEQuadra", query = "SELECT m FROM Mensalista m WHERE m.mensalistaPK.diaSemana = :diaSemana AND m.isAtivo = true AND m.mensalistaPK.quadraId = :quadraId"),
    @NamedQuery(name = "Mensalista.findByDiaSemanaValidos", query = "SELECT m FROM Mensalista m WHERE m.mensalistaPK.diaSemana = :diaSemana AND m.isAtivo = true"),
    @NamedQuery(name = "Mensalista.findMensalidade", query = "SELECT m FROM Mensalista m WHERE m.mensalistaPK.quadraId = :quadraId and m.mensalistaPK.horario = :horario and m.mensalistaPK.diaSemana = :diaSemana and m.isAtivo = true"),
    @NamedQuery(name = "Mensalista.findAll", query = "SELECT m FROM Mensalista m"),
    @NamedQuery(name = "Mensalista.findByPessoaId", query = "SELECT m FROM Mensalista m WHERE m.mensalistaPK.pessoaId = :pessoaId"),
    @NamedQuery(name = "Mensalista.findByQuadraId", query = "SELECT m FROM Mensalista m WHERE m.mensalistaPK.quadraId = :quadraId"),
    @NamedQuery(name = "Mensalista.findByDataCadastro", query = "SELECT m FROM Mensalista m WHERE m.dataCadastro = :dataCadastro"),
    @NamedQuery(name = "Mensalista.findByIsAtivo", query = "SELECT m FROM Mensalista m WHERE m.isAtivo = :isAtivo")})
public class Mensalista implements Serializable {

    @Transient
    public static final String FIND_BY_DIA_SEMANA_VALIDOS_E_QUADRA = "Mensalista.findByDiaSemanaValidosEQuadra";
    @Transient
    public static final String FIND_BY_DIA_SEMANA_VALIDOS = "Mensalista.findByDiaSemanaValidos";
    @Transient
    public static final String FIND_ALL = "Mensalista.findAll";
    @Transient
    public static final String FIND_BY_PESSOA_ID = "Mensalista.findByPessoaId";
    @Transient
    public static final String FIND_BY_QUADRA_ID = "Mensalista.findByQuadraId";
    @Transient
    public static final String FIND_BY_DATA_VIGENCIA = "Mensalista.findByDataVigencia";
    @Transient
    public static final String FIND_BY_DATA_CADASTRO = "Mensalista.findByDataCadastro";
    @Transient
    public static final String FIND_BY_PERIODO_MESES = "Mensalista.findByPeriodoMeses";
    @Transient
    public static final String FIND_BY_IS_ATIVO = "Mensalista.findByIsAtivo";
    
    public static final String FIND_MENSALIDADE = "Mensalista.findMensalidade";
    private static final long serialVersionUID = -6921926259916578361L;
    
    @EmbeddedId
    protected MensalistaPK mensalistaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_cadastro", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_ativo", nullable = false)
    private boolean isAtivo;
    @JoinColumn(name = "quadra_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Quadra quadra;
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pessoa pessoa;
    @Column(name = "data_fim_vigencia", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFimVigencia;

    //<editor-fold defaultstate="collapsed" desc="<<: Construtor :>>">
    public Mensalista() {
    }

    public Mensalista(MensalistaPK mensalistaPK) {
        this.mensalistaPK = mensalistaPK;
    }

    public Mensalista(MensalistaPK mensalistaPK, Date dataCadastro, Date dataFimVigencia, boolean isAtivo) {
        this.mensalistaPK = mensalistaPK;
        this.dataCadastro = dataCadastro;
        this.dataFimVigencia = dataFimVigencia;
        this.isAtivo = isAtivo;
    }

    public Mensalista(long pessoaId, long quadraId, Date horario, Date dataInicioVigencia, short diaSemana) {
        this.mensalistaPK = new MensalistaPK(pessoaId, quadraId, horario, dataInicioVigencia, diaSemana);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="<<: Get & Set :>>">
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

    public Date getDataFimVigencia() {
        return dataFimVigencia;
    }

    public void setDataFimVigencia(Date dataFimVigencia) {
        this.dataFimVigencia = dataFimVigencia;
    }

    public boolean getIsAtivo() {
        return isAtivo;
    }

    public void setIsAtivo(boolean isAtivo) {
        this.isAtivo = isAtivo;
    }

    public Quadra getQuadra() {
        return quadra;
    }

    public void setQuadra(Quadra quadra) {
        this.quadra = quadra;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="<<: Override :>>">
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mensalistaPK != null ? mensalistaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
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
    //</editor-fold>
}