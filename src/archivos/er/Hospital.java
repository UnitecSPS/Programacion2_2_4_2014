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
                return true;
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
            boolean dispo = rdocs.readBoolean();
            
            if( cod == dr ){
                rdocs.seek(pos);
                return dispo;
            }
            
        }
        return false;
    }
    
    public boolean doctorExiste(int dr)throws IOException{
        boolean dispo = doctorDisponible(dr);
        //si dispo es true por ende el dr existe
        //puede ser que dispo es false pero el puntero no esta al final
        //lo que significa que EXISTE pero no esta disponible
        return dispo || rdocs.getFilePointer() < rdocs.length();
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
        String foldername = getCitaPath(np,cp,nc);
        return new RandomAccessFile(foldername, "rw");
    }
    
    public boolean cancelarCita(int codpac,int numcita)throws IOException{
        if(pacienteExiste(codpac)){
            String nombrepac = rpacs.readUTF();
            String foldername = getCitaPath(nombrepac, codpac, numcita);
            File cita = new File(foldername);
            if(cita.exists()){
                RandomAccessFile rcita = new RandomAccessFile(cita,"rw");
                rcita.skipBytes(12);
                rcita.readUTF();
                rcita.readDouble();
                rcita.writeInt(CITA_CANCELADA);
                rcita.close();
                return true;
            }
            else
                System.out.println("Cita No Encontrada");
        }
        else
            System.out.println("No existe ese paciente");
        
        return false;
    }

    private String getCitaPath(String np, int cp, int nc) {
        return ROOT_FOLDER+"/"+np+" "+cp+"/cita_"+nc+".med";
    }

    public boolean atenderCita(int codpac, int numcita, String receta)throws IOException{
        if(pacienteExiste(codpac)){
            String nompac = rpacs.readUTF();
            String citapath = getCitaPath(nompac, codpac, numcita);
            File cita = new File(citapath);
            
            if(cita.exists()){
                try(RandomAccessFile rcita = new RandomAccessFile(cita,"rw")){
                    //leo dr
                    int coddr = rcita.readInt();
                    if(doctorDisponible(coddr)){
                        //imprimo nombre de dr
                        String drname = rdocs.readUTF();
                        System.out.println("Cita Atendida por el Dr " + drname);
                        //saco su monto
                        rdocs.readUTF();//leo la especialidad
                        double monto = rdocs.readDouble();
                        System.out.println("Monto a Pagar: " + monto);
                        //escribo en el archivo de citas
                        rcita.readLong();//leo la fecha
                        rcita.readUTF();//leo los sintomas
                        rcita.writeDouble(monto);//escribo el monto
                        rcita.writeInt(CITA_ATENDIDA);//escribo el nuevo estado
                        rcita.writeUTF(receta);//escribo la receta
                        return true;
                    }
                    else{
                        System.out.println("Dr YA NO ESTA DISPOBIBLE...Cancelando..");
                        rcita.readLong();//fecha
                        rcita.readUTF();//sintomas
                        rcita.readDouble();//monto
                        rcita.writeInt(CITA_CANCELADA);
                    }
                }
            }
            else
                System.out.println("Cita No Existe");
        }
        else
            System.out.println("Paciente No Existe");
        
        return false;
    }
    
    public void printCita(int codpac, int numcita)throws IOException{
        if(pacienteExiste(codpac)){
            String nompac = rpacs.readUTF();
            String citapath = getCitaPath(nompac, codpac, numcita);
            File cita = new File(citapath);
            
            if(cita.exists()){
                System.out.println("Cita de " + nompac);
                RandomAccessFile rcita =  new RandomAccessFile(cita,"rw");
                //leo dr
                int coddr = rcita.readInt();
                if(doctorExiste(coddr)){
                    //el puntero esta antes del nombre
                    String drname = rdocs.readUTF();
                    System.out.println("Cita Atendida por "+coddr+"-" +
                            drname);
                    //fecha
                    System.out.println("Fecha: " + new Date(rcita.readLong()));
                    //sintomas
                    System.out.println("Sintomas: " + rcita.readUTF());
                    //Monto
                    System.out.println("Monto: Lps."+ rcita.readDouble());
                    //estado
                    System.out.print("Estado: ");
                    switch(rcita.readInt()){
                        case CITA_ATENDIDA:
                            System.out.println("Atendida");
                            break;
                        case CITA_CANCELADA:
                            System.out.println("Cancelada");
                            break;
                        default:
                            System.out.println("Pendiente");
                    }
                    //receta
                    System.out.println("Receta: " + rcita.readUTF());
                    rcita.close();
                }
            }
            else
                System.out.println("Cita No Existe");
        }
        else
            System.out.println("Paciente No Existe");
    }
    
    public void citasPendientesPorPaciente(int codpac)throws IOException{
        if(pacienteExiste(codpac)){
            String nompac = rpacs.readUTF();
            //agarrar su folder y listar sus archivos
            File folder = new File(ROOT_FOLDER+"/"+nompac + " " +codpac);
            File citas[] = folder.listFiles();
            for(File cita : citas){
                try(RandomAccessFile rcita = new RandomAccessFile(cita,"rw")){
                    //numero de cita
                    int numcita = getNumCitaPorPath(cita.getName());
                    int coddr = rcita.readInt();
                    long fecha = rcita.readLong();
                    String sintomas = rcita.readUTF();
                    rcita.readDouble();
                    int estado = rcita.readInt();
                    rcita.readUTF();//receta

                    if(estado == CITA_PENDIENTE){
                        //se supone que el dr existe! sino, como se hizo la cita?
                        doctorExiste(coddr);
                        String drname = rdocs.readUTF();
                        //analizar fecha
                        Date now = new Date();
                        String fechaalerta = "A Tiempo";
                        if( now.getTime() > fecha)
                            fechaalerta = "Alerta! La fecha ya paso";
                        
                        System.out.printf("-Cita #%d asignada al Dr. %s, Pactada para %s, Por motivos de %s, %s%n",
                                numcita,drname,new Date(fecha),sintomas,fechaalerta);
                    }
                }
            }
        }
        else
            System.out.println("Paciente No Existe");
    }

    private int getNumCitaPorPath(String name) {
        String vals[] = name.split("_");
        //el val[1] deberia ser numcita.med
        String numero = vals[1].substring(0,vals[1].indexOf('.'));
        return Integer.parseInt(numero);
    }
    
    public void cambiarDisponibilidadDr(int coddr)throws IOException{
        boolean dispo = doctorDisponible(coddr);
        if(dispo){
            //activo, preguntar por desactivar
        }
        else if(rdocs.getFilePointer() < rdocs.length()){
            //desactivado, preguntar por activar
        }
        else{
            System.out.println("Doctor No existe");
        }
    }
    
    public double ingresos()throws IOException{
        double tot =0;
        File folder = new File(ROOT_FOLDER);
        File folderes[] = folder.listFiles();
        for(File pacfolder : folderes){
            if(pacfolder.isDirectory()){
                tot += montoPorPaciente(pacfolder);
            }
        }
        return tot;
    }
    
    private double montoPorPaciente(File pacfolder)throws IOException{
        double tot=0;
        File citas[] = pacfolder.listFiles();
        for(File cita : citas){
            try(RandomAccessFile rcita = new RandomAccessFile(cita,"rw")){
                //nombre de dr y fecha
                rcita.skipBytes(12);
                rcita.readUTF();//sintomas
                double monto = rcita.readDouble();
                int estado = rcita.readInt();
                rcita.readUTF();//receta
                
                if(estado == CITA_ATENDIDA)
                    tot+=monto;
            }
        }
        return tot;
    }
            
}
