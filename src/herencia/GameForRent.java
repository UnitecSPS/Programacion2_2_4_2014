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
public final class GameForRent extends ItemForRent{
    public int console;
    //enumaracion de consolas disponibles
    public static final int UNKNOWN = 0;
    public static final int PS3 = 1;
    public static final int XBOX = 2;
    public static final int WII = 3;

     /* Debe llamar el constructor del padre con los 3 valores importantes
     * ademas recibe de parametro un valor para inicializar la consola
     */
    public GameForRent(int code, String t, double p, int co){
        super(code,t,p);
        setConsole(co);
    }
    
    /**
     * REDEFINIR Rent:
     * Se cobra el precio y se lo puede llevar por 7 dias. 
     * igual se evalua la cantidad de copias que se tenga
     * dias adicionales se cobra el 60% del precio
     */
    @Override
    public double rent(int days) {
        //PS3 = 1; No puedo!
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

    private void setConsole(int co) {
        switch(co){
            case PS3:
            case WII:
            case XBOX:
                console = co;
            default:
                console = UNKNOWN;
        }
    }
        
}
