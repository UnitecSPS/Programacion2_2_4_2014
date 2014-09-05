/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package archivos.er;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Docente 17082011
 */
public class MainHospital {
    static Hospital hosp = new Hospital();
    static Scanner lea = new Scanner(System.in);
    
    public static void main(String[] args) {
        int op=0;
        
        do{
            System.out.println("1- Agregar Doctor");
            System.out.println("2- Agregar Paciente");
            System.out.println("3- Mantenimiento de Citas");
            System.out.println("4- Reportes");
            System.out.println("5- Salir");
            System.out.println("Escoja opcion: ");
            
            try{
                op = lea.nextInt();
                switch(op){
                    case 1:
                        addDoctor();
                        break;
                    case 2:
                        addPaciente();
                        break;
                    case 3:
                        citas();
                        break;
                    case 4:
                        reportes();
                        break;
                }
            }
            catch(IllegalArgumentException e){
                System.out.println("Especialidad Incorrecta");
            }
            catch(InputMismatchException e){
                System.out.println("Ingrese un dato numerico");
                lea.next();
            }
            catch(Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }while(op!=5);
    }

    private static void addDoctor() throws IOException {
        System.out.println("Nombre: ");
        String n = lea.next();
        System.out.println("Especialidad: ");
        EspecialiadMedica em = EspecialiadMedica.valueOf(lea.next().toUpperCase());
        System.out.println("Monto por Consulta: ");
        double mo = lea.nextDouble();
        
        hosp.addDoctor(n, em, mo);
    }
    
    private static Date getDate(){   
        String fecha = lea.next();
        //fecha----
        String valores[] = fecha.split("/");
        Calendar c = Calendar.getInstance();
        
        c.set(Integer.parseInt(valores[2]), 
                Integer.parseInt(valores[1])-1, 
                Integer.parseInt(valores[0]));
        return c.getTime();
    }

    private static void addPaciente()throws IOException {
        System.out.println("Nombre: ");
        String n = lea.next();
        //fecha nac
        System.out.println("Fecha nac en formato dia/mes/año: ");
        Date fechanac = getDate();
        //genero
        char gen = lea.next().charAt(0);
        
        hosp.addPaciente(n, fechanac, gen);
    }

    private static void reportes()throws IOException {
        int op;
        do{
            System.out.println("1- Listar DRs Disponibles");
            System.out.println("2- Listar Pacientes");
            System.out.println("3- Regresar al Menu Principal");
            System.out.println("Escoja opcion: ");
            op = lea.nextInt();
            
            switch(op){
                case 1:
                    hosp.listarDrsDisponibles();
                    break;
                case 2:
                    hosp.listarPacientes();
                    break;

            }
        }while(op!=3);
    }
    
    private static void citas()throws IOException {
        int op;
        do{
            System.out.println("1- Crear Cita");
            System.out.println("2- Cancelar Cita");
            System.out.println("3- Regresar al Menu Principal");
            System.out.println("Escoja opcion: ");
            op = lea.nextInt();
            
            switch(op){
                case 1:
                    crearCita();
                    break;
                case 2:
                    cancelarCita();
                    break;

            }
        }while(op!=3);
    }

    private static void crearCita()throws IOException {
        System.out.println("Codigo de Paciente: ");
        int numpac = lea.nextInt();
        System.out.println("Codigo de Doctor: ");
        int dr = lea.nextInt();
        System.out.println("Sintomas: ");
        String sin = lea.next();
        System.out.println("Fecha de la cita dia/mes/año:: ");
        Date fecha = getDate();
        
        if( hosp.crearCita(numpac,dr,fecha,sin) ){
            System.out.println("Cita Creada con Exito");
        }
        else{
            System.out.println("No se pudo crear cita");
        }
    }

    private static void cancelarCita()throws IOException {
        System.out.println("Codigo Paciente: ");
        int numpac = lea.nextInt();
        System.out.println("Numero de la Cita: ");
        int numcita = lea.nextInt();
        
        if(hosp.cancelarCita(numpac, numcita))
            System.out.println("Cita exitosamente cancelada");
        else
            System.out.println("Cita no se pudo cancelar");
    }

    
    
    
}
