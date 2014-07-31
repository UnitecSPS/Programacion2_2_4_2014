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
public class Phone {
    protected int numero;
    protected String imei;

    public Phone(int numero, String imei) {
        this.numero = numero;
        this.imei = imei;
    }

    public int getNumero() {
        return numero;
    }

    public String getImei() {
        return imei;
    }
    
    public void sms(int n,String msg){
        System.out.println("Mandando msg a "+n+" con:\n"+msg);
    }
    
    public void call(int n){
        System.out.println("Calling..."+n);
    }
}
