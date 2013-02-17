/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gap.view.ui.usuario;

import br.com.gap.view.factory.UsuarioBeanFactory;
import br.com.gap.view.ui.GUICadastro;
import entidades.Usuario;
import java.util.List;
import org.swingBean.descriptor.GenericFieldDescriptor;
import org.swingBean.descriptor.TableFieldDescriptor;
import org.swingBean.descriptor.XMLDescriptorFactory;

/**
 *
 * @author mjustino
 */
public class GUIUsuario extends GUICadastro<Usuario> {

    public GUIUsuario() throws InstantiationException, IllegalAccessException {
        super(Usuario.class);
    }

    @Override
    public Usuario adicionarRegistro(Usuario obj) throws Exception {
        return UsuarioBeanFactory.adicionar(obj);
    }

    @Override
    public Usuario alterarRegistro(Usuario obj) throws Exception {
        return UsuarioBeanFactory.alterar(obj);
    }

    @Override
    public GenericFieldDescriptor getDescriptorManutencao() {
        GenericFieldDescriptor descriptor = XMLDescriptorFactory.getFieldDescriptor(Usuario.class, "br\\com\\gap\\view\\ui\\usuario\\UsuarioManutencao_xmldescriptor.xml", "UsuarioManutencao_xmldescriptor");
        return descriptor;
    }

    @Override
    public TableFieldDescriptor getTableDescriptorResultado() {
        TableFieldDescriptor descriptor = XMLDescriptorFactory.getTableFieldDescriptor(Usuario.class, "br\\com\\gap\\view\\ui\\usuario\\UsuarioResultado_xmldescriptor.xml", "UsuarioResultado_xmldescriptor");
        return descriptor;
    }

    @Override
    public GenericFieldDescriptor getDescriptorPesquisa() {
        GenericFieldDescriptor descriptor = XMLDescriptorFactory.getFieldDescriptor(Usuario.class, "br\\com\\gap\\view\\ui\\usuario\\UsuarioPesquisa_xmldescriptor.xml", "UsuarioPesquisa_xmldescriptor");
        return descriptor;
    }

    @Override
    public List<Usuario> pesquisar() throws Exception {
        List<Usuario> registrosEncontrados = UsuarioBeanFactory.findByLoginOrNome(
                pnlPesquisa.getPropertyValue("login").toString(),
                pnlPesquisa.getPropertyValue("nome").toString());
        return registrosEncontrados;
    }

    @Override
    public void removerRegistro(Usuario obj) throws Exception {
        UsuarioBeanFactory.remover(obj);
    }

    @Override
    public String[] getPesquisaPropertiesToChanged() {
        return new String[]{"nome", "login"};
    }

    @Override
    public String getTitulo() {
        return "Cadastro de Usuários";
    }
}
