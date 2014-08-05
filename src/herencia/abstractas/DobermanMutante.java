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
public class DobermanMutante extends Doberman {

    @Override
    public void hablar() {
        super.hablar(); 
        System.out.println("Grrr");
    }
    
}
