/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pautas;

/**
 *
 * @author Docente 17082011
 */
public abstract class Plan {
    protected int numero;
    protected String nombre;

    public Plan(int numero, String nombre) {
        this.numero = numero;
        this.nombre = nombre;
    }

    public int getNumero() {
        return numero;
    }

    public String getNombre() {
        return nombre;
    }
    
    public abstract double pagoMensual(int cmins, int cmsgs);
    
    public void imprimir(){
        System.out.println(numero + nombre);
    }
}
