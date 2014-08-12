/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trabajos;

import java.util.Scanner;

/**
 *
 * @author Docente 17082011
 */
public class BarcoPasajero extends Barco {
    private String pasajeros[];
    private int cuantosPasajeros;
    private double precio;
    
    public BarcoPasajero(String nombre, double p, int max) {
        super(nombre);
        pasajeros = new String[max];
        cuantosPasajeros = 0;
        precio = p;
    }

    @Override
    void agregarElemento() {
        Scanner lea = new Scanner(System.in);
        if(cuantosPasajeros < pasajeros.length){
            pasajeros[cuantosPasajeros++] = lea.next();
        }
    }

    @Override
    double vaciarCobrar() {
        double t = cuantosPasajeros * precio;
        cuantosPasajeros = 0;
        return t;
    }
    
    public void listar(){
        for(String pasajer : pasajeros)
            System.out.println("-" + pasajer);
    }

    @Override
    public String toString() {
        return super.toString() + " cantidad de pasajeros: " + 
                cuantosPasajeros; 
    }
    
    
}
