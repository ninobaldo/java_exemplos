/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author avsilva
 */
@Entity
@Table(name = "cardapio", catalog = "guia", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cardapio.findAll", query = "SELECT c FROM Cardapio c"),
    @NamedQuery(name = "Cardapio.findById", query = "SELECT c FROM Cardapio c WHERE c.id = :id"),
    @NamedQuery(name = "Cardapio.findByNome", query = "SELECT c FROM Cardapio c WHERE c.nome = :nome"),
    @NamedQuery(name = "Cardapio.findByDescricao", query = "SELECT c FROM Cardapio c WHERE c.descricao = :descricao"),
    @NamedQuery(name = "Cardapio.findByPreco", query = "SELECT c FROM Cardapio c WHERE c.preco = :preco")})
public class Cardapio implements Serializable {
    private static final long serialVersionUID = -21908867278205083L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nome", nullable = false, length = 45)
    private String nome;
    @Size(max = 150)
    @Column(name = "descricao", length = 150)
    private String descricao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "preco", precision = 7, scale = 2)
    private BigDecimal preco;
    @JoinColumn(name = "Lugar_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Lugar lugarid;

    public Cardapio() {
    }

    public Cardapio(Integer id) {
        this.id = id;
    }

    public Cardapio(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
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
        if (!(object instanceof Cardapio)) {
            return false;
        }
        Cardapio other = (Cardapio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Cardapio[ id=" + id + " ]";
    }
    
}
