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
    public static final int CITA_PENDIENTE = 0;
    public static final int CITA_ATENDIDA = 1;
    public static final int CITA_CANCELADA = 2;
    
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
    
    public void listarPacientes()throws IOException{
        rpacs.seek(0);
        while(rpacs.getFilePointer() < rpacs.length()){
            int cod = rpacs.readInt();
            String np = rpacs.readUTF();
            Date nac = new Date(rpacs.readLong());
            char gen = rpacs.readChar();
            int ncitas = rpacs.readInt();
            System.out.printf("- %d, %s Nacido en %s Genero: %c, Citas hechas: %d%n",
                    cod,np,nac,gen,ncitas);
        }
    }
    
//     * paciente folder/cita_numcita.med
// * --------------------------------
// * int cod dr
// * long fecha
// * string sintomas
// * double monto
// * int estado
// * string receta
    
    public boolean crearCita(int cp,int dr,Date fecha,String sin)throws IOException{
        if(pacienteExiste(cp)){
            if(doctorDisponible(dr)){
                RandomAccessFile rcita = crearCitaFile(cp);
                //doctor
                rcita.writeInt(dr);
                //fecha
                rcita.writeLong(fecha.getTime());
                //sintomas
                rcita.writeUTF(sin);
                //monto
                rcita.writeDouble(0);
                //estado
                rcita.writeInt(CITA_PENDIENTE);
                //reseta
                rcita.writeUTF("Sin Receta");
                //close
                rcita.close();
            }
            else{
                System.out.println("DR no disponible o no existe.");
            }
        }
        else{
            System.out.println("Paciente no registrado");
        }
        
        return false;
    }

    private boolean pacienteExiste(int cp)throws IOException {
        rpacs.seek(0);
        while(rpacs.getFilePointer() < rpacs.length()){
            int cod = rpacs.readInt();
            if(cod == cp)
                return true;
            rpacs.readUTF();
            rpacs.skipBytes(14);
        }
        return false;
    }

    public boolean doctorDisponible(int dr)throws IOException {
        rdocs.seek(0);
        
        while(rdocs.getFilePointer() < rdocs.length()){
            int cod = rdocs.readInt();
            long pos = rdocs.getFilePointer();
            rdocs.readUTF();
            rdocs.readUTF();
            rdocs.readDouble();
            if(  rdocs.readBoolean() && cod == dr ){
                rdocs.seek(pos);
                return true;
            }
        }
        return false;
    }

    /**
     * LLamar esta funcion solo si el paciente existe, despues de haber
     * llamado la funcion pacienteExiste, si no
     * puede que falle
     * @param cp
     * @return 
     */
    private RandomAccessFile crearCitaFile(int cp)throws IOException {
        String np = rpacs.readUTF();
        rpacs.skipBytes(10);
        int nc = rpacs.readInt()+1;
        rpacs.seek(rpacs.getFilePointer()-4);
        rpacs.writeInt(nc);
        
        System.out.println("Creando Cita para " + np + " ....");
        String foldername = ROOT_FOLDER+"/"+np+" "+cp+"/cita_"+nc+".med";
        return new RandomAccessFile(foldername, "rw");
    }
            
}
