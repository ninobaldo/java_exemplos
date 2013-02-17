/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gap.view.ui.mensalista;

import br.com.gap.view.factory.ClienteBeanFactory;
import br.com.gap.view.factory.MensalistaBeanFactory;
import br.com.gap.view.ui.GUIMensagem;
import br.com.gap.view.ui.GUISubCadastroSimples;
import br.com.gap.view.util.FuncoesInterface;
import entidades.Cliente;
import entidades.Mensalista;
import entidades.MensalistaPK;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import org.openswing.swing.client.DateControl;
import org.openswing.swing.internationalization.java.Resources;
import org.openswing.swing.util.java.Consts;
import retorno.HorarioMensalista;

/**
 *
 * @author mjustino
 */
public class GUIReservaMensalista extends GUISubCadastroSimples<Mensalista> {

    private JPanel pnlDados;
    private GridBagConstraints layoutConstraints;
    private GridBagLayout layout;
    private JComboBox<Cliente> jCliente;
    private DateControl jVigenciaInicio;
    private DateControl jVigenciaFim;
    private final HorarioMensalista horarioMensalista;

    public GUIReservaMensalista(HorarioMensalista horarioMensalista) throws InstantiationException, IllegalAccessException, Exception {
        super(Mensalista.class);
        this.horarioMensalista = horarioMensalista;

        getClientes();
    }

    @Override
    public JPanel getComponentesDadosCadastro() throws ParseException {
        /**
         * definir o layout em grid gridx controla a coluna a ser incluida gridy
         * controla a linha a ser incluida
         */
        jVigenciaInicio = new DateControl();
        jVigenciaInicio.setDateType(Consts.TYPE_DATE);
        jVigenciaInicio.setFormat(Resources.DMY);
        jVigenciaInicio.setDate(new Date());

        jVigenciaFim = new DateControl();
        jVigenciaFim.setDateType(Consts.TYPE_DATE);
        jVigenciaFim.setFormat(Resources.DMY);
        jVigenciaFim.setDate(new Date());

        jCliente = new JComboBox<>();
//        jCliente.setEditable(true);
//        jCliente.getEditor().getEditorComponent().addKeyListener(new KeyListener() {
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//            }
//
//            @Override
//            public void keyTyped(KeyEvent e) {
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
////                /**
////                 * pesquisar os clientes com base no que foi digitado quando o
////                 * tamanho do campo atingir tamanho de 3 dígitos.
////                 */
////                try {
////                    JTextField txt = (JTextField) jCliente.getEditor().getEditorComponent();
////                    String nome = txt.getText();
////
////                    if (nome.length() > 2) {
////                        getClientes(nome);
////                        jCliente.getEditor().setItem(nome);
////                        jCliente.setPopupVisible(true);
////                    } else if (nome.length() == 0) {
////                        jCliente.setPopupVisible(false);
////                        jCliente.removeAllItems();
////                    }
////                } catch (Exception ex) {
////                    GUIMensagem.showMessage(ex);
////                }
//            }
//        });

        layoutConstraints = new GridBagConstraints();
        layout = FuncoesInterface.getGridBagLayoutCadastros(layoutConstraints);
        pnlDados = new JPanel(layout);

        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 0;
        pnlDados.add(new JLabel("Início da Vigência"), layoutConstraints);

        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 1;
        pnlDados.add(jVigenciaInicio, layoutConstraints);

        layoutConstraints.gridx = 1;
        layoutConstraints.gridy = 0;
        pnlDados.add(new JLabel("Fim da Vigência"), layoutConstraints);

        layoutConstraints.gridx = 1;
        layoutConstraints.gridy = 1;
        pnlDados.add(jVigenciaFim, layoutConstraints);

        layoutConstraints.gridx = 2;
        layoutConstraints.gridy = 0;
        pnlDados.add(new JLabel("Cliente"), layoutConstraints);

        layoutConstraints.gridx = 2;
        layoutConstraints.gridy = 1;
        pnlDados.add(jCliente, layoutConstraints);

        return pnlDados;
    }

    @Override
    public void limparComponentesDadosCadastro() {
        FuncoesInterface.limparCampos(pnlDados);
    }

    @Override
    public String getTitulo() {
        return "Reserva para Mensalista";
    }

    private void getClientes(/*
             * String nomeCliente
             */) throws Exception {
        /**
         * define o renderer para que o jcombobox possa entender o objeto que
         * está contido no mesmo.
         */
        ListCellRenderer renderer = new ListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                return new JLabel(((Cliente) value).getNome());
            }
        };
        jCliente.setRenderer(renderer);
        List<Cliente> clientes = ClienteBeanFactory.findAll();
        //preenche o combobox das quadras
        for (Cliente cliente : clientes) {
            jCliente.addItem(cliente);
        }
    }

    @Override
    public Mensalista setDadosObjeto() {
        Mensalista obj = new Mensalista();
        MensalistaPK pk = new MensalistaPK();
        pk.setDataInicioVigencia(jVigenciaInicio.getDate());
        pk.setDiaSemana(horarioMensalista.getDiaSemana().getValor());
        pk.setHorario(horarioMensalista.getHora());
        pk.setPessoaId(((Cliente) jCliente.getSelectedItem()).getId());
        pk.setQuadraId(horarioMensalista.getQuadra().getId());
        obj.setMensalistaPK(pk);
        obj.setDataFimVigencia(jVigenciaFim.getDate());
        obj.setDataCadastro(jVigenciaInicio.getDate());
        return obj;
    }

    @Override
    public Mensalista salvarDados(Mensalista obj) throws Exception {
        return MensalistaBeanFactory.criar(obj);
    }
}
