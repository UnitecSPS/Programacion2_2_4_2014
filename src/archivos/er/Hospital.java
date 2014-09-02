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
import java.util.Date;

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
    
    private int getCodigo(long offset){
        int code;
        try (RandomAccessFile rcods = new RandomAccessFile(ROOT_FOLDER+"/codigos.med", "rw")) {
            rcods.seek(offset);
            code = rcods.readInt();
            rcods.seek(offset);
            rcods.writeInt(code+1);
        }
        catch(IOException e){
            return -1;
        }
        return code;
    }
    
    private int getCodigoDrNuevo(){
        return getCodigo(0);
    }
    
    private int getCodigoPacNuevo(){
        return getCodigo(4);
    }
    
    public void addDoctor(String no,EspecialiadMedica em, double mon) throws IOException{
        rdocs.seek(rdocs.length());
        //codigo
        rdocs.writeInt(getCodigoDrNuevo());
        //nombre
        rdocs.writeUTF(no);
        //especialidad
        rdocs.writeUTF(em.name());
        //monto
        rdocs.writeDouble(mon);
        //dispo
        rdocs.writeBoolean(true);
    }
    
    public void listarDrsDisponibles()throws IOException{
        rdocs.seek(0);
        
        while(rdocs.getFilePointer() < rdocs.length()){
            int cod = rdocs.readInt();
            String no = rdocs.readUTF();
            String es = rdocs.readUTF();
            double m = rdocs.readDouble();
            if( rdocs.readBoolean() )
                System.out.printf("*%d - %s - %s Costo Consulta Lps. %.1f\n",
                        cod, no, es, m);
        }
    }
    
    public void addPaciente(String np,Date fechanac,char gen)throws IOException{
        rpacs.seek(rpacs.length());
        //codigo
        int code = getCodigoPacNuevo();
        rpacs.writeInt(code);
        //nombre
        rpacs.writeUTF(np);
        //fecha
        rpacs.writeLong(fechanac.getTime());
        //genero
        rpacs.writeChar(gen);
        //num citas
        rpacs.writeInt(0);
        //crear folder
        String foldername=np+" "+code;
        File folder = new File(ROOT_FOLDER+"/"+foldername);
        folder.mkdir();
    }
            
}
