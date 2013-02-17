/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package retorno;

import entidades.Quadra;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author avsilva
 */
public class HorarioReserva implements Serializable {
    private static final long serialVersionUID = 4377596413703297064L;

    private Date data;
    private Date hora;
    private Quadra quadra;
    //Apresentação
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    private String horaExtenso;
    private String nomeQuadra;

    public HorarioReserva(Date data, Date hora, Quadra quadra) {
        this.data = data;
        this.quadra = quadra;
        this.hora = hora;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getHoraExtenso() {
        horaExtenso = sdf.format(hora);
        return horaExtenso;
    }

    public String getNomeQuadra() {
        nomeQuadra = quadra.getNome();
        return nomeQuadra;
    }

    public Quadra getQuadra() {
        return quadra;
    }

    public void setQuadra(Quadra quadra) {
        this.quadra = quadra;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HorarioReserva other = (HorarioReserva) obj;
        if (this.data != other.data && (this.data == null || !this.data.equals(other.data))) {
            return false;
        }
        if (this.hora != other.hora && (this.hora == null || !this.hora.equals(other.hora))) {
            return false;
        }
        if (this.quadra != other.quadra && (this.quadra == null || !this.quadra.equals(other.quadra))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.data != null ? this.data.hashCode() : 0);
        hash = 53 * hash + (this.hora != null ? this.hora.hashCode() : 0);
        hash = 53 * hash + (this.quadra != null ? this.quadra.hashCode() : 0);
        return hash;
    }
}
