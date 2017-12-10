/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.com.ict.pokerstars.bo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Oscar Gutiérrez
 */
public class Baraja {

    private List<Carta> mazo;
    private List<Integer> cartasRepartidas;

    public Baraja() {
        mazo = new ArrayList<>();
        cartasRepartidas = new ArrayList<>();
    }

    public void barajar() {
        mazo.clear();
        cartasRepartidas.clear();
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 13; j++) {
                if (j == 1)//Ace
                {
                    mazo.add(new Carta(i, j + 13));
                } else {
                    mazo.add(new Carta(i, j));
                }
            }
        }
    }

    public List<Carta> generarMano() {
        List<Carta> mano = new ArrayList<>();
        while (mano.size() != 5) {
            Random r = new Random();
            int index = r.nextInt(mazo.size());
            if (!cartasRepartidas.contains(index)) {
                mano.add(mazo.get(index));
                cartasRepartidas.add(index);
            }
        }
        Collections.sort(mano);
        return mano;
    }

    public String muestrarInfoCartas(Carta c) {
        String valor = "" + c.getValor();
        if (c.getValor() > 10) {
            switch (c.getValor()) {
                case 14:
                    valor = "As";
                    break;
                case 13:
                    valor = "K";
                    break;
                case 12:
                    valor = "Q";
                    break;
                case 11:
                    valor = "J";
                    break;
            }
        }
        return valor + " de " + c.getPinta().getNombre();
    }

}
