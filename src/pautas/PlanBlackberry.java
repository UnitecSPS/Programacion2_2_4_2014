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
public class PlanBlackberry extends Plan {
    private String pin;
    private ArrayList<String> bbm;
    
    public  PlanBlackberry(int n, String no, String pi){
        super(n,no);
        pin = pi;
        bbm = new ArrayList<>();
    }
    
    @Override
    public double pagoMensual(int cmins, int cmsgs){
        double tot = 40;
        if(cmins > 200)
            tot += (cmins-200) * 0.8;
        if(cmsgs > 300)
            tot += (cmsgs-300) * 0.2;
        return tot;
    }
    
    @Override
    public void imprimir(){
        super.imprimir();
        System.out.println(pin);
    }
    
    public void addPin(String pin){
        if(!bbm.contains(pin))
            bbm.add(pin);
    }
    
    public String getPin(){
        return pin;
    }
}
