/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package archivos;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Docente 17082011
 */
public class FileWriterTest {
    public static void main(String[] args) {
        Scanner lea = new Scanner(System.in);
        do{
            System.out.println("Direccion: ");
            String resp = lea.next();
            System.out.println("Append?: ");
            char app = lea.next().charAt(0);
        
            try{
                FileWriter fw = new FileWriter(resp, Character.toUpperCase(app) == 'S');
                
                System.out.println("Escribe lo que quieras hasta que ingreses EXIT: ");
                
                do{
                    String dato = lea.nextLine();
                    
                    if(dato.equalsIgnoreCase("EXIT"))
                        break;
                    else{
                        fw.write(dato+"\n");
                        fw.flush();
                    }
                    
                }while(true);
                fw.close();
            }
            catch(IOException e){
                System.out.println(e.toString());
            }
                  
        }while(true);
    }
}
