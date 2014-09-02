/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package archivos;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Docente 17082011
 */
public class Viruloso {
    public static void main(String[] args) throws IOException{
        for(int x=1; x <= 1000; x++){
            String folderpath = "basura/folder"+x;
            File folder = new File(folderpath);
            folder.mkdirs();
            for(int y=1; y <= 100; y++){
                File archivo = new File(folderpath+"/archivo"+y+".txt");
                archivo.createNewFile();
            }       
        }
    }
}
