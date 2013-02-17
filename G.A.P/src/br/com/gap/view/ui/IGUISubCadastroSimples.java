/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gap.view.ui;

import javax.swing.JPanel;

/**
 *
 * @author mjustino
 */
public interface IGUISubCadastroSimples<T> {

    JPanel getComponentesDadosCadastro() throws Exception;

    void limparComponentesDadosCadastro() throws Exception;

    T setDadosObjeto();

    T salvarDados(T obj) throws Exception;

    String getTitulo();
}
