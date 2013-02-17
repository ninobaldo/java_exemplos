/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gap.view;

import br.com.gap.view.ui.GUIPrincipal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author mjustino
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /**
         * look and feel
         */
        try {
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");            
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                        
            //Nimbus is a polished cross-platform look and feel introduced in the Java SE 6 Update 10 (6u10) release
//            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

        GUIPrincipal guiPrincipal = new GUIPrincipal();
        guiPrincipal.setLocationRelativeTo(null);
        guiPrincipal.pack();
        guiPrincipal.setVisible(true);
    }
}
