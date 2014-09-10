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
public class Lista {
    private Nodo inicio = null;
    
    public void addNodo(Nodo obj){
        if(inicio == null){
            inicio = obj;
        }
        else{
            Nodo tmp = inicio;
            
            while(tmp.next != null ){
                tmp = tmp.next;
            }
            tmp.next = obj;
        }
    }
}
