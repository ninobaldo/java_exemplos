/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gap.view.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author mjustino
 */
public class FixedLenghtDocument extends PlainDocument {

    private int maxLength;

    public FixedLenghtDocument(int maxLenght) {
        super();
        this.maxLength = maxLenght;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) {
            return;
        }

        // aceitara qualquer no. de caracteres
        if (maxLength <= 0) {
            super.insertString(offset, str, attr);
            return;
        }

        // se o comprimento final for menor...
        int ilen = (getLength() + str.length());
        if (ilen <= maxLength) {
            super.insertString(offset, str, attr);   // ...aceita str
        } else {
            if (getLength() == maxLength) {
                return; // nada a fazer
            }
            String newStr = str.substring(0, (maxLength - getLength()));

            super.insertString(offset, newStr, attr);
        }
    }
}
