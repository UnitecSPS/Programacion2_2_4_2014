/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package repaso;

/**
 *
 * @author Docente 17082011
 */
public class Patito {
    public String raza;

    public Patito(String raza) {
        this.raza = raza;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }
    
    public void nadar(){
        System.out.println("Nadando en el agua azul");
    }
    
}
