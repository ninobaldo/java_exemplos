/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package outros;

import java.util.List;
import javax.swing.JPanel;
import org.swingBean.descriptor.TableFieldDescriptor;

/**
 *
 * @author mjustino
 */
public interface IGUIListagem<T> {
    JPanel getComponentesDadosCadastro() throws Exception;

    TableFieldDescriptor getTableDescriptorResultado() throws Exception;

    void limparComponentesDadosCadastro() throws Exception;

    List<T> getRegistrosExistentes() throws Exception;
    
    void getDependentesListagem() throws Exception;

    String getTitulo();
    
    void callActionSelecionarRegistro(T obj) throws Exception;
}
