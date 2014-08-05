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
public class Pollo extends Animal {

    public Pollo(int patas, String raza) {
        super(patas, raza);
    }
    
    @Override
    public void hablar() {
        //super.hablar();
        System.out.println("Pio Pio Pio");
    }

    @Override
    public void queComo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
