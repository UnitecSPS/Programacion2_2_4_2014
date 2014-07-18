/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package repaso;
import java.util.ArrayList;
/**
 *
 * @author Docente 17082011
 */
public class TestArraylist {
    public static void main(String[] args) {
        //String arr[] = new String[long]
        ArrayList<String> cadenas = new ArrayList<>();
        //arr[pos] = valor
        cadenas.add("Carlos");
        cadenas.add("Luis");
        //recorrer
        for(int x=0; x < cadenas.size(); x++)
            System.out.println("-"+cadenas.get(x));
        for(String c : cadenas)
            System.out.println("-"+c);
        //cadenas.add(new Patito());
        //arr[pos]
        //arr.length
        System.out.println("size = " + cadenas.size());
        //remover
        cadenas.remove("Carlos");
        System.out.println(cadenas.get(0));
        //cadenas.clear();
        
        if( cadenas.contains("Luisa") )
            System.out.println("ESTa!!!");
    }
}
