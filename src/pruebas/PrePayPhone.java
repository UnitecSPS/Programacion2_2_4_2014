/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pruebas;

/**
 *
 * @author Docente 17082011
 */
public class PrePayPhone extends Phone {
    private double saldo;
    
    public PrePayPhone(int n, String i){
        super(n,i);
        saldo = 100;
    }
    
    @Override
    public void call(int n){
        if(saldo > 0){
            super.call(n);
            saldo -= 20;
            sms(numero, "Ahora tienes de Saldo Lps. "+saldo);
        }
    }
    
    public static void main(String[] args) {
        Phone p = new PrePayPhone(12,"123m");
        p.call(123);
    }
}
