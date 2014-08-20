/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package errores;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Docente 17082011
 */
public class TestException {
    public static void main(String[] args) {
        
        try{
            a();
            System.out.println("Terminando el Try");
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Te pasaste de los limites del arreglo, " +
            e.getMessage() + " no existe");
            //e.printStackTrace();
        }
        catch(InputMismatchException e){
            System.out.println("Ingresaste un valor erroneo");
        }
        catch(Exception e){
            System.out.println("ERROR: " + e.getMessage());
            //if( e instanceof ArrayIndexOutOfBoundsException)
        }
        finally{
            System.out.println("Se corrio el finally wojooo");
        }
        
        
        System.out.println("TERMINO MAIN!");
    }

    private static void a() {
        b();
        System.out.println("TERMINO A!");
    }

    private static void b() {

        Scanner lea  = new Scanner(System.in);
        int pos = lea.nextInt();
        int arr[] = {1,0};
        int y = arr[pos];
        y = 5/y;
      
        System.out.println("TERMINO B!");
    }
}
