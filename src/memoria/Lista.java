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
    
    public void print(){
        Nodo tmp = inicio;
        int cont=1;
        while(tmp != null)
        {
            System.out.println(cont+"-"+tmp.nombre);
            tmp = tmp.next;
            cont++;
        }
    }
    
    public boolean delete(String nombre){
        if(inicio!=null){
            if(inicio.nombre.equals(nombre)){
                inicio = inicio.next;
                return true;
            }
            else{
                Nodo tmp = inicio;
                while(tmp.next!= null){
                    if(tmp.next.nombre.equals(nombre)){
                        tmp.next = tmp.next.next;
                        return true;
                    }
                    else
                        tmp = tmp.next;
                }
            }
        }
        return false;
    }
    
    /**
     * Agrega un NUEVO nodo DESPUES del nodo que contiene el nombre
     * segun el parametro que se recibe. Si no existe el nodo con ese
     * nombreantes, no se agrega nada.
     * @param obj El objeto nuevo
     * @param nombreantes El nombre del nodo antes
     * @return Si se pudo agregar o no.
     */
    public boolean AddInTheMiddle(Nodo obj,String nombreantes){
        Nodo tmp = inicio;
        while(tmp != null){
            if(tmp.nombre.equals(nombreantes)){
                //encontre el nodo
                //1-el nuevo apunta al siguiente del tmp para no perder esa conexion
                obj.next = tmp.next;
                //2-Ahora muevo el siguiente al nuevo
                tmp.next = obj;
                return true;
            }
            else
                tmp = tmp.next;
        }
        return false;
    }
}
