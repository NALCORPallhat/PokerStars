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
public enum Pinta  {
    
    Pica("Picas",1),
    Corazon("Corazones",2),
    Trebol("Tr\u00e9boles",3),
    Diamante("Diamantes",4);
    
    private String nombre;
    private int valor;

    private Pinta(String n, int v) {
        nombre=n;
        valor=v;
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
    
    public static Pinta getPintaPorValor(int v){
        switch(v){
            case 4: return Diamante;
            case 3: return Trebol;
            case 2: return Corazon;
            case 1: return Pica;
        }
        return null;
    }
    
        
        
    
    
}
