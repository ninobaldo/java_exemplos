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
public class CalendarData extends CalendarFormatter{
 
    public CalendarData()
    {
        super(new SimpleDateFormat("dd/MM/yyyy"), "Data");
    }
}
