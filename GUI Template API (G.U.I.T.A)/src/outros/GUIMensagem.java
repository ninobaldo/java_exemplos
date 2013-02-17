/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package outros;

import javax.swing.JOptionPane;

/**
 *
 * @author mjustino
 */
public class GUIMensagem extends JOptionPane{
    public static void showMessage(String message, String title, int messageType){
        showMessageDialog(null, message, title, messageType);
    }
    
    public static void showMessage(Exception ex){
        showMessageDialog(null, ex);
    }
}
