/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package outros;

import br.com.gap.view.util.FuncoesInterface;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.swingBean.actions.ApplicationAction;
import org.swingBean.descriptor.BeanTableModel;
import org.swingBean.descriptor.TableFieldDescriptor;
import org.swingBean.gui.JBeanTable;

/**
 *
 * @author mjustino
 */
public abstract class GUIListagem<T> extends GUI implements IGUIListagem<T> {

    private BeanTableModel<T> beanTableModel;
    private JBeanTable gridDados;

    /**
     * Creates new form GUIListagem
     */
    public GUIListagem() throws InstantiationException, IllegalAccessException, Exception {
        initComponents();

        this.title = getTitulo();

        pnlInternoDados.setLayout(new BorderLayout());
        pnlInternoGrid.setLayout(new BorderLayout());

        setComponentesDadosCadastro();
        setComponentesTabela();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlInternoDados = new javax.swing.JPanel();
        pnlInternoGrid = new javax.swing.JPanel();
        btnListar = new javax.swing.JButton();
        btnSelecionar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setFrameIcon(null);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        pnlInternoDados.setLayout(null);

        javax.swing.GroupLayout pnlInternoGridLayout = new javax.swing.GroupLayout(pnlInternoGrid);
        pnlInternoGrid.setLayout(pnlInternoGridLayout);
        pnlInternoGridLayout.setHorizontalGroup(
            pnlInternoGridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlInternoGridLayout.setVerticalGroup(
            pnlInternoGridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 187, Short.MAX_VALUE)
        );

        btnListar.setMnemonic('L');
        btnListar.setText("Listar");
        btnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarActionPerformed(evt);
            }
        });

        btnSelecionar.setMnemonic('S');
        btnSelecionar.setText("Selecionar");
        btnSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlInternoGrid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlInternoDados, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 437, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnListar, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSelecionar, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnListar, btnSelecionar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlInternoDados, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnListar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlInternoGrid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSelecionar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
        try {
            FuncoesInterface.setCursor(this, FuncoesInterface.TipoCursor.WAIT_CURSOR);

            listarRegistrosExistentes();
        } catch (Exception ex) {
            GUIMensagem.showMessage(ex);
        } finally {
            FuncoesInterface.setCursor(this, FuncoesInterface.TipoCursor.DEFAULT_CURSOR);
        }
    }//GEN-LAST:event_btnListarActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        try {
            FuncoesInterface.setCursor(this, FuncoesInterface.TipoCursor.WAIT_CURSOR);

            listarCamposDependentes();
        } catch (Exception ex) {
            GUIMensagem.showMessage(ex);
        } finally {
            FuncoesInterface.setCursor(this, FuncoesInterface.TipoCursor.DEFAULT_CURSOR);
        }
    }//GEN-LAST:event_formComponentShown

    private void btnSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionarActionPerformed
        try {
            FuncoesInterface.setCursor(this, FuncoesInterface.TipoCursor.WAIT_CURSOR);

            //obter o registro selecionado e executar ação com o mesmo
            T obj = getRegistroAtual();
            callActionSelecionarRegistro(obj);
        } catch (Exception ex) {
            GUIMensagem.showMessage(ex);
        } finally {
            FuncoesInterface.setCursor(this, FuncoesInterface.TipoCursor.DEFAULT_CURSOR);
        }
    }//GEN-LAST:event_btnSelecionarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnListar;
    private javax.swing.JButton btnSelecionar;
    private javax.swing.JPanel pnlInternoDados;
    private javax.swing.JPanel pnlInternoGrid;
    // End of variables declaration//GEN-END:variables

    private void listarRegistrosExistentes() throws Exception {
        beanTableModel.setBeanList(new ArrayList<T>());
        List<T> registros = getRegistrosExistentes();
        beanTableModel.setBeanList(registros);
    }

    private void setComponentesTabela() throws Exception {
        pnlInternoGrid.add(getComponenteGrid());
//        gridDados.addDoubleClickAction(new ApplicationAction() {
//
//            @Override
//            public void execute() {
//                try {
//                    FuncoesInterface.setCursor(null, FuncoesInterface.TipoCursor.WAIT_CURSOR);
//
//                    //@todo: abrir tela para realização de reserva.
//                } catch (Exception ex) {
//                    GUIMensagem.showMessage(ex);
//                } finally {
//                    FuncoesInterface.setCursor(null, FuncoesInterface.TipoCursor.DEFAULT_CURSOR);
//                }
//            }
//        });
        
        ListSelectionModel lsm = gridDados.getSelectionModel();
        lsm.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    FuncoesInterface.setCursor(null, FuncoesInterface.TipoCursor.WAIT_CURSOR);

                    //@todo: abrir tela para realização de reserva.
                } catch (Exception ex) {
                    GUIMensagem.showMessage(ex);
                } finally {
                    FuncoesInterface.setCursor(null, FuncoesInterface.TipoCursor.DEFAULT_CURSOR);
                }
            }
        });
    }

    private JScrollPane getComponenteGrid() throws Exception {
        TableFieldDescriptor descriptor = getTableDescriptorResultado();
        beanTableModel = new BeanTableModel<>(descriptor);
        gridDados = new JBeanTable(beanTableModel);

        return new JScrollPane(gridDados);
    }

    private void setComponentesDadosCadastro() throws Exception {
        JPanel pnl = getComponentesDadosCadastro();
        pnl.setPreferredSize(pnlInternoDados.getSize());
        //pnl.setBackground(Color.lightGray);
        pnlInternoDados.add(pnl);
    }

    private void listarCamposDependentes() throws Exception {
        getDependentesListagem();
    }

    private T getRegistroAtual() {
        int index = gridDados.getSelectedRow();
        if (index != -1) {
            return beanTableModel.getBeanAt(index);            
        } else {
            return null;
        }
    }
}
