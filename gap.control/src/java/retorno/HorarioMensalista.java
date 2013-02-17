/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package retorno;

import entidades.Quadra;
import enums.DiaSemana;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author avsilva
 */
public class HorarioMensalista implements Serializable{
    private static final long serialVersionUID = -7519795905192572206L;

    private final String situacao[] = {"OCUPADO", "LIVRE"};
    private String horaExtenso;
    private String diaSemanaExtenso;
    private String nomeQuadra;
    private Boolean isOcupado;
    private Quadra quadra;
    private DiaSemana diaSemana;
    private Date hora;
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

    public HorarioMensalista(Date hora, DiaSemana dia_Semana, String nomeQuadra, Boolean isOcupado, Quadra quadra) {
        this.hora = hora;
        this.nomeQuadra = nomeQuadra;
        this.isOcupado = isOcupado;
        this.quadra = quadra;
        this.diaSemana = dia_Semana;
    }

    /**
     * Recupera dia da semana por extenso
     *
     * @return dia da semana por extenso
     */
    public String getDiaSemanaExtenso() {
        diaSemanaExtenso = diaSemana.getNome();
        return diaSemanaExtenso;
    }

    public String getSituacao() {
        return situacao[isOcupado ? 0 : 1];
    }

    public Boolean getIsOcupado() {
        return isOcupado;
    }

    public void setIsOcupado(Boolean isOcupado) {
        this.isOcupado = isOcupado;
    }

    public Quadra getQuadra() {
        return quadra;
    }

    public void setQuadra(Quadra quadra) {
        this.quadra = quadra;
    }

    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }
    
    /**
     * Recupera Hora do dia
     *
     * @return hora
     */
    public String getHoraExtenso() {
        horaExtenso = sdf.format(hora);
        return horaExtenso;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    
    public String getNomeQuadra() {
        return nomeQuadra;
    }

    public void setNomeQuadra(String nomeQuadra) {
        this.nomeQuadra = nomeQuadra;
    }
}
