/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.com.ict.pokerstars.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import cl.com.ict.pokerstars.enums.Jugada;

/**
 *
 * @author Oscar Gutiérrez
 */
public class Juego {

    private List<Jugador> jugadores;
    private Baraja baraja;
    private Map<String, List<Jugador>> jugadas;
    private int numeroJugadores;
    private Jugador ganador;
    private Jugada jugadaGanadora;

    public Juego() {
        jugadores = new ArrayList<>();
        baraja = new Baraja();
    }

    public void generarJugadores() {
        jugadores.clear();
        for (int i = 0; i < numeroJugadores; i++) {
            Jugador j = new Jugador();
            j.setNombre("Jugador " + i);
            jugadores.add(j);
        }
    }

    public void repartirCartas() {
        baraja.barajar();
        for (int i = 0; i < jugadores.size(); i++) {
            jugadores.get(i).setMano(baraja.generarMano());
        }
    }

    public void verificarJugadas() {
        jugadas = new HashMap<>();
        for (Jugador j : jugadores) {
            j.obtenerMapaRepeticiones();
            Jugada jugada = null;
            if (j.straightFlush()) {
                jugada = Jugada.StraightFlush;
            } else if (j.fourOfKind()) {
                jugada = Jugada.FourOfKind;
            } else if (j.fullHouse()) {
                jugada = Jugada.FullHouse;
            } else if (j.flush()) {
                jugada = Jugada.Flush;
            } else if (j.straight()) {
                jugada = Jugada.Straight;
            } else if (j.threeOfKind()) {
                jugada = Jugada.ThreeOfKind;
            } else if (j.twoPair()) {
                jugada = Jugada.TwoPair;
            } else if (j.onePair()) {
                jugada = Jugada.OnePair;
            } else {
                jugada = Jugada.HighCard;
            }
            if (jugadas.containsKey(jugada.getNombre())) {
                List<Jugador> jugadores = jugadas.get(jugada.getNombre());
                jugadores.add(j);
            } else {
                List<Jugador> jugadores = new ArrayList<>();
                jugadores.add(j);
                jugadas.put(jugada.getNombre(), jugadores);
            }
        }
    }

    public void buscarGanador() {
        for (String jugada : jugadas.keySet()) {
            switch (jugada) {
                case "straightFlush": { //Listo
                    jugadaGanadora = Jugada.StraightFlush;
                    if (jugadas.get(jugada) != null) {
                        if (jugadas.get(jugada).size() == 1) {
                            ganador = jugadas.get(jugada).get(0);
                        } else {
                            ganador = desempateSraightFlush(jugadas.get(jugada));
                        }
                    }
                }
                break;
                case "fourOfKind": { //Listo
                    jugadaGanadora = Jugada.FourOfKind;
                    if (jugadas.get(jugada) != null) {
                        if (jugadas.get(jugada).size() == 1) {
                            ganador = jugadas.get(jugada).get(0);
                        } else {
                            ganador = desempateFourOfKind(jugadas.get(jugada));
                        }
                    }
                }
                break;
                case "fullHouse": {//Listo
                    jugadaGanadora = Jugada.FullHouse;
                    if (jugadas.get(jugada) != null) {
                        if (jugadas.get(jugada).size() == 1) {
                            ganador = jugadas.get(jugada).get(0);
                        } else {
                            ganador = desempateFullHouse(jugadas.get(jugada));
                        }
                    }
                }
                break;
                case "flush": {//Listo
                    jugadaGanadora = Jugada.Flush;
                    if (jugadas.get(jugada) != null) {
                        if (jugadas.get(jugada).size() == 1) {
                            ganador = jugadas.get(jugada).get(0);
                        } else {
                            ganador = desempateFlush(jugadas.get(jugada));
                        }
                    }
                }
                break;
                case "straight": {//Listo
                    jugadaGanadora = Jugada.Straight;
                    if (jugadas.get(jugada) != null) {
                        if (jugadas.get(jugada).size() == 1) {
                            ganador = jugadas.get(jugada).get(0);
                        } else {
                            ganador = desempateStraight(jugadas.get(jugada));
                        }
                    }
                }
                break;
                case "threeOfKind": {//Listo
                    jugadaGanadora = Jugada.ThreeOfKind;
                    if (jugadas.get(jugada) != null) {
                        if (jugadas.get(jugada).size() == 1) {
                            ganador = jugadas.get(jugada).get(0);
                        } else {
                            ganador = desempateThreeOfKind(jugadas.get(jugada));
                        }
                    }
                }
                break;
                case "twoPair": {//Listo
                    jugadaGanadora = Jugada.TwoPair;
                    if (jugadas.get(jugada) != null) {
                        if (jugadas.get(jugada).size() == 1) {
                            ganador = jugadas.get(jugada).get(0);
                        } else {
                            ganador = desempateTwoPair(jugadas.get(jugada));
                        }
                    }
                }
                break;
                case "onePair": {//Listo
                    jugadaGanadora = Jugada.OnePair;
                    if (jugadas.get(jugada) != null) {
                        if (jugadas.get(jugada).size() == 1) {
                            ganador = jugadas.get(jugada).get(0);
                        } else {
                            ganador = desempateOnePair(jugadas.get(jugada));
                        }
                    }
                }
                break;
                case "highCard": {
                    jugadaGanadora = Jugada.HighCard;
                    ganador = desempateHighCard(jugadas.get(jugada));
                }
                break;
                default:
                    return;
            }
        }
    }

