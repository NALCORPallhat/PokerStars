/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.com.ict.pokerstars.bo;

import cl.com.ict.pokerstars.enums.Pinta;

/**
 *
 * @author Oscar Gutiérrez
 */
public class Carta implements Comparable<Carta>{

    private Pinta pinta;
    private Integer valor;

    public Carta(int pinta, int valor) {
        this.pinta = Pinta.getPintaPorValor(pinta);
        this.valor = valor;
    }

    public Pinta getPinta() {
        return pinta;
    }

    public void setPinta(Pinta pinta) {
        this.pinta = pinta;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public int compareTo(Carta o) {
        return valor.compareTo(o.getValor());
    }
}
