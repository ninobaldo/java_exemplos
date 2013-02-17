/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gap.view.ui.quadra;

import br.com.gap.view.factory.QuadraBeanFactory;
import br.com.gap.view.ui.GUISubCadastro;
import br.com.gap.view.util.FixedLenghtDocument;
import br.com.gap.view.util.FuncoesInterface;
import entidades.Quadra;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.ParseException;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.swingBean.descriptor.TableFieldDescriptor;
import org.swingBean.descriptor.XMLDescriptorFactory;

/**
 *
 * @author mjustino
 */
public class GUIQuadra extends GUISubCadastro<Quadra> {

    private JPanel pnlDados;
    private JCheckBox ckbDisponivel;
    private JTextField txtDescricao;
    private JLabel label2;
    private JLabel label1;
    private JTextField txtNome;
    private GridBagConstraints layoutConstraints;
    private GridBagLayout layout;

    public GUIQuadra() throws InstantiationException, IllegalAccessException, Exception {
        super(Quadra.class);
    }

    @Override
    public TableFieldDescriptor getTableDescriptorResultado() {
        TableFieldDescriptor descriptor = XMLDescriptorFactory.getTableFieldDescriptor(
                Quadra.class,
                "br\\com\\gap\\view\\ui\\quadra\\QuadraTable_xmldescriptor.xml",
                "QuadraTable_xmldescriptor");
        return descriptor;
    }

    @Override
    public void removerRegistro(Quadra obj) throws Exception {
        if (obj != null) {
            QuadraBeanFactory.remover(obj);
        }
    }

    @Override
    public List<Quadra> getRegistrosExistentes() throws Exception {
        return QuadraBeanFactory.findAll();
    }

    @Override
    public Quadra adicionarRegistro(Quadra obj) throws Exception {
        if (obj != null) {
            return QuadraBeanFactory.adicionar(obj);
        } else {
            return null;
        }
    }

    @Override
    public Quadra alterarRegistro(Quadra obj) throws Exception {
        if (obj != null) {
            return QuadraBeanFactory.alterar(obj);
        } else {
            return null;
        }
    }

    @Override
    public JPanel getComponentesDadosCadastro() throws ParseException {
        /**
         * definir o layout em grid gridx controla a coluna a ser incluida gridy
         * controla a linha a ser incluida
         */
        label1 = new JLabel("Nome:");
        txtNome = new JTextField();
        txtNome.setDocument(new FixedLenghtDocument(50));
        txtNome.setPreferredSize(new Dimension(150, 20));
        label2 = new JLabel("Descrição:");
        txtDescricao = new JTextField();
        txtDescricao.setDocument(new FixedLenghtDocument(100));
        txtDescricao.setPreferredSize(new Dimension(250, 20));
        ckbDisponivel = new JCheckBox("Quadra disponível?");

        layoutConstraints = new GridBagConstraints();
        layout = FuncoesInterface.getGridBagLayoutCadastros(layoutConstraints);
        pnlDados = new JPanel(layout);
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 0;
        pnlDados.add(label1, layoutConstraints);
        layoutConstraints.gridy = 1;
        pnlDados.add(txtNome, layoutConstraints);
        layoutConstraints.gridy = 2;
        pnlDados.add(label2, layoutConstraints);
        layoutConstraints.gridy = 3;
        pnlDados.add(txtDescricao, layoutConstraints);
        layoutConstraints.gridy = 5;
        pnlDados.add(ckbDisponivel, layoutConstraints);

        return pnlDados;
    }

    @Override
    public void limparComponentesDadosCadastro() {
        FuncoesInterface.limparCampos(pnlDados);
    }

    @Override
    public void setObjetoDadosCadastro(Quadra t) {
        txtNome.setText(t.getNome());
        txtDescricao.setText(t.getDescricao());
        ckbDisponivel.setSelected(t.getIsDisponivel());
    }

    @Override
    public Quadra getObjetoDadosCadastro(Quadra t) throws ParseException {
        if (t == null) {
            t = new Quadra();
        }

        t.setNome(txtNome.getText());
        t.setDescricao(txtDescricao.getText());
        t.setIsDisponivel(ckbDisponivel.isSelected());

        return t;
    }

    @Override
    public String getTitulo() {
        return "Cadastro de Quadra";
    }
}
