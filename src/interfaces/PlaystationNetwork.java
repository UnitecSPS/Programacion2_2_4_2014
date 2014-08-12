/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;

/**
 *
 * @author Docente 17082011
 */
public class PlaystationNetwork {
    public static void main(String[] args) throws CloneNotSupportedException {
        IPlaystationNetwork device = new Uncharted();
        device.uploadUpdate();
        System.out.println("Version: " + IPlaystationNetwork.VERSION);
        
        if(device instanceof Uncharted &&
           device instanceof PS3Game &&
           device instanceof IPlaystationNetwork)
            System.out.println("ES TODO ESO!");
        
        if(device instanceof Playable)
            System.out.println("Loding GAME!");
         
        device = new PSPhone(9995);
        device.uploadUpdate();
        ((PSPhone)device).loadAppStore();
        ((iSmartPhone)device).loadAppStore();
        
         if(!(device instanceof Playable))
            System.out.println("Implementa IPSN pero NO es un juego");  
             
        IPlaystationNetwork ipsn = new IPlaystationNetwork() {

            @Override
            public boolean connect() {
                System.out.println("Connecting directly from PSN");
                return true;
            }

            @Override
            public void uploadUpdate() {
                System.out.println("Uploading from PSN");
            }
        };
        ipsn.uploadUpdate();
        
        ///cloneable
        Uncharted drake = new Uncharted();
        drake.clone();
    }
}
