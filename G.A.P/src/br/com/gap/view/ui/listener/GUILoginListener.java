/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gap.view.ui.listener;

import br.com.gap.view.factory.UsuarioBeanFactory;
import br.com.gap.view.ui.GUILogin;
import br.com.gap.view.ui.GUIMensagem;
import br.com.gap.view.util.FuncoesInterface;
import br.com.gap.view.util.Session;
import exceptions.UsuarioNaoEncontradoException;
import exceptions.UsuarioNovoException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.naming.NamingException;

/**
 *
 * @author mjustino
 */
public class GUILoginListener {

    private GUILogin guiLogin;

    public static void setListener(GUILogin guiLogin) {
        new GUILoginListener(guiLogin);
    }

    private GUILoginListener(GUILogin guiLogin) {
        this.guiLogin = guiLogin;
        this.guiLogin.btnEntrarActionPerformed(new GUILoginListenerEntrar());
    }

    class GUILoginListenerEntrar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                FuncoesInterface.setCursor(guiLogin, FuncoesInterface.TipoCursor.WAIT_CURSOR);

                //todo: substituir o método de obtenção da senha: txtSenha.getPassword().toString().trim());
                Session.usuarioLogado = UsuarioBeanFactory.login(
                        guiLogin.getLogin(),
                        guiLogin.getPassword());
                guiLogin.setVisible(false);
            } catch (NamingException | UsuarioNaoEncontradoException | UsuarioNovoException ex) {
                GUIMensagem.showMessage(ex);
            } catch (Exception ex) {
                GUIMensagem.showMessage(ex);
            } finally {
                FuncoesInterface.setCursor(guiLogin, FuncoesInterface.TipoCursor.DEFAULT_CURSOR);
            }
        }
    }
}