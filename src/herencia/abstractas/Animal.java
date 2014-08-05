/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package herencia.abstractas;

/**
 *
 * @author Docente 17082011
 */
public abstract class Animal {
    protected int patas;
    protected String raza;

    public Animal(int patas, String raza) {
        this.patas = patas;
        this.raza = raza;
    }
    
    public abstract void hablar();
    
    public abstract void queComo();

    @Override
    public String toString() {
        return "{" + "patas=" + patas + ", raza=" + raza + '}';
    }
    
    
}
