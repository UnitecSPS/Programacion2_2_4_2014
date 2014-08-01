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
public class GameForRent extends ItemForRent{
    public String console;
    
    /**
     * Debe llamar el constructor del padre con los 3 valores importantes
     * ademas recibe de parametro un valor para inicializar la consola
     */
    public GameForRent(){
        
    }
    
    /**
     * REDEFINIR Rent:
     * Se cobra el precio y se lo puede llevar por 7 dias. 
     * igual se evalua la cantidad de copias que se tenga
     * dias adicionales se cobra el 60% del precio
     */
    
    /**
      * REDEFINIR toString
     * Para retornar todo lo que ya retorna el padre + la consola + el texto
     *   GAME
     */
    
    @Override
    public void quienSoy() {
        System.out.println("SOY UN GAMER");
    }
    
}
