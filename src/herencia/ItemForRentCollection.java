/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package herencia;

import java.util.ArrayList;

/**
 *
 * @author Docente 17082011
 */
public class ItemForRentCollection {
    public static void main(String[] args) {
        ArrayList<ItemForRent> items = new ArrayList<>();
        items.add(new MovieForRent(1, "Harry Potter", 0));
        items.add(new GameForRent(2, "FIFA", 0, 1));
        //Integer e;
       // e.equals(e)
        if( items.contains( ItemForRent.getInstance(1) ) ){
            System.out.println("SI ENTRO");
        }
    }
}
