/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gap.view.ui;

import java.awt.Dimension;
import javax.swing.JInternalFrame;

/**
 *
 * @author mjustino
 */
public abstract class GUI extends JInternalFrame {
    public void setPosition(){
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation(
                (d.width- this.getSize().width)/2, 
                (d.height-this.getSize().height)/2);
    }
}
