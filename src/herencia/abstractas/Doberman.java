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
public class Doberman extends Perro {
    public Doberman(){
        super("Doberman");
    }

    @Override
    public void hablar() {
        System.out.println("Guf guf guf");
    }

    @Override
    public void queComo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
