/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package memoria;

/**
 *
 * @author Docente 17082011
 */
public class MemoriaTest {
    public static void main(String[] args) {
        Nodo a = new Nodo("Messi");
        Nodo b = a;//new Nodo("Paolo");
        b.nombre = "Gochez";
        System.out.println(a.nombre);
        System.out.println(b.nombre);
        
        if( a == b )
            System.out.println("ES CIERTO!");
        
        String c1 = "hola";
        String c2 = "hola";
        
        if(c1.equals(c2))
            System.out.println("Son iguales");
        else
            System.out.println("No son iguales");
    }
}
