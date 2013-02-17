/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gap.view.exception;

/**
 *
 * @author mjustino
 */
public class DukeClubException extends Exception{

    public DukeClubException(String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
    }

    public DukeClubException(Throwable thrwbl) {
        super(thrwbl);
    }

    public DukeClubException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public DukeClubException(String string) {
        super(string);
    }

    public DukeClubException() {
    }
}
