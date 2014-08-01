/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package herencia;

/**
 *
 * @author Docente 17082011
 */
public class TestForRen {
    public static void main(String[] args) {
        MovieForRent movie = new MovieForRent(1,"Harry Potter",10);
        System.out.println(movie);
        movie.code =2;
        
        ItemForRent ifr0 = new ItemForRent();
        ifr0.quienSoy();
        
        //UPCASTING-----------------
        ItemForRent ifr = new MovieForRent(2,"Avatar",9.99);
        ifr.quienSoy();
        
        ItemForRent ifr2 = new GameForRent();
        ifr2.quienSoy();
        
        //DOWNCASTING----------------------
        //1- INDIRECTO
        if( ifr instanceof MovieForRent){
            movie = (MovieForRent)ifr;
            movie.title = "TITANIC";
            System.out.println(ifr.title);
            movie.addActor("Leonardo DiCaprio");
            movie.printActors();
        }
        //2- DIRECTO
        ((MovieForRent)ifr).addActor("Kate Winslet");
        ((MovieForRent)ifr).printActors();
    }
}
