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
    private String estado; 
    
    public MovieForRent(int c,String t,double p){
        super(c,t,p);
        actors = new ArrayList<>();
        estado = "ESTRENO";
    }
    
    public void addActor(String name){
        actors.add(name);
    }
    
    public void printActors(){
        for(String actor : actors){
            System.out.println("-"+actor);
        }
    }
    
    /**
     * 1-Vamos a ver si la fecha de ahora con respecto
     *  a la publishAt es de mas de 5 meses
     * 2-si es de mas de 5 meses cambiaremos el estado a 
     *  NORMAL
     */
    public void evaluarEstado(){
        
    }
    
    /**
     * REDFEFINIR rent
     *  1- SIempre verificar las copias
     *  2- segun el estado se le cobrara
     *     - Se cobra solo el precio.
     *     * - Si es ESTRENO solo se da 3 dias gratis
     *     * - SI es NORMAL solo se da por 5 dias gratis
     *      - los dias adicionales se cobran, cada dia * 50% del precio.
     * 3-restar una copia
     */
    
    /**
     * REDEFINIR toString
     * Para retornar todo lo que ya retorna el padre + el estado de la movie +
     *  MOVIE
     */
    
    @Override
    public void quienSoy(){
        System.out.println("SOY UNA MOVIE");
    }
}
