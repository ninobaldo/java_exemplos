/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;

/**
 *
 * @author avsilva
 */
public class FactoryGUI extends JFrame{
    private static final long serialVersionUID = -1566203516192972700L;
//@todo colocar no factory futuro para construção de telas swing
    public void main(String[] args) {
        // coloca uma figura na barra de título da janela
        URL url = this.getClass().getResource("imagem.png");
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
        this.setIconImage(imagemTitulo);
    }
    
    public static void getTamanhoTela(){
        //@todo: Implementar
        //pega tela com base na resolução do SO e das variais do sistema
    }
    
    public static void getTamanhoMinimo(){
        //@todo: Implementar
        //pega tamanho de uma variavel
    }
    
    public static void getTamanhoMaximo(){
        //@todo: Implementar
    }
}
