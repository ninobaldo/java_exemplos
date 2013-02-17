/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gap.view.ui;

import java.util.List;
import javax.swing.JPanel;
import org.swingBean.descriptor.TableFieldDescriptor;

/**
 *
 * @author mjustino
 */
public interface IGUISubCadastro<T> {

    JPanel getComponentesDadosCadastro() throws Exception;

    TableFieldDescriptor getTableDescriptorResultado() throws Exception;

    void limparComponentesDadosCadastro() throws Exception;

    void removerRegistro(T obj) throws Exception;

    void setObjetoDadosCadastro(T registroAtual) throws Exception;

    T getObjetoDadosCadastro(T registroAtual) throws Exception;

    List<T> getRegistrosExistentes() throws Exception;

    T adicionarRegistro(T obj) throws Exception;

    T alterarRegistro(T obj) throws Exception;
    
    String getTitulo();
}
