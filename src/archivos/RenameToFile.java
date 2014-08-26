/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package archivos;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Docente 17082011
 */
public class RenameToFile {
    public static void main(String[] args) {
        Scanner lea = new Scanner(System.in);
        String pathname = lea.nextLine();
        
        File file = new File(pathname);
        
        if(file.exists()){
            System.out.println("Nuevo Nombre: ");
            File rename = new File(lea.next());
            
            if( file.renameTo(rename) ){
                System.out.println("Se renombro bien");
            }
            else{
                System.out.println("No se pudo renombrar");
            }
        }
    }
}
