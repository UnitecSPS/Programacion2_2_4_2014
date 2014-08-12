/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trabajos;

import java.util.Date;

/**
 *
 * @author Docente 17082011
 */
public abstract class Barco {
    protected String nombre;
    protected Date fecha;

    public Barco(String nombre) {
        this.nombre = nombre;
        fecha = new Date();
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "Barco{" + "nombre=" + nombre + ", fecha=" + fecha + '}';
    }
    
    abstract void agregarElemento();
    abstract double vaciarCobrar();
}
