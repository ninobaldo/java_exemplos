/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gap.view.ui.cliente.telefone;

import br.com.gap.view.factory.ClienteBeanFactory;
import br.com.gap.view.ui.GUISubCadastro;
import br.com.gap.view.util.FixedLenghtNumberDocument;
import br.com.gap.view.util.Formatador;
import br.com.gap.view.util.FuncoesInterface;
import entidades.Cliente;
import entidades.Telefone;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.ParseException;
import java.util.List;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.swingBean.descriptor.TableFieldDescriptor;
import org.swingBean.descriptor.XMLDescriptorFactory;

/**
 *
 * @author mjustino
 */
public class GUIClienteTelefones extends GUISubCadastro<Telefone> {

    private JPanel pnlDados;
    private JTextField txtDescricao;
    private JLabel label3;
    private JFormattedTextField txtTelefone;
    private JLabel label2;
    private JLabel label1;
    private JFormattedTextField txtDDD;
    private Cliente cliente;

    public GUIClienteTelefones(Cliente cliente) throws InstantiationException, IllegalAccessException, Exception {
        super(Telefone.class);
        this.cliente = cliente;
    }

    @Override
    public TableFieldDescriptor getTableDescriptorResultado() {
        TableFieldDescriptor descriptor = XMLDescriptorFactory.getTableFieldDescriptor(
                Telefone.class,
                "br\\com\\gap\\view\\ui\\cliente\\telefone\\TelefoneTable_xmldescriptor.xml",
                "TelefoneTable_xmldescriptor");
        return descriptor;
    }

    @Override
    public void removerRegistro(Telefone obj) throws Exception {
        if (obj != null) {
            cliente.getTelefones().remove(obj);
            cliente = ClienteBeanFactory.alterar(cliente);
        }
    }

    @Override
    public List<Telefone> getRegistrosExistentes() {
        return cliente.getTelefones();
    }

    @Override
    public Telefone adicionarRegistro(Telefone obj) throws Exception {
        if (obj != null) {
            cliente.getTelefones().add(obj);
            cliente = ClienteBeanFactory.alterar(cliente);
        }
        return obj;
    }

    @Override
    public Telefone alterarRegistro(Telefone obj) throws Exception {
        int index = cliente.getTelefones().indexOf(obj);
        if (index != -1) {            
            cliente.getTelefones().set(index, obj);
            cliente = ClienteBeanFactory.alterar(cliente);
        }
        return obj;
    }

    @Override
    public JPanel getComponentesDadosCadastro() throws ParseException {
        label1 = new JLabel("DDD:");
        txtDDD = new JFormattedTextField();
        txtDDD.setDocument(new FixedLenghtNumberDocument(2));
        txtDDD.setPreferredSize(new Dimension(50, 20));
        label2 = new JLabel("Telefone:");
        txtTelefone = new JFormattedTextField();
        Formatador.setMascara(txtTelefone, "####-####");
        txtTelefone.setPreferredSize(new Dimension(100, 20));
        label3 = new JLabel("Descricao:");
        txtDescricao = new JTextField();
        txtDescricao.setPreferredSize(new Dimension(200, 20));

        pnlDados = new JPanel(new FlowLayout());
        pnlDados.add(label1);
        pnlDados.add(txtDDD);
        pnlDados.add(label2);
        pnlDados.add(txtTelefone);
        pnlDados.add(label3);
        pnlDados.add(txtDescricao);

        return pnlDados;
    }

    @Override
    public void limparComponentesDadosCadastro() {
        FuncoesInterface.limparCampos(pnlDados);
    }

    @Override
    public void setObjetoDadosCadastro(Telefone t) {
        txtDDD.setText(t.getDdd().toString());
        txtDescricao.setText(t.getDescricao());
        txtTelefone.setText(t.getNumero().toString());
    }

    @Override
    public Telefone getObjetoDadosCadastro(Telefone t) throws ParseException {
        if(t == null)
            t = new Telefone();
            
        t.setDdd(Short.parseShort(txtDDD.getText()));
        String telefone = txtTelefone.getText();
        telefone = Formatador.limpaNumero(telefone);
        int telefone_numero = Integer.parseInt(telefone);
        t.setNumero(telefone_numero);
        t.setDescricao(txtDescricao.getText());

        return t;
    }

    @Override
    public String getTitulo() {
        return "Telefones de Contato";
    }
}
