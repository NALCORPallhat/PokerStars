/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.com.ict.pokerstars.enums;

/**
 *
 * @author Oscar Gutiérrez
 */
public enum Jugada {

    StraightFlush("straightFlush", 8),
    FourOfKind("fourOfKind", 7),
    FullHouse("fullHouse", 6),
    Flush("flush", 5),
    Straight("straight", 4),
    ThreeOfKind("threeOfKind", 3),
    TwoPair("twoPair", 2),
    OnePair("onePair", 1),
    HighCard("highCard", 0);

    private String nombre;
    private int valor;

    private Jugada(String n, int v) {
        nombre = n;
        valor = v;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

}
