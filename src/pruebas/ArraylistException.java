/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pruebas;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Docente 17082011
 */
public class ArraylistException {
    ArrayList<Integer> numeros = new ArrayList<>();
    
    public void updateFromKeyboard(int pos){
        Scanner lea;
        
        do{
            lea = new Scanner(System.in);
            try{
                int valor = lea.nextInt();
                numeros.set(pos, valor);
                break;
            }
            catch(InputMismatchException e){
                System.out.println("Ingrese un valor valido");
                lea.next();
            }
            catch(IndexOutOfBoundsException e){
                System.out.println(pos+" fuera de rango");
                break;
            }
            catch(Exception e){
                System.out.println("Error: " + e.getMessage());
                break;
            }
            finally{
                lea.close();
            }
        }while(true);
    }
}
