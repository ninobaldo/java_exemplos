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
public class CalendarDataHora extends CalendarFormatter {

    public CalendarDataHora() {
        super(new SimpleDateFormat("dd/MM/yyyy HH:mm"), "Data/Hora");
    }
}
