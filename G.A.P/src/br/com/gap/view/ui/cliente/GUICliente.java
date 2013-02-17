/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gap.view.ui.cliente;

import br.com.gap.view.factory.ClienteBeanFactory;
import br.com.gap.view.ui.GUICadastro;
import br.com.gap.view.ui.GUIMensagem;
import br.com.gap.view.ui.GUIPrincipal;
import br.com.gap.view.ui.cliente.email.GUIClienteEmails;
import br.com.gap.view.ui.cliente.telefone.GUIClienteTelefones;
import br.com.gap.view.util.FuncoesInterface;
import entidades.Cliente;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.JPanel;
import org.swingBean.actions.ApplicationAction;
import org.swingBean.descriptor.GenericFieldDescriptor;
import org.swingBean.descriptor.TableFieldDescriptor;
import org.swingBean.descriptor.XMLDescriptorFactory;
import org.swingBean.gui.JActButton;

/**
 *
 * @author mjustino
 */
public class GUICliente extends GUICadastro<Cliente> {
    private final GUIPrincipal guiPrincipal;

    public GUICliente(GUIPrincipal guiPrincipal) throws InstantiationException, IllegalAccessException {
        super(Cliente.class);
        this.guiPrincipal = guiPrincipal;

        //<editor-fold defaultstate="collapsed" desc="botoes de ação">
        /**
         * adicionar botões de ação para telefones e para responsáveis
         */
        JActButton btnTelefones = new JActButton("Telefones", new ApplicationAction() {

            @Override
            public void execute() {
                try {
                    FuncoesInterface.setCursor(pnlManutencao, FuncoesInterface.TipoCursor.WAIT_CURSOR);

                    listarTelefones();
                } catch (Exception ex) {
                    GUIMensagem.showMessage(ex);
                } finally {
                    FuncoesInterface.setCursor(pnlManutencao, FuncoesInterface.TipoCursor.DEFAULT_CURSOR);
                }
            }
        });
        JActButton btnResponsaveis = new JActButton("Responsáveis", new ApplicationAction() {

            @Override
            public void execute() {
                //@todo: responsáveis  - passar o cliente
            }
        });
        JActButton btnEmails = new JActButton("Emails", new ApplicationAction() {

            @Override
            public void execute() {
                try {
                    FuncoesInterface.setCursor(pnlManutencao, FuncoesInterface.TipoCursor.WAIT_CURSOR);

                    listarEmails();
                } catch (Exception ex) {
                    GUIMensagem.showMessage(ex);
                } finally {
                    FuncoesInterface.setCursor(pnlManutencao, FuncoesInterface.TipoCursor.DEFAULT_CURSOR);
                }
            }
        });
        JPanel pnlBotoes = new JPanel(new FlowLayout());
        pnlBotoes.add(btnTelefones);
        pnlBotoes.add(btnEmails);
        pnlBotoes.add(btnResponsaveis);
        pnlInternoManutencao.add(pnlBotoes, BorderLayout.AFTER_LAST_LINE);
        //</editor-fold>
    }

    @Override
    public Cliente adicionarRegistro(Cliente obj) throws Exception {
        return ClienteBeanFactory.adicionar(obj);
    }

    @Override
    public Cliente alterarRegistro(Cliente obj) throws Exception {
        return ClienteBeanFactory.alterar(obj);
    }

    @Override
    public String[] getPesquisaPropertiesToChanged() {
        return new String[]{"nome", "documento"};
    }

    @Override
    public GenericFieldDescriptor getDescriptorManutencao() {
        GenericFieldDescriptor descriptor = XMLDescriptorFactory.getFieldDescriptor(
                Cliente.class,
                "br\\com\\gap\\view\\ui\\cliente\\ClienteManutencao_xmldescriptor.xml",
                "ClienteManutencao_xmldescriptor");
        return descriptor;
    }

    @Override
    public TableFieldDescriptor getTableDescriptorResultado() {
        TableFieldDescriptor descriptor = XMLDescriptorFactory.getTableFieldDescriptor(
                Cliente.class,
                "br\\com\\gap\\view\\ui\\cliente\\ClienteResultado_xmldescriptor.xml",
                "ClienteResultado_xmldescriptor");
        return descriptor;
    }

    @Override
    public GenericFieldDescriptor getDescriptorPesquisa() {
        GenericFieldDescriptor descriptor = XMLDescriptorFactory.getFieldDescriptor(
                Cliente.class,
                "br\\com\\gap\\view\\ui\\cliente\\ClientePesquisa_xmldescriptor.xml",
                "ClientePesquisa_xmldescriptor");
        return descriptor;
    }

    @Override
    public List<Cliente> pesquisar() throws Exception {
        return ClienteBeanFactory.findByNomeOrDocumento(
                pnlPesquisa.getPropertyValue("nome").toString(),
                pnlPesquisa.getPropertyValue("documento").toString());
    }

    @Override
    public void removerRegistro(Cliente obj) throws Exception {
        ClienteBeanFactory.remover(obj);
    }

    private void listarTelefones() throws InstantiationException, IllegalAccessException, Exception {
        if (getRegistroAtual() != null) {            
            GUIClienteTelefones gui = new GUIClienteTelefones(getRegistroAtual());
            guiPrincipal.getDesktopPane().add(gui);
            gui.setPosition();
            gui.setVisible(true);
        }
    }
    
    private void listarEmails() throws InstantiationException, IllegalAccessException, Exception {
        if (getRegistroAtual() != null) {
            GUIClienteEmails gui = new GUIClienteEmails(getRegistroAtual());
            guiPrincipal.getDesktopPane().add(gui);
            gui.setPosition();
            gui.setVisible(true);
        }
    }

    @Override
    public String getTitulo() {
        return "Cadastro de Clientes";
    }
}