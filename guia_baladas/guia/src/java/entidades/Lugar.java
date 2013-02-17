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
@Table(name = "lugar", catalog = "guia", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lugar.findAll", query = "SELECT l FROM Lugar l"),
    @NamedQuery(name = "Lugar.findById", query = "SELECT l FROM Lugar l WHERE l.id = :id"),
    @NamedQuery(name = "Lugar.findByNome", query = "SELECT l FROM Lugar l WHERE l.nome = :nome"),
    @NamedQuery(name = "Lugar.findByDescricao", query = "SELECT l FROM Lugar l WHERE l.descricao = :descricao"),
    @NamedQuery(name = "Lugar.findByAbertura", query = "SELECT l FROM Lugar l WHERE l.abertura = :abertura"),
    @NamedQuery(name = "Lugar.findByFechamento", query = "SELECT l FROM Lugar l WHERE l.fechamento = :fechamento")})
public class Lugar implements Serializable {
    private static final long serialVersionUID = 8096515273730701973L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 45)
    @Column(name = "nome", length = 45)
    private String nome;
    @Size(max = 150)
    @Column(name = "descricao", length = 150)
    private String descricao;
    @Column(name = "abertura")
    @Temporal(TemporalType.TIME)
    private Date abertura;
    @Column(name = "fechamento")
    @Temporal(TemporalType.TIME)
    private Date fechamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lugarid")
    private List<Cardapio> cardapioList;
    @JoinColumn(name = "Endereco_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Endereco enderecoid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lugarid")
    private List<Cartaz> cartazList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lugarid")
    private List<Roteiro> roteiroList;

    public Lugar() {
    }

    public Lugar(Integer id) {
        this.id = id;
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

    public Date getAbertura() {
        return abertura;
    }

    public void setAbertura(Date abertura) {
        this.abertura = abertura;
    }

    public Date getFechamento() {
        return fechamento;
    }

    public void setFechamento(Date fechamento) {
        this.fechamento = fechamento;
    }

    @XmlTransient
    public List<Cardapio> getCardapioList() {
        return cardapioList;
    }

    public void setCardapioList(List<Cardapio> cardapioList) {
        this.cardapioList = cardapioList;
    }

    public Endereco getEnderecoid() {
        return enderecoid;
    }

    public void setEnderecoid(Endereco enderecoid) {
        this.enderecoid = enderecoid;
    }

    @XmlTransient
    public List<Cartaz> getCartazList() {
        return cartazList;
    }

    public void setCartazList(List<Cartaz> cartazList) {
        this.cartazList = cartazList;
    }

    @XmlTransient
    public List<Roteiro> getRoteiroList() {
        return roteiroList;
    }

    public void setRoteiroList(List<Roteiro> roteiroList) {
        this.roteiroList = roteiroList;
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
        if (!(object instanceof Lugar)) {
            return false;
        }
        Lugar other = (Lugar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Lugar[ id=" + id + " ]";
    }
    
}
