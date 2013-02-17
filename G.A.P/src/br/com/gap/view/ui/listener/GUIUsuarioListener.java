/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gap.view.ui.listener;

import br.com.gap.view.ui.usuario.GUIUsuario;

/**
 *
 * @author mjustino
 */
public class GUIUsuarioListener {

    private GUIUsuario gui;

    public static void setListener(GUIUsuario gui) {
        new GUIUsuarioListener(gui);
    }

    private GUIUsuarioListener(GUIUsuario gui) {
        this.gui = gui;
//        this.gui.btnPLocalizarActionPerformed(new GUIUserListenerLocalizar());
//        this.gui.btnIncluirNovoRegistroActionPerformed(new GUIUserListenerIncluirNovoRegistro());
//        this.gui.btnRemoverRegistroActionPerformed(new GUIUserListenerRemoverRegistro());
    }
//    class GUIUserListenerLocalizar implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent ae) {
//            try {
//                GUIFuncoes.setCursor(gui, GUIFuncoes.TipoCursor.WAIT_CURSOR);
//
//                List<Usuario> usuarios = UsuarioBeanFactory.findByLoginOrNome(
//                        gui.getUserLogin(),
//                        gui.getUserName());
//
//                gui.carregarRegistrosEncontrados(usuarios);
//
//            } catch (Exception ex) {
//                Logger.getLogger(GUIUsuarioListener.class.getName()).log(Level.SEVERE, null, ex);
//                GUIMensagem.showMessage(ex);
//            } finally {
//                GUIFuncoes.setCursor(gui, GUIFuncoes.TipoCursor.DEFAULT_CURSOR);
//            }
//        }
//    }
}
