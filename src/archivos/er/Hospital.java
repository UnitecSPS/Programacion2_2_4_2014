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
import java.util.Scanner;

/**
 *
 * @author Docente 17082011
 *
 * codigos.med ------------- int cod dr int cod pac
 *
 * doctores.med -------------- int cod dr String nombre String espc double monto
 * boolean dispo
 *
 * pacientes.med ---------------- int cod pac String nom pac long fecha nac char
 * genero int numcitas
 *
 * paciente folder/cita_numcita.med -------------------------------- int cod dr
 * long fecha string sintomas double monto int estado string receta
 */
public class Hospital {

    private RandomAccessFile rdocs;
    private RandomAccessFile rpacs;
    public static final String ROOT_FOLDER = "hospital";
    public static final int CITA_PENDIENTE = 0;
    public static final int CITA_ATENDIDA = 1;
    public static final int CITA_CANCELADA = 2;

    public Hospital() {
        initfolderroot();
        try {
            rdocs = new RandomAccessFile(ROOT_FOLDER + "/doctores.med", "rw");
            rpacs = new RandomAccessFile(ROOT_FOLDER + "/pacientes.med", "rw");
            initcodigosfile();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void initfolderroot() {
        File f = new File(ROOT_FOLDER);
        f.mkdir();
    }

    private void initcodigosfile() throws IOException {
        RandomAccessFile rcods = new RandomAccessFile(ROOT_FOLDER + "/codigos.med", "rw");
        if (rcods.length() == 0) {
            rcods.writeInt(1);
            rcods.writeInt(1);
        }
        rcods.close();
    }

    private int getCodigo(long offset) {
        int code;
        try (RandomAccessFile rcods = new RandomAccessFile(ROOT_FOLDER + "/codigos.med", "rw")) {
            rcods.seek(offset);
            code = rcods.readInt();
            rcods.seek(offset);
            rcods.writeInt(code + 1);
        } catch (IOException e) {
            return -1;
        }
        return code;
    }

    private int getCodigoDrNuevo() {
        return getCodigo(0);
    }

    private int getCodigoPacNuevo() {
        return getCodigo(4);
    }

    public void addDoctor(String no, EspecialiadMedica em, double mon) throws IOException {
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

    public void listarDrsDisponibles() throws IOException {
        rdocs.seek(0);

        while (rdocs.getFilePointer() < rdocs.length()) {
            int cod = rdocs.readInt();
            String no = rdocs.readUTF();
            String es = rdocs.readUTF();
            double m = rdocs.readDouble();
            if (rdocs.readBoolean()) {
                System.out.printf("*%d - %s - %s Costo Consulta Lps. %.1f\n",
                        cod, no, es, m);
            }
        }
    }

    public void addPaciente(String np, Date fechanac, char gen) throws IOException {
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
        String foldername = np + " " + code;
        File folder = new File(ROOT_FOLDER + "/" + foldername);
        folder.mkdir();
    }

    public void listarPacientes() throws IOException {
        rpacs.seek(0);
        while (rpacs.getFilePointer() < rpacs.length()) {
            int cod = rpacs.readInt();
            String np = rpacs.readUTF();
            Date nac = new Date(rpacs.readLong());
            char gen = rpacs.readChar();
            int ncitas = rpacs.readInt();
            System.out.printf("- %d, %s Nacido en %s Genero: %c, Citas hechas: %d%n",
                    cod, np, nac, gen, ncitas);
        }
    }

    public boolean crearCita(int cp, int dr, Date fecha, String sin) throws IOException {
        if (pacienteExiste(cp)) {
            if (doctorDisponible(dr)) {
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
            } else {
                System.out.println("DR no disponible o no existe.");
            }
        } else {
            System.out.println("Paciente no registrado");
        }

        return false;
    }

    private boolean pacienteExiste(int cp) throws IOException {
        rpacs.seek(0);
        while (rpacs.getFilePointer() < rpacs.length()) {
            int cod = rpacs.readInt();
            if (cod == cp) {
                return true;
            }
            rpacs.readUTF();
            rpacs.skipBytes(14);
        }
        return false;
    }

    public boolean doctorDisponible(int dr) throws IOException {
        rdocs.seek(0);

        while (rdocs.getFilePointer() < rdocs.length()) {
            int cod = rdocs.readInt();
            long pos = rdocs.getFilePointer();
            rdocs.readUTF();
            rdocs.readUTF();
            rdocs.readDouble();
            boolean dispo = rdocs.readBoolean();
            if (cod == dr) {
                rdocs.seek(pos);
                return dispo;
            }
        }
        return false;
    }

    public boolean doctorExiste(int dr) throws IOException {
        boolean dispo = doctorDisponible(dr);
        if (dispo == true) {
            return true;
        } else if (rdocs.getFilePointer() < rdocs.length()) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * LLamar esta funcion solo si el paciente existe, despues de haber llamado
     * la funcion pacienteExiste, si no puede que falle
     *
     * @param cp
     * @return
     */
    private RandomAccessFile crearCitaFile(int cp) throws IOException {
        String np = rpacs.readUTF();
        rpacs.skipBytes(10);
        int nc = rpacs.readInt() + 1;
        rpacs.seek(rpacs.getFilePointer() - 4);
        rpacs.writeInt(nc);

        System.out.println("Creando Cita para " + np + " ....");
        String foldername = getCitaPath(np, cp, nc);
        return new RandomAccessFile(foldername, "rw");
    }

    public boolean cancelarCita(int codpac, int numcita) throws IOException {
        if (pacienteExiste(codpac)) {
            String nombrepac = rpacs.readUTF();
            String foldername = getCitaPath(nombrepac, codpac, numcita);
            File cita = new File(foldername);
            if (cita.exists()) {
                RandomAccessFile rcita = new RandomAccessFile(cita, "rw");
                rcita.skipBytes(12);
                rcita.readUTF();
                rcita.readDouble();
                rcita.writeInt(CITA_CANCELADA);
                rcita.close();
                return true;
            } else {
                System.out.println("Cita No Encontrada");
            }
        } else {
            System.out.println("No existe ese paciente");
        }

        return false;
    }

    public boolean atenderCita(int codpac, int numcita, String receta) throws IOException {
        if (pacienteExiste(codpac)) {
            String nombrepac = rpacs.readUTF();
            String foldername = getCitaPath(nombrepac, codpac, numcita);
            File cita = new File(foldername);
            if (cita.exists()) {
                RandomAccessFile rcita = new RandomAccessFile(cita, "rw");
                int dr = rcita.readInt();
                if (doctorDisponible(dr)) {
                    String nombredoc = rdocs.readUTF();
                    rdocs.readUTF();
                    double monto = rdocs.readDouble();
                    System.out.println("Cita atendida por el Dr. " + nombredoc);
                    System.out.println("Monto a pagar: Lmps. " + monto);
                    rcita.skipBytes(8);
                    rcita.readUTF();
                    rcita.writeDouble(monto);
                    rcita.writeInt(CITA_ATENDIDA);
                    rcita.writeUTF(receta);
                    rcita.close();
                    return true;
                } else {
                    System.out.println("Doctor no disponible");
                    cancelarCita(codpac, numcita);
                    return false;
                }
            } else {
                System.out.println("Cita No Encontrada");
            }
        } else {
            System.out.println("No existe ese paciente");
        }

        return false;
    }

    public boolean printCita(int codpac, int numcita) throws IOException {
        if (pacienteExiste(codpac)) {
            String nombrepac = rpacs.readUTF();
            String foldername = getCitaPath(nombrepac, codpac, numcita);
            File cita = new File(foldername);
            if (cita.exists()) {
                RandomAccessFile rcita = new RandomAccessFile(cita, "rw");
                int codDr = rcita.readInt();
                long fecha = rcita.readLong();
                String sintomas = rcita.readUTF();
                double monto = rcita.readDouble();
                int estado = rcita.readInt();
                String receta = rcita.readUTF();
                doctorExiste(codDr);
                String nombredoc = rdocs.readUTF();
                System.out.println("Nombre del paciente: " + nombrepac);
                System.out.println("Nombre del doctor: " + nombredoc + ". Su codigo es: " + codDr);
                System.out.println("Fecha: " + fecha);
                System.out.println("Sintomas: " + sintomas);
                System.out.println("Monto: " + monto);
                switch (estado) {
                    case CITA_PENDIENTE:
                        System.out.println("Cita pendiente");
                        break;
                    case CITA_ATENDIDA:
                        System.out.println("Cita atendida");
                        break;
                    case CITA_CANCELADA:
                        System.out.println("Cita cancelada");
                        break;
                }
                System.out.println("Receta: " + receta);
                rcita.close();
                return true;

            } else {
                System.out.println("Cita No Encontrada");
            }
        } else {
            System.out.println("No existe ese paciente");
        }
        return false;
    }

    public double ingresos() throws IOException {
        double monto_final = 0;
        File pacs = new File(ROOT_FOLDER);
        File citas[] = pacs.listFiles();
        for (File cita : citas) {
            if (cita.isDirectory()) {
                File c[] = cita.listFiles();
                for (File monto : c) {
                    String foldername = ROOT_FOLDER + "/" + cita.getName() + "/" + monto.getName();
                    File cita2 = new File(foldername);
                    RandomAccessFile rcita = new RandomAccessFile(cita2, "rw");
                    rcita.readInt();
                    rcita.readLong();
                    rcita.readUTF();
                    monto_final += rcita.readDouble();
                    rcita.close();
                }
            }
        }
        return monto_final;
    }
    
    public void cambiarDisponibilidadDr(int codDr) throws IOException{
        if(doctorExiste(codDr)){
            rdocs.readUTF();
            rdocs.readUTF();
            rdocs.readDouble();
            long pos=rdocs.getFilePointer();
            boolean dispo=rdocs.readBoolean();
            rdocs.seek(pos);
            Scanner lea = new Scanner(System.in);
            String res;
            if(dispo){
                System.out.print("Esta seguro que lo desea desactivar?: ");
                res=lea.next();
                if(res.equals("si"))
                rdocs.writeBoolean(false);
            }else{
                System.out.print("Esta seguro que lo desea activar?: ");
                res=lea.next();
                if(res.equals("si"))    
                rdocs.writeBoolean(true);
            }
        }
    }








public void citasPendientesPorPaciente(int codpac) throws IOException{
        if(pacienteExiste(codpac)){
            String nombre=rpacs.readUTF();
            rpacs.readLong();
            char genero=rpacs.readChar();
            int numcitas=rpacs.readInt();
            System.out.println("Nombre Paciente: "+ nombre+" Genero: "+genero);
            for (int i = 1; i <=numcitas; i++) {
                String folder=getCitaPath(nombre, codpac, i);
                RandomAccessFile rcitas=new RandomAccessFile(folder,"rw");
                int CodDr=rcitas.readInt();
                doctorExiste(CodDr);
                String nombreDr=rdocs.readUTF();
                long fechaCita=rcitas.readLong();
                Date fecha = new Date(fechaCita);
                String sintomas =rcitas.readUTF();
                rcitas.readDouble();
                int estado=rcitas.readInt();
                rcitas.readUTF();
                Date hoy = new Date();
                String estadoFecha;
                if(fechaCita-hoy.getTime()>0)
                    estadoFecha="A Tiempo";
                else
                    estadoFecha="Alerta!";
                if(estado==CITA_PENDIENTE){
                    System.out.printf("- Cita #%d, Cita asignada al DR: %s, CodDr: %d, Pactada para la fecha: %s, Por motivos de: %s,%s %n",i,nombreDr,CodDr,fecha,sintomas,estadoFecha);
                }
            }
        }
    }

    private String getCitaPath(String np, int cp, int nc) {
        return ROOT_FOLDER + "/" + np + " " + cp + "/cita_" + nc + ".med";
    }

}
