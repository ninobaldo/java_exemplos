/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans.repository;

import entidades.Usuario;
import enums.SituacaoUsuario;
import exceptions.SituacaoUsuarioInvalidaException;
import exceptions.UsuarioJaExistenteException;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import security.HashFactory;

/**
 *
 * @author avsilva
 */
@Stateless
@Remote(UsuarioRepositoryRemote.class)
public class UsuarioRepository implements UsuarioRepositoryRemote {
//@todo fazer validações
    @PersistenceContext
    EntityManager manager;

    @Override
    public Usuario adicionar(Usuario usuario) throws UsuarioJaExistenteException {
        validaUsuario(usuario);

        TypedQuery<Usuario> query = manager.createNamedQuery("Usuario.findByLogin", Usuario.class);

        query.setParameter("login", usuario.getLogin());

        Usuario u = null;

        try {
            u = query.getSingleResult();
        } catch (NoResultException e) {
        }

        if (usuario.getId() != null || u != null) {
            throw new UsuarioJaExistenteException();
        }

        usuario.setSenha(HashFactory.gerarHash("123gap", HashFactory.AlgoritmoHash.SHA512));
        usuario.setSituacao(SituacaoUsuario.NOVO.toString());
        usuario.setUltimaTroca(new Date());

        manager.persist(usuario);
        return usuario;
    }

    private void validaUsuario(Usuario usuario) throws IllegalArgumentException, NullPointerException {
        if (usuario == null) {
            throw new NullPointerException("Usuário está nulo.");
        }

        if (usuario.getLogin() == null || usuario.getLogin().trim().equals("")) {
            throw new IllegalArgumentException("login de usuário está nulo ou vazio.");
        }
    }

    @Override
    public void remover(Usuario usuario) {
        manager.remove(manager.merge(usuario));
    }

    @Override
    public Usuario alterar(Usuario usuario) throws SituacaoUsuarioInvalidaException {

        if (!SituacaoUsuario.existe(usuario.getSituacao())) {
            throw new SituacaoUsuarioInvalidaException();
        }

        return manager.merge(usuario);
    }

    @Override
    public Usuario findById(Long id) {
        return this.manager.find(Usuario.class, id);
    }

    @Override
    public List<Usuario> findAll() {
        TypedQuery<Usuario> query = this.manager.createNamedQuery(
                "Usuario.findAll", Usuario.class);
        return query.getResultList();
    }

    @Override
    public List<Usuario> findByLoginOrNome(String login, String nome) {
        TypedQuery<Usuario> query = this.manager.createNamedQuery(
                "Usuario.findByLoginOrNome", Usuario.class);
        login = login == null || login.trim().equals("") ? login : "%" + login + "%";
        nome = nome == null || nome.trim().equals("") ? nome : "%" + nome + "%";

        query.setParameter("login", login);
        query.setParameter("nome", nome);

        return query.getResultList();
    }
}
