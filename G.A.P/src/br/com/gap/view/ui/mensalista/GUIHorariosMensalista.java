/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gap.view.ui.mensalista;

import br.com.gap.view.factory.MensalistaBeanFactory;
import br.com.gap.view.factory.QuadraBeanFactory;
import br.com.gap.view.ui.GUIListagem;
import br.com.gap.view.ui.GUIPrincipal;
import br.com.gap.view.util.FuncoesInterface;
import entidades.Quadra;
import enums.DiaSemana;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.ParseException;
import java.util.List;
import javax.swing.*;
import org.swingBean.descriptor.TableFieldDescriptor;
import org.swingBean.descriptor.XMLDescriptorFactory;
import retorno.HorarioMensalista;

/**
 *
 * @author mjustino
 */
public class GUIHorariosMensalista extends GUIListagem<HorarioMensalista> {
    private JPanel pnlDados;
    private GridBagConstraints layoutConstraints;
    private GridBagLayout layout;
    private JComboBox<Quadra> jcmbQuadra;
    private JComboBox<DiaSemana> jcmbDiaSemana;
    private final GUIPrincipal guiPrincipal;

    public GUIHorariosMensalista(GUIPrincipal guiPrincipal) throws InstantiationException, IllegalAccessException, Exception {
        super();
        this.guiPrincipal = guiPrincipal;
    }

    @Override
    public TableFieldDescriptor getTableDescriptorResultado() {
        TableFieldDescriptor descriptor = XMLDescriptorFactory.getTableFieldDescriptor(
                HorarioMensalista.class,
                "br\\com\\gap\\view\\ui\\mensalista\\HorariosMensalistaTable_xmldescriptor.xml",
                "HorariosMensalistaTable_xmldescriptor");
        return descriptor;
    }

    @Override
    public JPanel getComponentesDadosCadastro() throws ParseException {
        /**
         * definir o layout em grid gridx controla a coluna a ser incluida gridy
         * controla a linha a ser incluida
         */
        jcmbQuadra = new JComboBox<>();
        jcmbDiaSemana = new JComboBox<>();
                
        layoutConstraints = new GridBagConstraints();
        layout = FuncoesInterface.getGridBagLayoutCadastros(layoutConstraints);
        pnlDados = new JPanel(layout);
        
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 0;
        pnlDados.add(new JLabel("Quadra"), layoutConstraints);
        
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 1;
        pnlDados.add(jcmbQuadra, layoutConstraints);
        
        layoutConstraints.gridx = 1;
        layoutConstraints.gridy = 0;
        pnlDados.add(new JLabel("Dia da Semana"), layoutConstraints);
        
        layoutConstraints.gridx = 1;
        layoutConstraints.gridy = 1;
        pnlDados.add(jcmbDiaSemana, layoutConstraints);

        return pnlDados;
    }

    @Override
    public void limparComponentesDadosCadastro() {
        FuncoesInterface.limparCampos(pnlDados);
    }

    @Override
    public String getTitulo() {
        return "Horários de Mensalistas";
    }

    @Override
    public List<HorarioMensalista> getRegistrosExistentes() throws Exception {
        return MensalistaBeanFactory.getMensalidades(
                (DiaSemana)jcmbDiaSemana.getSelectedItem(),
                (Quadra)jcmbQuadra.getSelectedItem());
    }
    
    private void getQuadras() throws Exception{
        /**
         * define o renderer para que o jcombobox
         * possa entender o objeto que está contido no
         * mesmo.
         */
        ListCellRenderer renderer = new ListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel jText = new JLabel(((Quadra)value).getNome());
                return jText;
            }
        };
        jcmbQuadra.setRenderer(renderer);
        List<Quadra> quadras = QuadraBeanFactory.findAll();
        //preenche o combobox das quadras
        for (Quadra quadra : quadras) {
            jcmbQuadra.addItem(quadra);
        }
    }
    
    private void getDiasSemana(){
        //preenche o combobox dos dias da semana
        for (DiaSemana diaSemana : DiaSemana.values()) {
            jcmbDiaSemana.addItem(diaSemana);
        }
    }

    @Override
    public void getDependentesListagem() throws Exception {
        getQuadras();
        getDiasSemana();
    }

    @Override
    public void callActionSelecionarRegistro(HorarioMensalista obj) throws Exception {
        //@todo: incluir o objeto quadra na passagem para a tela de reserva.
        GUIReservaMensalista gui = new GUIReservaMensalista(obj);
        guiPrincipal.getDesktopPane().add(gui);
        gui.setPosition();
        gui.setVisible(true);
    }
}
