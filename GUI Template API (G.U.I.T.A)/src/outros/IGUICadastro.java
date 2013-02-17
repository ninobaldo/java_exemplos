/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package outros;

import java.util.List;
import org.swingBean.descriptor.GenericFieldDescriptor;
import org.swingBean.descriptor.TableFieldDescriptor;

/**
 *
 * @author mjustino
 */
public interface IGUICadastro<T> {
    
    T adicionarRegistro(T obj) throws Exception;

    T alterarRegistro(T obj) throws Exception;

    String[] getPesquisaPropertiesToChanged();

    GenericFieldDescriptor getDescriptorManutencao();

    GenericFieldDescriptor getDescriptorPesquisa();

    TableFieldDescriptor getTableDescriptorResultado();

    List<T> pesquisar() throws Exception;

    void removerRegistro(T obj) throws Exception;
    
    String getTitulo();
}
