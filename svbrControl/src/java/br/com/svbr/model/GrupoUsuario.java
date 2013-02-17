/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.svbr.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author avsilva
 */
@Entity
public class GrupoUsuario implements Serializable {

    private static final long serialVersionUID = 7987039661795841126L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(mappedBy = "grupoUsuario")
    private List<Usuario> usuarios;
    @ManyToMany
    private Set<Permissao> permissoes;

    //<editor-fold defaultstate="collapsed" desc="<<: Get e Sets :>>">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(Set<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
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
        if (!(object instanceof GrupoUsuario)) {
            return false;
        }
        GrupoUsuario other = (GrupoUsuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.svbr.model.GrupoUsuario[ id=" + id + " ]";
    }
    //</editor-fold>
}