    private Jugador desempateSraightFlush(List<Jugador> ganadores) {
        int high = 0;
        for (Jugador jugador : ganadores) {
            if (jugador.highCard() > high) {
                high = jugador.highCard();
            }
        }
         List<Jugador> ganadoresHigh = buscarGanadorPorHigh(ganadores, high);
        if (ganadoresHigh.size() == 1) {
            return ganadoresHigh.get(0);
        }
        Jugador ganador = buscarGanadorPorPinta(ganadores);
        return ganador;
    }

    private Jugador desempateHighCard(List<Jugador> ganadores) {
        Set<Integer> highs = new HashSet<>();
        Jugador ganador = buscarGanadorCartaAltaExcluyendo(ganadores, highs);
        if (ganador != null) {
            return ganador;
        } else {
            ganador = buscarGanadorCartaAltaExcluyendo(ganadores, highs);
            if (ganador != null) {
                return ganador;
            } else {
                ganador = buscarGanadorCartaAltaExcluyendo(ganadores, highs);
                if (ganador != null) {
                    return ganador;
                } else {
                    ganador = buscarGanadorCartaAltaExcluyendo(ganadores, highs);
                    if (ganador != null) {
                        return ganador;
                    } else {
                        ganador = buscarGanadorCartaAltaExcluyendo(ganadores, highs);
                        if (ganador != null) {
                            return ganador;
                        } else {
                            ganador = buscarGanadorPorPinta(ganadores);
                            return ganador;
                        }
                    }
                }
            }
        }
    }

    private Jugador desempateOnePair(List<Jugador> ganadores) {
        int highPair = 0;
        for (Jugador g : ganadores) {
            for (Integer key : g.getRepeticiones().keySet()) {
                if (highPair > key) {
                    highPair = key;
                }
            }
        }
        List<Jugador> ganadoresPair = new ArrayList<>();
        for (Jugador g : ganadores) {
            for (Integer key : g.getRepeticiones().keySet()) {
                if (highPair == key) {
                    ganadoresPair.add(g);
                }
            }
        }
        if (ganadoresPair.size() == 1) {
            return ganadoresPair.get(0);
        } else {
            Set<Integer> highs = new HashSet<>();
            Jugador ganador = buscarGanadorCartaAltaExcluyendo(ganadores, highs);
            if (ganador != null) {
                return ganador;
            } else {
                ganador = buscarGanadorCartaAltaExcluyendo(ganadores, highs);
                if (ganador != null) {
                    return ganador;
                } else {
                    ganador = buscarGanadorCartaAltaExcluyendo(ganadores, highs);
                    if (ganador != null) {
                        return ganador;
                    } else {
                        ganador = buscarGanadorPorPinta(ganadores);
                        return ganador;
                    }
                }
            }
        }
    }

    private Jugador buscarGanadorCartaAltaExcluyendo(List<Jugador> ganadores, Set<Integer> highs) {
        int high = 0;
        Set<Integer> exclude = null;
        for (Jugador jugador : ganadores) {
            exclude = new HashSet<>(highs);
            exclude.addAll(jugador.getRepeticiones().keySet());
            if (jugador.highCard(exclude) > high) {
                high = jugador.highCard(exclude);
            }
        }
        List<Jugador> ganadoresHigh = new ArrayList<>();
        for (Jugador g : ganadores) {
            exclude = new HashSet<>(highs);
            exclude.addAll(g.getRepeticiones().keySet());
            if (g.highCard(exclude) == high) {
                ganadoresHigh.add(g);
            }
        }
        if (ganadoresHigh.size() == 1) {
            return ganadoresHigh.get(0);
        }
        highs.add(high);
        return null;
    }

    private Jugador desempateStraight(List<Jugador> ganadores) {
        int high = 0;
        for (Jugador jugador : ganadores) {
            if (jugador.highCard() > high) {
                high = jugador.highCard();
            }
        }
        List<Jugador> ganadoresHigh = buscarGanadorPorHigh(ganadores, high);
        if (ganadoresHigh.size() == 1) {
            return ganadoresHigh.get(0);
        }
        Jugador ganador = buscarGanadorPorValorPinta(ganadores);
        return ganador;
    }

    private Jugador desempateFlush(List<Jugador> ganadores) {
        int high = 0;
        for (Jugador jugador : ganadores) {
            if (jugador.highCard() > high) {
                high = jugador.highCard();
            }
        }
        List<Jugador> ganadoresHigh = buscarGanadorPorHigh(ganadores, high);
        return ganadoresHigh.get(0);

    }

    private Jugador desempateFullHouse(List<Jugador> ganadores) {
        return desempateTrio(ganadores);
    }

