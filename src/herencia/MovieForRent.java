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
public class MovieForRent extends ItemForRent {
    private ArrayList<String> actors;
    
    public MovieForRent(int c,String t,double p){
        super(c,t,p);
        actors = new ArrayList<>();
    }
    
    public void addActor(String name){
        actors.add(name);
    }
    
    @Override
    public void quienSoy(){
        System.out.println("SOY UNA MOVIE");
    }
}
