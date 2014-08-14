/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pautas;

/**
 *
 * @author Docente 17082011
 */
public class PlanIphone extends Plan {
    private String email;
    
    public PlanIphone(int n, String no, String e){
        super(n,no);
        email = e;
    }
    
    @Override
    public double pagoMensual(int cmins,int cmsgs){
        return 22 + (cmins*0.4) + (cmsgs*0.1);
    }
    
    @Override
    public void imprimir(){
        super.imprimir();
        System.out.println("Email " + email);
    }
    
    public String getEmail(){
        return email;
    }
}