    private Jugador desempateFourOfKind(List<Jugador> ganadores) {
        int mayor = 0;
        for (Jugador g : ganadores) {
            for (Integer key : g.getRepeticiones().keySet()) {
                if (key > mayor) {
                    mayor = key;
                }
                break;
            }
        }
        for (Jugador g : ganadores) {
            for (Integer key : g.getRepeticiones().keySet()) {
                if (key == mayor) {
                    return g;
                }
            }
        }
        return null;
    }

    private List<Jugador> buscarGanadorPorHigh(List<Jugador> ganadores, int valor) {
        List<Jugador> ganadoresHigh = new ArrayList<>();
        for (Jugador g : ganadores) {
            if (g.highCard() == valor) {
                ganadoresHigh.add(g);
            }
        }
        return ganadoresHigh;
    }

    private List<Jugador> buscarGanadorPorValorIndex(List<Jugador> ganadores, int index) {
        int high = 0;
        for (Jugador jugador : ganadores) {
            if (jugador.getMano().get(index).getValor() > high) {
                high = jugador.highCard();
            }
        }
        List<Jugador> ganadoresHigh = new ArrayList<>();
        for (Jugador g : ganadores) {
            if (g.highCard() == high) {
                ganadoresHigh.add(g);
            }
        }
        return ganadoresHigh;
    }

    private Jugador buscarGanadorPorPinta(List<Jugador> ganadores) {
        int high = 0;
        for (Jugador jugador : ganadores) {
            if (jugador.sumatoriaPinta() > high) {
                high = jugador.sumatoriaPinta();
            }
        }
        for (Jugador g : ganadores) {
            if (g.sumatoriaPinta() == high) {
                return g;
            }
        }
        return null;
    }
    
    private Jugador buscarGanadorPorValorPinta(List<Jugador> ganadores) {
        int high = 0;
        for (Jugador jugador : ganadores) {
            if (jugador.sumatoriaValorPinta()> high) {
                high = jugador.sumatoriaValorPinta();
            }
        }
        for (Jugador g : ganadores) {
            if (g.sumatoriaValorPinta() == high) {
                return g;
            }
        }
        return null;
    }

    private Jugador desempateTwoPair(List<Jugador> ganadores) {
        List<Integer> sumatorias = new ArrayList<>();
        int sum = 0;
        for (Jugador g : ganadores) {
            for (Integer key : g.getRepeticiones().keySet()) {
                sum += key * g.getRepeticiones().get(key);
            }
            sumatorias.add(sum);
            sum = 0;
        }
        int high = 0;
        for (int i = 0; i < sumatorias.size(); i++) {
            if (high > sumatorias.get(i)) {
                high = sumatorias.get(i);
            }
        }
        List<Jugador> ganadoresa = new ArrayList<>();
        for (Jugador g : ganadores) {
            for (Integer key : g.getRepeticiones().keySet()) {
                sum += key * g.getRepeticiones().get(key);
            }
            if (high == sum) {
                ganadoresa.add(g);
            }
        }
        if (ganadoresa.size() == 1) {
            return ganadoresa.get(0);
        }
        Jugador ganador = buscarGanadorPorCartaAltaTwoPair(ganadores);
        return ganador;
    }

    private Jugador desempateThreeOfKind(List<Jugador> ganadores) {
        return desempateTrio(ganadores);
    }

    private Jugador desempateTrio(List<Jugador> ganadores) {
        int high = 0;
        for (Jugador g : ganadores) {
            for (Integer key : g.getRepeticiones().keySet()) {
                if (g.getRepeticiones().get(key) == 3) {
                    if (key > high) {
                        high = key;
                    }
                }
            }
        }
        for (Jugador g : ganadores) {
            for (Integer key : g.getRepeticiones().keySet()) {
                if (key == high) {
                    return g;
                }
            }
        }
        return null;
    }

    private Jugador buscarGanadorPorCartaAltaTwoPair(List<Jugador> ganadores) {
        int high = 0;
        for (Jugador jugador : ganadores) {
            if (jugador.highCard(jugador.getRepeticiones().keySet()) > high) {
                high = jugador.highCard(jugador.getRepeticiones().keySet());
            }
        }
        List<Jugador> ganadoresa = new ArrayList<>();
        for (Jugador g : ganadores) {
            if (g.highCard(g.getRepeticiones().keySet()) == high) {
                ganadoresa.add(g);
            }
        }
        if (ganadoresa.size() == 1) {
            return ganadoresa.get(0);
        }
        Jugador ganador = buscarGanadorPorPinta(ganadores);
        return ganador;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public Baraja getBaraja() {
        return baraja;
    }

    public void setBaraja(Baraja baraja) {
        this.baraja = baraja;
    }

    public int getNumeroJugadores() {
        return numeroJugadores;
    }

    public void setNumeroJugadores(int numeroJugadores) {
        this.numeroJugadores = numeroJugadores;
    }

    public Jugador getGanador() {
        return ganador;
    }

    public void setGanador(Jugador ganador) {
        this.ganador = ganador;
    }

    public Jugada getJugadaGanadora() {
        return jugadaGanadora;
    }

    public void setJugadaGanadora(Jugada jugadaGanadora) {
        this.jugadaGanadora = jugadaGanadora;
    }
    
    

}
