/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package herencia.abstractas;

import java.util.ArrayList;

/**
 *
 * @author Docente 17082011
 */
public class Granja {
    public static void main(String[] args) {
        ArrayList<Animal> animales = new ArrayList<>();
        animales.add(new Pollo(4, "Pollo") );
        animales.add(new Vaca());
        //una vaca costumizable bajo demanda
        animales.add(new Vaca(){
           @Override
            public void hablar() {
                System.out.println("Muu SOfisticada Miuuuuu");
            } 
        });
        animales.add(new Doberman());
        animales.add(new DobermanMutante());
        //on demand!
        animales.add(new Animal(4,"Java") {
            //hasta una funcion nueva!
            public void hable(){
                System.out.println("Q$##%$&%#$# desde hable");
            }
            
            @Override
            public void hablar() {
                hable();
            }

            @Override
            public void queComo() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        for(Animal ani : animales){
            ani.hablar();
            if(ani instanceof Perro){
                System.out.println("Es Animal");
            }
            else if(ani instanceof Vaca){
                
            }
            //***
        }
    }
}
