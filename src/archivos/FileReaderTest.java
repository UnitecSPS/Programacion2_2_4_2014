/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package archivos;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author Docente 17082011
 */
public class FileReaderTest {
    public static void main(String[] args) {
        Scanner lea = new Scanner(System.in);
        
        do{
            System.out.println("Direccion: ");
            String path = lea.next();
            
            try{
                //1- metodo directo del filereader
                File file = new File(path);
                FileReader fr = new FileReader(file);
                
                char contenido[] = new char[(int)file.length()];
                int bytes = fr.read(contenido);
                 
                System.out.println("Contenidio\n----------------");
                System.out.println(contenido);
                
                System.out.printf("Bytes leidos %d es igual al Size %d\n",
                        bytes,file.length());
                
                //2- metodo con Scanner
                fr = new FileReader(file);
                Scanner lector = new Scanner(fr);
                System.out.println("\nContenido con Scanner\n----------------");
                while(lector.hasNext()){
                    System.out.println(lector.nextLine());
                }
            }
            catch(Exception e){
                System.out.println(e);
            }
        }while(true);
    }
}
