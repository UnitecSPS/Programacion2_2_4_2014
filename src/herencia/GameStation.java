/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package herencia;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Docente 17082011
* 1- Hacer un Arraylist para guardar movies o games
* 2- Hacer un menu con las siguientes opciones:
*    a. AGREGAR ITEM. Se pide el tipo MOVIE o GAME y en base a eso
*       se piden los valores necesarios para formar un nuevo objeto
*       segun el tipo y agregarlo al arreglo. Validar que el codigo
*       sea UNICO.
*     TIP:  BUSCAR(int  code)**** Me retorna la posicion o el objeto 
*        si existe un item con ese codigo
*  2-Rentar. Pedir el codigo, y si este existe, pedir los dias que se
*    lo va llevar y luego imprimir el total a pagar
*  3- Listar todos los items de la tienda. Al final poner un dato 
*    estadistico de cuantos son movies y cuantos games. 
*     TIP: Esto ultimo se logra con el apoyo del instanceof
*   4- EValuar estado. Recorre toda la coleccion y mandan a llamar la
*      funcion evaluarEstado de todos los objetos MovieForRent que hay
*     en el arreglo. TIP: Utilizar instanceof
*/

public class GameStation {
    static ArrayList<ItemForRent> items;
    static Scanner lea = new Scanner(System.in);
    
    public static void main(String[] args) {
        items = new ArrayList<>();
        int op;
        
        do{
            System.out.println("1-AGREGAR");
            System.out.println("2-RENTAR");
            System.out.println("3-LISTAR");
            System.out.println("4-EVALUAR ESTADO");
            System.out.println("5-DEVOLVER COPIAS");
            System.out.println("6-SALIR");
            System.out.print("Escoja Opcion: ");
            op = lea.nextInt();
            
            switch(op){
                case 1:
                    addItem();
                    break;
                case 2:
                    rentItem();
                    break;
                case 3:
                    list();
                    break;
                case 4:
                    evaluate();
                    break;
                case 5:
                    returnItems();
                    break;
            }
        }while(op!=6);
    }

    private static ItemForRent search(int cod){
        for(ItemForRent item : items){
            if(item.getCode() == cod)
                return item;
        }
        return null;
    }
    
    private static void addItem() {
        String tipo = lea.next();
        
        if(tipo.equals("MOVIE")){
            items.add(new MovieForRent(1,"title", 100) );
        }
        else{
            items.add(new GameForRent(1, "title", 0, 0));
        }
    }

    private static void rentItem() {
        int code = lea.nextInt();
        ItemForRent item = search(code);
        
        if(item != null){
            int dias = lea.nextInt();
            System.out.println("Total: " + 
                    item.rent(dias));
        }
    }

    private static void list() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void evaluate() {
        for(ItemForRent item : items){
            if(item instanceof MovieForRent)
                ((MovieForRent)item).evaluarEstado();
        }
    }

    private static void returnItems() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
