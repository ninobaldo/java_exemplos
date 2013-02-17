/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.text.SimpleDateFormat;

/**
 *
 * @author avsilva
 */
public class CalendarHora extends CalendarFormatter {
    public CalendarHora(){
        super(new SimpleDateFormat("HH:mm"), "Hora");
    }
    
}
