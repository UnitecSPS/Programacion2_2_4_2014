/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package archivos.er;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Docente 17082011
 * 
 * codigos.med
 * -------------
 * int cod dr
 * int cod pac
 * 
 * doctores.med
 * --------------
 * int cod dr
 * String nombre
 * String espc
 * double monto
 * boolean dispo
 * 
 * pacientes.med
 * ----------------
 * int cod pac
 * String nom pac
 * long fecha nac
 * char genero
 * int numcitas
 * 
 * paciente folder/cita_numcita.med
 * --------------------------------
 * int cod dr
 * long fecha
 * string sintomas
 * double monto
 * int estado
 * string receta
 */
public class Hospital {
    private RandomAccessFile rdocs;
    private RandomAccessFile rpacs;
    public static final String ROOT_FOLDER = "hospital";
    
    public Hospital(){
        initfolderroot();
        try{
            rdocs = new RandomAccessFile(ROOT_FOLDER+"/doctores.med", "rw");
            rpacs = new RandomAccessFile(ROOT_FOLDER+"/pacientes.med", "rw");
            initcodigosfile();
        }
        catch(Exception e){
            System.out.println("Error: " +e.getMessage());
        }
    }

    private void initfolderroot() {
        File f = new File(ROOT_FOLDER);
        f.mkdir();
    }

    private void initcodigosfile() throws IOException {
        RandomAccessFile rcods = new RandomAccessFile(ROOT_FOLDER+"/codigos.med", "rw");
        if(rcods.length() == 0){
            rcods.writeInt(1);
            rcods.writeInt(1);
        }
        rcods.close();
    }
            
}
