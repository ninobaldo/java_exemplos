/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gap.view.ui.cliente.email;

import br.com.gap.view.factory.ClienteBeanFactory;
import br.com.gap.view.ui.GUISubCadastro;
import br.com.gap.view.util.FixedLenghtDocument;
import br.com.gap.view.util.FuncoesInterface;
import entidades.Cliente;
import entidades.Email;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.ParseException;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.swingBean.descriptor.TableFieldDescriptor;
import org.swingBean.descriptor.XMLDescriptorFactory;

/**
 *
 * @author mjustino
 */
public class GUIClienteEmails extends GUISubCadastro<Email> {

    private JPanel pnlDados;
    private JTextField txtDescricao;
    private JTextField txtEmail;
    private JLabel label2;
    private JLabel label1;
    private Cliente cliente;
    private GridBagConstraints layoutConstraints;
    private GridBagLayout layout;

    public GUIClienteEmails(Cliente cliente) throws InstantiationException, IllegalAccessException, Exception {
        super(Email.class);
        this.cliente = cliente;
    }

    @Override
    public TableFieldDescriptor getTableDescriptorResultado() {
        TableFieldDescriptor descriptor = XMLDescriptorFactory.getTableFieldDescriptor(
                Email.class,
                "br\\com\\gap\\view\\ui\\cliente\\email\\EmailTable_xmldescriptor.xml",
                "EmailTable_xmldescriptor");
        return descriptor;
    }

    @Override
    public void removerRegistro(Email obj) throws Exception {
        if (obj != null) {
            cliente.getEmails().remove(obj);
            cliente = ClienteBeanFactory.alterar(cliente);
        }
    }

    @Override
    public List<Email> getRegistrosExistentes() {
        return cliente.getEmails();
    }

    @Override
    public Email adicionarRegistro(Email obj) throws Exception {
        if (obj != null) {
            cliente.getEmails().add(obj);
            cliente = ClienteBeanFactory.alterar(cliente);
        }
        return obj;
    }

    @Override
    public Email alterarRegistro(Email obj) throws Exception {
        int index = cliente.getEmails().indexOf(obj);
        if (index != -1) {            
            cliente.getEmails().set(index, obj);
            cliente = ClienteBeanFactory.alterar(cliente);
        }
        return obj;
    }

    @Override
    public JPanel getComponentesDadosCadastro() throws ParseException {
        /**
         * definir o layout em grid
         * gridx controla a coluna a ser incluida
         * gridy controla a linha a ser incluida
         */
        label2 = new JLabel("Email:");
        txtEmail = new JTextField();
        txtEmail.setPreferredSize(new Dimension(200, 20));
        label1 = new JLabel("Descrição:");
        txtDescricao = new JTextField();        
        txtDescricao.setDocument(new FixedLenghtDocument(100));
        txtDescricao.setPreferredSize(new Dimension(250, 20));        
        
        layoutConstraints = new GridBagConstraints();
        layout = FuncoesInterface.getGridBagLayoutCadastros(layoutConstraints);
        pnlDados = new JPanel(layout);
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 0;
        layoutConstraints.weightx=1;
        pnlDados.add(label2, layoutConstraints);
        layoutConstraints.gridy = 1;        
        pnlDados.add(txtEmail, layoutConstraints);        
        layoutConstraints.gridy = 2;
        pnlDados.add(label1, layoutConstraints);
        layoutConstraints.gridy = 3;
        pnlDados.add(txtDescricao, layoutConstraints);

        return pnlDados;
    }

    @Override
    public void limparComponentesDadosCadastro() {
        FuncoesInterface.limparCampos(pnlDados);
    }

    @Override
    public void setObjetoDadosCadastro(Email t) {
        txtDescricao.setText(t.getDescricao());
        txtEmail.setText(t.getEmail());
    }

    @Override
    public Email getObjetoDadosCadastro(Email t) throws ParseException {
        if(t == null)
            t = new Email();
            
        t.setDescricao(txtDescricao.getText());        
        t.setEmail(txtEmail.getText());

        return t;
    }

    @Override
    public String getTitulo() {
        return "E-mails de Contato";
    }
}
