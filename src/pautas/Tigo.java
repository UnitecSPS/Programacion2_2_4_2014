/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pautas;

import java.util.ArrayList;

/**
 *
 * @author Docente 17082011
 */
public class Tigo {
    ArrayList<Plan> planes = new ArrayList<>();
    
    public void addPlan(int nt,String no, String extra, String tipo){
        if( !busqueda(nt,extra,tipo) ){
            if(tipo.equals("IPHONE"))
                planes.add(new PlanIphone(nt,no,extra));
            else if(tipo.equals("BACKBERRY"))
                planes.add(new PlanBlackberry(nt,no,extra));
        }
    }

    public boolean busqueda(int nt, String extra, String tipo) {
        return busqueda(nt,extra,tipo,0);
    }

    private boolean busqueda(int nt, String extra, String tipo, int pos) {
        if(pos < planes.size()){
            Plan plan = planes.get(pos);
            if(plan.getNumero() == nt)
                return true;
            else if(plan instanceof PlanIphone && tipo.equals("IPHONE")){
                if( ((PlanIphone)plan).getEmail().equals(extra) )
                    return true;
            }
            else if(plan instanceof PlanBlackberry && tipo.equals("BLACKBERRY")){
                if( ((PlanBlackberry)plan).getPin().equals(extra) )
                    return true;
            }
            return busqueda(nt,extra,tipo,pos+1);
        }
        return false;
    }
    
    public double pagoPlan(int nt,int mins,int msgs){
        for(Plan plan : planes){
            if(plan.getNumero() == nt){
                return plan.pagoMensual(mins, msgs);
            }
        }
        return 0;
    }
    
    public void addAmigo(int nt,String pin){
        for(Plan plan : planes){
            if( plan.getNumero() == nt){
                if( plan instanceof PlanBlackberry)
                    ((PlanBlackberry)plan).addPin(pin);
            }
        }
    }
    
    public void listar(){
        int ciphones=0, cbbs=0;
        
        for(Plan plan : planes){
            plan.imprimir();
            if(plan instanceof PlanIphone)
                ciphones++;
            else
                cbbs++;
        }
        System.out.println("iphones" + ciphones + "bbs"+ cbbs);
    }
}
