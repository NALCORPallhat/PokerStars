/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.com.ict.pokerstars;

import java.util.Scanner;
import cl.com.ict.pokerstars.bo.Baraja;
import cl.com.ict.pokerstars.bo.Carta;
import cl.com.ict.pokerstars.bo.Juego;
import cl.com.ict.pokerstars.bo.Jugador;

/**
 *
 * @author Oscar Gutiérrez
 */
public class PokerStars {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanIn =  null;
        String inputString = null;
        System.out.println("POKER STARS");
        try {
            // creates a console object
            scanIn = new Scanner(System.in);
            if (scanIn != null) {
                // read line from the user input
                System.out.println("Ingrese el n\u00famero de jugadores (Debe ser menor o igual a 10 y mayor a 1): ");
                inputString = scanIn.nextLine();
                int N = 0;
                while (true) {
                    try {
                        N = Integer.parseInt(inputString);
                        if(N>=10){
                            System.out.println("Ingrese el n\u00famero de jugadores (Debe ser menor o igual a 10): ");
                            inputString = scanIn.nextLine();
                            continue;
                        }
                        if(N==1){
                            System.out.println("Ingrese el n\u00famero de jugadores (Debe ser mayor a 1): ");
                            inputString = scanIn.nextLine();
                            continue;
                        }
                        break;
                    } catch (NumberFormatException e) {
                    }
                }
                Juego juego = new Juego();
                Baraja baraja = new Baraja();
                juego.setBaraja(baraja);
                juego.setNumeroJugadores(N);
                juego.generarJugadores();
                juego.repartirCartas();
                juego.verificarJugadas();
                juego.buscarGanador();
                for (Jugador j : juego.getJugadores()) {
                    System.out.println("El jugador " + j.getNombre());
                    System.out.println("Obtuvo la siguiente mano: ");
                    for (Carta carta : j.getMano()) {
                        System.out.println(baraja.muestrarInfoCartas(carta));
                    }
                }
                System.out.println("El jugador gandaor fu\u00e9: " + juego.getGanador().getNombre());
                System.out.println("Y gan\u00f3 con la jugada: " + juego.getJugadaGanadora().getNombre());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
