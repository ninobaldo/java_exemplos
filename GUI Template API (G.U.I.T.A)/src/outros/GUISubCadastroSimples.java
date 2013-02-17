/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package outros;

import br.com.gap.view.util.FuncoesInterface;
import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author mjustino
 */
public abstract class GUISubCadastroSimples<T> extends GUI implements IGUISubCadastroSimples<T> {

    private T registroAtual;
    private final Class<T> clazz;

    /**
     * Creates new form GUIListagem
     */
    public GUISubCadastroSimples(Class<T> clazz) throws InstantiationException, IllegalAccessException, Exception {
        initComponents();

        this.registroAtual = null;
        this.clazz = clazz;
        this.title = getTitulo();
        this.pnlInternoDados.setLayout(new BorderLayout());
        
        setComponentesDadosCadastro();
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
        btnOk = new javax.swing.JButton();

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

        btnOk.setMnemonic('L');
        btnOk.setText("Ok");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlInternoDados, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlInternoDados, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOk)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        try {
            FuncoesInterface.setCursor(this, FuncoesInterface.TipoCursor.WAIT_CURSOR);

            registrarDadosInformados();
        } catch (Exception ex) {
            GUIMensagem.showMessage(ex);
        } finally {
            FuncoesInterface.setCursor(this, FuncoesInterface.TipoCursor.DEFAULT_CURSOR);
        }
    }//GEN-LAST:event_btnOkActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        try {
            FuncoesInterface.setCursor(this, FuncoesInterface.TipoCursor.WAIT_CURSOR);

            limparComponentesDadosCadastro();
        } catch (Exception ex) {
            GUIMensagem.showMessage(ex);
        } finally {
            FuncoesInterface.setCursor(this, FuncoesInterface.TipoCursor.DEFAULT_CURSOR);
        }
    }//GEN-LAST:event_formComponentShown
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOk;
    private javax.swing.JPanel pnlInternoDados;
    // End of variables declaration//GEN-END:variables

    private void setComponentesDadosCadastro() throws Exception {
        JPanel pnl = getComponentesDadosCadastro();
        pnl.setPreferredSize(pnlInternoDados.getSize());
        //pnl.setBackground(Color.lightGray);
        pnlInternoDados.add(pnl);
    }

    private void registrarDadosInformados() throws Exception {
        T obj = clazz.newInstance();
        obj = setDadosObjeto();
        registroAtual = salvarDados(obj);        
        limparComponentesDadosCadastro();
        setVisible(false);
    }
}
