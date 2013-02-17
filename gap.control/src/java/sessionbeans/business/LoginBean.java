/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans.business;

import entidades.Usuario;
import enums.SituacaoUsuario;
import exceptions.UsuarioInativoException;
import exceptions.UsuarioNaoEncontradoException;
import exceptions.UsuarioNovoException;
import java.util.Date;
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
@Remote(LoginRemote.class)
public class LoginBean implements LoginRemote {

    @PersistenceContext
    EntityManager manager;

    @Override
    public Usuario login(String usuario, String senha) throws UsuarioNaoEncontradoException, UsuarioNovoException, UsuarioInativoException {

        try {
            TypedQuery<Usuario> query = manager.createNamedQuery("Usuario.login", Usuario.class);
            query.setParameter("login", usuario);
            query.setParameter("senha", HashFactory.gerarHash(senha, HashFactory.AlgoritmoHash.SHA512));

            Usuario u = query.getSingleResult();

            if (u.getSituacao().equals(SituacaoUsuario.NOVO.toString())) {
                throw new UsuarioNovoException();
            }
            else if (u.getSituacao().equals(SituacaoUsuario.INATIVO.toString())){
                throw new UsuarioInativoException();
            }

            return u;
        } catch (NoResultException e) {
            throw new UsuarioNaoEncontradoException();
        }
    }

    @Override
    public void logout(Usuario usuario) {
        //@todo fazer logout
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void trocarSenha(String usuario, String senhaAntiga, String senhaNova) throws UsuarioNaoEncontradoException {
        try {
            TypedQuery<Usuario> query = manager.createNamedQuery("Usuario.login", Usuario.class);
            query.setParameter("login", usuario);
            query.setParameter("senha", HashFactory.gerarHash(senhaAntiga, HashFactory.AlgoritmoHash.SHA512));

            Usuario u = query.getSingleResult();

            u.setSenha(HashFactory.gerarHash(senhaNova, HashFactory.AlgoritmoHash.SHA512));
            u.setSituacao(SituacaoUsuario.ATIVO.toString());
            u.setUltimaTroca(new Date());
            manager.merge(u);

        } catch (NoResultException e) {
            throw new UsuarioNaoEncontradoException("O usuário ou a senha antiga estão erradas!");
        }
    }
}