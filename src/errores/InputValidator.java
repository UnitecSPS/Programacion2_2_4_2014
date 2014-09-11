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
public class InputValidator {

    public static void main(String[] args) {
        Scanner lea = new Scanner(System.in);
        
        do{
            try{
                int x = lea.nextInt();
                break;
            }
            catch(InputMismatchException e){
                System.out.println("Ingrese un numero correcto");
                lea.next();
            }
        }while(true);
    }
}
