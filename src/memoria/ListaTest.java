/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package memoria;

import java.util.Scanner;

/**
 *
 * @author Docente 17082011
 */
public class ListaTest {
    public static void main(String[] args) {
        Lista lista = new Lista();
        int op;
        Scanner lea = new Scanner(System.in);
        
        do{
            System.out.println("1- Agregar Nodo");
            System.out.println("2- Imprimir");
            System.out.println("3- Borrar");
            System.out.println("4- Agregar Enmedio");
            System.out.println("5- Salir");
            System.out.println("Escoja opcion: ");
            op = lea.nextInt();
            
            switch(op){
                case 1:
                    System.out.println("Nombre: ");
                    lista.addNodo( new Nodo(lea.next())  );
                    break;
                case 2:
                    lista.print();
                    break;
                case 3:
                    System.out.println("Nombre a borrar: ");
                    if( lista.delete(lea.next()) )
                        System.out.println("Se borro bien");
                    else
                        System.out.println("No se pudo borrar");
                    break;
                case 4:
                    System.out.println("Nombre de Nodo Nuevo: ");
                    Nodo obj = new Nodo(lea.next());
                    System.out.println("Despues de: ");
                    if( lista.AddInTheMiddle(obj, lea.next()) )
                        System.out.println("Se agrego exitosamente");
                    else
                        System.out.println("No se pudo agregar");
            }
        }while(op!=5);
    }
}
