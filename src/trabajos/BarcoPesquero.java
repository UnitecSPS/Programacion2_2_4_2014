/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trabajos;

/**
 *
 * @author Docente 17082011
 */
public class BarcoPesquero extends Barco {
    private int pecesCapturados;
    private final double precioPorPescado;
    
    public BarcoPesquero(String nombre, double p) {
        super(nombre);
        pecesCapturados = 0;
        precioPorPescado = p;
    }

    @Override
    void agregarElemento() {
        pecesCapturados++;
    }

    @Override
    double vaciarCobrar() {
        double t = pecesCapturados * precioPorPescado;
        pecesCapturados = 0;
        return t;
    }

    @Override
    public String toString() {
        return super.toString() + " peces capturados: " +
                pecesCapturados; 
    }
    
    
}
