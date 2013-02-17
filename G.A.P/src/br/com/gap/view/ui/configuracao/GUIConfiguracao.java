/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gap.view.ui.configuracao;

import br.com.gap.view.factory.ExpedienteBeanFactory;
import br.com.gap.view.ui.GUISubCadastro;
import br.com.gap.view.util.FixedLenghtNumberDocument;
import br.com.gap.view.util.FuncoesInterface;
import entidades.Expediente;
import enums.DiaSemana;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.openswing.swing.client.DateControl;
import org.openswing.swing.internationalization.java.Resources;
import org.openswing.swing.util.java.Consts;
import org.swingBean.descriptor.TableFieldDescriptor;
import org.swingBean.descriptor.XMLDescriptorFactory;

/**
 *
 * @author mjustino
 */
public class GUIConfiguracao extends GUISubCadastro<Expediente> {

    private DateControl jExpedienteInicio;
    private DateControl jExpedienteFim;
    private JTextField jPeriodoBase;
    private DateControl jDataVigencia;
    private JComboBox jDiaSemana;
    private JPanel pnlDados;
    private GridBagConstraints layoutConstraints;
    private GridBagLayout layout;

    public GUIConfiguracao() throws InstantiationException, IllegalAccessException, Exception {
        super(Expediente.class);
    }

    @Override
    public TableFieldDescriptor getTableDescriptorResultado() {
        TableFieldDescriptor descriptor = XMLDescriptorFactory.getTableFieldDescriptor(
                Expediente.class,
                "br\\com\\gap\\view\\ui\\configuracao\\ConfiguracaoTable_xmldescriptor.xml",
                "Configuracao");
        return descriptor;
    }

    @Override
    public void removerRegistro(Expediente obj) throws Exception {
        if (obj != null) {
            ExpedienteBeanFactory.remover(obj);
        }
    }

    @Override
    public List<Expediente> getRegistrosExistentes() throws Exception {
        return ExpedienteBeanFactory.findAll();
    }

    @Override
    public Expediente adicionarRegistro(Expediente obj) throws Exception {
        if (obj != null) {
            return ExpedienteBeanFactory.adicionar(obj);
        }
        return obj;
    }

    @Override
    public Expediente alterarRegistro(Expediente obj) throws Exception {
        if (obj != null) {
            return ExpedienteBeanFactory.alterar(obj);
        }
        return obj;
    }

    @Override
    public JPanel getComponentesDadosCadastro() throws ParseException {
        /**
         * definir o layout em grid gridx controla a coluna a ser incluida gridy
         * controla a linha a ser incluida
         */
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        jExpedienteInicio = new DateControl();        
        jExpedienteInicio.setDateType(Consts.TYPE_TIME);
        jExpedienteInicio.setTimeFormat(Resources.HH_MM);
        jExpedienteInicio.setDate(sdf.parse("08:00"));

        jExpedienteFim = new DateControl();
        jExpedienteFim.setDateType(Consts.TYPE_TIME);
        jExpedienteFim.setTimeFormat(Resources.HH_MM);
        jExpedienteFim.setDate(sdf.parse("22:00"));

        jPeriodoBase = new JTextField("60");
        jPeriodoBase.setDocument(new FixedLenghtNumberDocument(3));

        jDataVigencia = new DateControl();
        jDataVigencia.setDateType(Consts.TYPE_DATE);
        jDataVigencia.setFormat(Resources.DMY);
        jDataVigencia.setDate(new Date());

        jDiaSemana = new JComboBox();
        for (DiaSemana diaSemana : DiaSemana.values()) {
            jDiaSemana.addItem(diaSemana);
        }

        layoutConstraints = new GridBagConstraints();
        layout = FuncoesInterface.getGridBagLayoutCadastros(layoutConstraints);
        pnlDados = new JPanel();
        pnlDados.setLayout(layout);

        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 0;
        layoutConstraints.weighty = 0.5;
        pnlDados.add(new JLabel("Início do expediente:"), layoutConstraints);
        
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 1;
        layoutConstraints.weighty = 0.5;
        pnlDados.add(jExpedienteInicio, layoutConstraints);
        
        layoutConstraints.gridx = 1;
        layoutConstraints.gridy = 0;
        layoutConstraints.weighty = 0.5;
        pnlDados.add(new JLabel("Fim do expediente:"), layoutConstraints);
        
        layoutConstraints.gridx = 1;
        layoutConstraints.gridy = 1;
        layoutConstraints.weighty = 0.5;
        pnlDados.add(jExpedienteFim, layoutConstraints);
        
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 2;
        layoutConstraints.weighty = 0.5;
        pnlDados.add(new JLabel("Período base (minutos):"), layoutConstraints);
        
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 3;
        layoutConstraints.weighty = 0.5;
        pnlDados.add(jPeriodoBase, layoutConstraints);
        
        layoutConstraints.gridx = 1;
        layoutConstraints.gridy = 2;
        layoutConstraints.weighty = 0.5;
        pnlDados.add(new JLabel("Data de vigência:"), layoutConstraints);
        
        layoutConstraints.gridx = 1;
        layoutConstraints.gridy = 3;
        layoutConstraints.weighty = 0.5;
        pnlDados.add(jDataVigencia, layoutConstraints);
        
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 4;
        layoutConstraints.weighty = 0.5;
        pnlDados.add(new JLabel("Dia da semana:"), layoutConstraints);
        
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 5;
        layoutConstraints.weighty = 0.5;
        pnlDados.add(jDiaSemana, layoutConstraints);

        return pnlDados;
    }

    @Override
    public void limparComponentesDadosCadastro() {
        FuncoesInterface.limparCampos(pnlDados);
    }

    @Override
    public void setObjetoDadosCadastro(Expediente t) {
        if (t != null) {
            jDiaSemana.setSelectedItem(DiaSemana.valueOf(t.getDiaSemanaExtenso()));
            jExpedienteFim.setDate(t.getFimExpediente());
            jExpedienteInicio.setDate(t.getInicioExpediente());
            jPeriodoBase.setText(t.getPeriodoBaseMinutos().toString());
            jDataVigencia.setDate(t.getVigencia());
        } else {
            FuncoesInterface.limparCampos(pnlDados);
        }
    }

    @Override
    public Expediente getObjetoDadosCadastro(Expediente t) throws ParseException {
        if (t == null) {
            t = new Expediente();
        }
        
        t.setDiaSemana(((DiaSemana)jDiaSemana.getSelectedItem()).getValor());
        t.setFimExpediente(jExpedienteFim.getDate());
        t.setInicioExpediente(jExpedienteInicio.getDate());
        t.setPeriodoBaseMinutos(Short.parseShort(jPeriodoBase.getText()));
        t.setVigencia(jDataVigencia.getDate());

        return t;
    }

    @Override
    public String getTitulo() {
        return "Configurações de Sistema";
    }
}
