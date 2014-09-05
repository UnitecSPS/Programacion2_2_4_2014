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
            System.out.println("5- Cambiar disponibilidad");
            System.out.println("6- Salir");
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
                    case 5:
                        System.out.println("Cod dr");
                        int cod = lea.nextInt();
                        hosp.cambiarDisponibilidadDr(cod);
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
        }while(op!=6);
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
            System.out.println("3- Imprimir cita");
            System.out.println("4- Citas Pendientes por Paciente");
            System.out.println("5- Imprimir ingresos");
            System.out.println("6- Regresar al Menu Principal");
            System.out.println("Escoja opcion: ");
            op = lea.nextInt();
            
            switch(op){
                case 1:
                    hosp.listarDrsDisponibles();
                    break;
                case 2:
                    hosp.listarPacientes();
                    break;
                case 3:
                    printCita();
                    break;
                case 4:
                    System.out.println("Codigo");
                    int cod=lea.nextInt();
                   hosp.citasPendientesPorPaciente(cod) ;
                           break;
                case 5:
                    ingresos();
                    break;


            }
        }while(op!=6);
    }
    
    private static void citas()throws IOException {
        int op;
        do{
            System.out.println("1- Crear Cita");
            System.out.println("2- Cancelar Cita");
            System.out.println("3- Atender cita");
            System.out.println("4- Regresar al Menu Principal");
            System.out.println("Escoja opcion: ");
            op = lea.nextInt();
            
            switch(op){
                case 1:
                    crearCita();
                    break;
                case 2:
                    cancelarCita();
                    break;
                case 3:
                    atenderCita();
                    break;
            }
        }while(op!=4);
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

    private static void atenderCita() throws IOException {
      System.out.println("Codigo Paciente: ");
        int numpac = lea.nextInt();
        System.out.println("Numero de la Cita: ");
        int numcita = lea.nextInt();
        System.out.println("Receta del paciente");
        String receta = lea.next();
        
        if(hosp.atenderCita(numpac, numcita,receta))
            System.out.println("Cita exitosamente atendida");
        else
            System.out.println("Cita no se pudo atender");
    }

    private static void printCita() throws IOException {
         System.out.println("Codigo Paciente: ");
        int numpac = lea.nextInt();
        System.out.println("Numero de la Cita: ");
        int numcita = lea.nextInt();
        
        if(hosp.printCita(numpac, numcita))
            System.out.println("Cita exitosamente atendida");
        else
            System.out.println("Cita no se pudo atender");
    }

    private static void ingresos() throws IOException{
        System.out.println(hosp.ingresos());
    }

    private static void pendientes() {
        
    }

    
    
    
}
