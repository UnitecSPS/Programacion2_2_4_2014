/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package herencia;

import java.util.ArrayList;
import java.util.Date;

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
        if(estado.equals("ESTRENO")){
            Date now = new Date();
            long diff = now.getTime() - publishAt.getTime();
            double segs = diff/100;
            double mins = segs/60;
            double hours = mins/60;
            double days = hours/24;
            double months = days/30;

            if(months > 5)
                estado = "NORMAL";
        }
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
    @Override
    public final double rent(int days) {
        
        if(copies>0){
            double total = price;
            if(estado.equals("ESTRENO") && days > 3)
                total += (days-3)*(price*0.5);
            else if(estado.equals("NORMAL") && days > 5)
                total += (days-5)*(price*0.5);
            copies--;
            return total;
        }
        return 0;
    }

    
    
    /**
     * REDEFINIR toString
     * Para retornar todo lo que ya retorna el padre + el estado de la movie +
     *  MOVIE
     */
    @Override
    public String toString(){
        return "MOVIE " + super.toString() + "-" + estado;
    }
    
    @Override
    public final void quienSoy(){
        System.out.println("SOY UNA MOVIE");
    }
    
    
}
