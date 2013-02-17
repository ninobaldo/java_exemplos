/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.svbr.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author avsilva
 */
@Entity
public class Usuario implements Serializable {
    private static final long serialVersionUID = -433731354448787302L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String login;
    private String senha;
    @ManyToOne
    private GrupoUsuario grupoUsuario;
    @ManyToMany
    private Set<Permissao> permissoes;
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimoAlteracaoSenha;

    public Usuario() {
    }
    
    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    //<editor-fold defaultstate="collapsed" desc="<<: Get e Set :>>">
    public Date getUltimoAlteracaoSenha() {
        return ultimoAlteracaoSenha;
    }
    
    public void setUltimoAlteracaoSenha(Date ultimoAlteracaoSenha) {
        this.ultimoAlteracaoSenha = ultimoAlteracaoSenha;
    }
    
    public GrupoUsuario getGrupoUsuario() {
        return grupoUsuario;
    }
    
    public void setGrupoUsuario(GrupoUsuario grupoUsuario) {
        this.grupoUsuario = grupoUsuario;
    }
    
    public Set<Permissao> getPermissoes() {
        return permissoes;
    }
    
    public void setPermissoes(Set<Permissao> permissoes) {
        this.permissoes = permissoes;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getLogin() {
        return login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public Situacao getSituacao() {
        return situacao;
    }
    
    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }
    private Situacao situacao;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="<<: Override :>>">
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "br.com.svbr.model.Usuario[ id=" + id + " ]";
    }
    //</editor-fold>
}
