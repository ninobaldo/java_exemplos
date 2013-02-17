package enums;

import java.text.ParseException;

public enum DiaSemana {

    SEGUNDA(1, "segunda-feira"),
    TERCA(2, "terça-feira"),
    QUARTA(3, "quarta-feira"),
    QUINTA(4, "quinta-feira"),
    SEXTA(5, "sexta-feira"),
    SABADO(6, "sabado"),
    DOMINGO(7, "domingo");
    
    private final int valor;
    private final String nome;

    private DiaSemana(int valor, String nome) {
        this.valor = valor;
        this.nome = nome;
    }

    public short getValor() {
        return (short) valor;
    }

    public String getNome() {
        return nome;
    }

    public static String getNome(short valor) {
        for (DiaSemana diaSemana : DiaSemana.values()) {
            if (diaSemana.getValor() == valor) {
                return diaSemana.getNome();
            }
        }
        return "";
    }

    public static boolean contains(short valor) {
        if (valor < (short) 1 || valor > (short) 7) {
            return false;
        }

        return true;
    }

    public static boolean contains(String valor) {
        for (DiaSemana diaSemana : DiaSemana.values()) {
            if (diaSemana.getNome().equals(valor)) {
                return true;
            }
        }
        return false;
    }
    
    public static DiaSemana valueOf(int valor) throws ParseException{

        for (DiaSemana diaSemana : DiaSemana.values()) {
            if(diaSemana.getValor()== valor)
            {
                return diaSemana;
            }
        }
        
        throw new ParseException("O valor passado não é um dia da semana valido", valor);
    }
}