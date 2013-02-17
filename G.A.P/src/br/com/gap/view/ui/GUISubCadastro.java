/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gap.view.ui;

import br.com.gap.view.util.FuncoesInterface;
import java.awt.BorderLayout;
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
public abstract class GUISubCadastro<T> extends GUI implements IGUISubCadastro<T> {

    private T registroAtual;
    private final Class<T> clazz;
    private BeanTableModel<T> beanTableModel;
    private JBeanTable gridDados;

    /**
     * Creates new form GUISubCadastro
     */
    public GUISubCadastro(Class<T> clazz) throws InstantiationException, IllegalAccessException, Exception {
        initComponents();

        this.title = getTitulo();
        
        pnlInternoDados.setLayout(new BorderLayout());
        pnlInternoGrid.setLayout(new BorderLayout());

        this.clazz = clazz;

        setComponentesDadosCadastro();
        setComponentesTabela();
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
//                    atualizarCamposDados();
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

                    atualizarCamposDados();
                } catch (Exception ex) {
                    GUIMensagem.showMessage(ex);
                } finally {
                    FuncoesInterface.setCursor(null, FuncoesInterface.TipoCursor.DEFAULT_CURSOR);
                }
            }
        });        
    }

    private void setComponentesDadosCadastro() throws Exception {
        JPanel pnl = getComponentesDadosCadastro();
        pnl.setPreferredSize(pnlInternoDados.getSize());
        //pnl.setBackground(Color.lightGray);
        pnlInternoDados.add(pnl);
    }

    private JScrollPane getComponenteGrid() throws Exception {
        TableFieldDescriptor descriptor = getTableDescriptorResultado();
        beanTableModel = new BeanTableModel<>(descriptor);
        gridDados = new JBeanTable(beanTableModel);

        return new JScrollPane(gridDados);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnlInternoDados = new javax.swing.JPanel();
        btnSalvar = new javax.swing.JButton();
        pnlInternoGrid = new javax.swing.JPanel();
        btnAdicionar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();

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

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlInternoGridLayout = new javax.swing.GroupLayout(pnlInternoGrid);
        pnlInternoGrid.setLayout(pnlInternoGridLayout);
        pnlInternoGridLayout.setHorizontalGroup(
            pnlInternoGridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlInternoGridLayout.setVerticalGroup(
            pnlInternoGridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 272, Short.MAX_VALUE)
        );

        btnAdicionar.setText("Adicionar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnRemover.setText("Remover");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlInternoDados, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSalvar))
                    .addComponent(pnlInternoGrid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAdicionar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemover)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlInternoDados, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalvar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlInternoGrid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            FuncoesInterface.setCursor(this, FuncoesInterface.TipoCursor.WAIT_CURSOR);

            salvarRegistro();
        } catch (Exception ex) {
            registroAtual = null;
            GUIMensagem.showMessage(ex);
        } finally {
            FuncoesInterface.setCursor(this, FuncoesInterface.TipoCursor.DEFAULT_CURSOR);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        try {
            FuncoesInterface.setCursor(this, FuncoesInterface.TipoCursor.WAIT_CURSOR);

            listarRegistrosExistentes();
        } catch (Exception ex) {
            registroAtual = null;
            GUIMensagem.showMessage(ex);
        } finally {
            FuncoesInterface.setCursor(this, FuncoesInterface.TipoCursor.DEFAULT_CURSOR);
        }
    }//GEN-LAST:event_formComponentShown

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        try {
            FuncoesInterface.setCursor(this, FuncoesInterface.TipoCursor.WAIT_CURSOR);

            incluirNovoRegistro();
        } catch (Exception ex) {
            registroAtual = null;
            GUIMensagem.showMessage(ex);
        } finally {
            FuncoesInterface.setCursor(this, FuncoesInterface.TipoCursor.DEFAULT_CURSOR);
        }
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        try {
            FuncoesInterface.setCursor(this, FuncoesInterface.TipoCursor.WAIT_CURSOR);

            removerRegistro();
        } catch (Exception ex) {
            registroAtual = null;
            GUIMensagem.showMessage(ex);
        } finally {
            FuncoesInterface.setCursor(this, FuncoesInterface.TipoCursor.DEFAULT_CURSOR);
        }
    }//GEN-LAST:event_btnRemoverActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel pnlInternoDados;
    private javax.swing.JPanel pnlInternoGrid;
    // End of variables declaration//GEN-END:variables

    private void incluirNovoRegistro() throws Exception {
        registroAtual = null;
        limparComponentesDadosCadastro();
        pnlInternoDados.requestFocus();
    }

    private void removerRegistro() throws Exception {
        int index = gridDados.getSelectedRow();
        if (index != -1) {
            registroAtual = beanTableModel.getBeanAt(index);

            if (registroAtual != null) {
                removerRegistro(registroAtual);
            }
        }
        listarRegistrosExistentes();
        limparComponentesDadosCadastro();
    }

    private void atualizarCamposDados() throws Exception {
        int index = gridDados.getSelectedRow();
        if (index != -1) {
            registroAtual = beanTableModel.getBeanAt(index);
            setObjetoDadosCadastro(registroAtual);
        } else {
            limparComponentesDadosCadastro();
        }
    }

    private void listarRegistrosExistentes() throws Exception {
        List<T> registros = getRegistrosExistentes();
        beanTableModel.setBeanList(registros);
    }

    private void salvarRegistro() throws InstantiationException, IllegalAccessException, Exception {
        if (registroAtual == null) {
            registroAtual = clazz.newInstance();
            registroAtual = getObjetoDadosCadastro(registroAtual);
            registroAtual = adicionarRegistro(registroAtual);
        } else {
            registroAtual = getObjetoDadosCadastro(registroAtual);
            registroAtual = alterarRegistro(registroAtual);
        }
        listarRegistrosExistentes();
    }
}