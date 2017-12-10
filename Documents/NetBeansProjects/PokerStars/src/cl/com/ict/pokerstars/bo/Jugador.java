/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.com.ict.pokerstars.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Oscar Gutiérrez
 */
public class Jugador {

    private String nombre;
    private List<Carta> mano;
    private HashMap<Integer, Integer> repeticiones;

    public void obtenerMapaRepeticiones() {
        repeticiones = new HashMap<>();
        for (int i = 0; i < mano.size(); i++) {
            int j = mano.get(i).getValor();
            int cont = 1;
            for (int k = 0; k < mano.size(); k++) {
                if (i != k) {
                    int l = mano.get(k).getValor();
                    if (j == l) {
                        cont++;
                    }
                }
            }
            if (cont > 1) {
                repeticiones.put(j, cont);
            }
        }
    }

    public boolean straightFlush() {
        if (straight() && flush()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean fourOfKind() {
        for (Object key : repeticiones.keySet()) {
            if (repeticiones.get(key) == 4) {
                return true;
            }
        }
        return false;
    }

    public boolean fullHouse() {
        boolean three = false;
        boolean pair = false;
        for (Integer key : repeticiones.keySet()) {
            if (repeticiones.get(key) == 3) {
                three = true;
            }
            if (repeticiones.get(key) == 2) {
                pair = true;
            }
        }
        if (pair && three) {
            return true;
        } else {
            return false;
        }
    }

    public boolean flush() {
        for (int i = 0; i < mano.size() - 1; i++) {
            Carta c = mano.get(i);
            Carta c1 = mano.get(i + 1);
            if (c.getPinta() != c1.getPinta()) {
                return false;
            }
        }
        return true;
    }

    public boolean straight() {
        for (int i = 0; i < mano.size() - 1; i++) {
            Carta c = mano.get(i);
            Carta c1 = mano.get(i + 1);
            if ((c.getValor() + 1) != c1.getValor()) {
                return false;
            }
        }
        return true;
    }

    public boolean threeOfKind() {
        for (Integer key : repeticiones.keySet()) {
            if (repeticiones.get(key) == 3) {
                return true;
            }
        }
        return false;
    }

    public boolean twoPair() {
        if (repeticiones.keySet().size() == 2) {
            int cont = 0;
            for (Integer key : repeticiones.keySet()) {
                if (repeticiones.get(key) == 2) {
                    cont++;
                }
            }
            if (cont == 2) {
                return true;
            }
        }
        return false;
    }

    public boolean onePair() {
        for (Integer key : repeticiones.keySet()) {
            if (repeticiones.get(key) == 2) {
                return true;
            }
        }
        return false;
    }

    public int highCard() {
        int m = 0;
        for (Carta carta : mano) {
            if (carta.getValor() > m) {
                m = carta.getValor();
            }
        }
        return m;
    }

    public int highCard(Set<Integer> values) {
        int m = 0;
        for (Carta carta : mano) {
            if (!values.contains(carta.getValor())) {
                if (carta.getValor() > m) {
                    m = carta.getValor();
                }
            }
        }
        return m;
    }

    public int sumatoriaValor() {
        int m = 0;
        for (Carta carta : mano) {
            m += carta.getValor();
        }
        return m;
    }

    public int sumatoriaPinta() {
        int m = 0;
        for (Carta carta : mano) {
            m += carta.getPinta().getValor();
        }
        return m;
    }

    public int sumatoriaValorPinta() {
        int m = 0;
        for (Carta carta : mano) {
            m += carta.getValor() + carta.getPinta().getValor();
        }
        return m;
    }

    public List<Carta> getMano() {
        return mano;
    }

    public void setMano(List<Carta> mano) {
        this.mano = mano;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public HashMap<Integer, Integer> getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(HashMap<Integer, Integer> repeticiones) {
        this.repeticiones = repeticiones;
    }

}
