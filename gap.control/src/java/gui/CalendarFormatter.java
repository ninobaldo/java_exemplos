/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.towel.bean.Formatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author avsilva
 */
public abstract class CalendarFormatter implements Formatter {

    private final SimpleDateFormat sdf;
    private final String nome;

    protected CalendarFormatter(SimpleDateFormat sdf, String nome) {
        this.sdf = sdf;
        this.nome = nome;
    }

    @Override
    public Object format(Object o) {
        return sdf.format(((Calendar) o).getTime());
    }

    @Override
    public Object parse(Object o) {
        Calendar cal = new GregorianCalendar();
        try {
            cal.setTime(sdf.parse((String) o));
        } catch (ParseException ex) {
        }
        return cal;
    }

    @Override
    public String getName() {
        return nome;
    }
}
