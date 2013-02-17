/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gap.view.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

/**
 *
 * @author mjustino
 */
public class FixedLenghtNumberDocument extends FixedLenghtDocument {
    public FixedLenghtNumberDocument(int maxLenght) {
        super(maxLenght);
    }
    
    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException{
        String s = Formatador.limpaNumero(str);
        super.insertString(offset, s, attr);
    }    
}
