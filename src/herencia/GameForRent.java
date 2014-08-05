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
    public GameForRent(int code, String t, double p, String co){
        super(code,t,p);
        console = co;
    }
    
    /**
     * REDEFINIR Rent:
     * Se cobra el precio y se lo puede llevar por 7 dias. 
     * igual se evalua la cantidad de copias que se tenga
     * dias adicionales se cobra el 60% del precio
     */
    @Override
    public double rent(int days) {
        
        if(copies>0){
            double total = price;
            if(days > 7)
                total += (days-7)*(price*0.6);
            
            copies--;
            return total;
        }
        return 0;
    }
    
    /**
      * REDEFINIR toString
     * Para retornar todo lo que ya retorna el padre + la consola + el texto
     *   GAME
     */
    @Override
    public String toString(){
        return "GAME " + super.toString() + "-" + console;
    }
    
    @Override
    public void quienSoy() {
        System.out.println("SOY UN GAMER");
    }
    
}
