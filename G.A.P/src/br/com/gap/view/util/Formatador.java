/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gap.view.util;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFormattedTextField;

/**
 *
 * @author mjustino
 */
public class Formatador {

    public static void setMascara(JFormattedTextField txt, String mascara) throws ParseException {
        /**
         * mascara para CPF "###.###.###-##" 
         * mascara para CNPJ "##.###.###/####-##"
         */
        txt.setFormatterFactory(null);
        javax.swing.text.MaskFormatter format = new javax.swing.text.MaskFormatter(mascara);
        format.setPlaceholderCharacter('0');
        txt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(format));
        txt.setValue(null);
    }

    public static void limpaMascara(JFormattedTextField txt) {
        txt.setFormatterFactory(null);
    }

    public static String limpaNumero(String valor) {

        StringBuilder sb = new StringBuilder();

        String expression = "\\d";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(valor);

        while (matcher.find()) {
            sb.append(matcher.group());
        }
        
        return sb.toString();
    }
}
