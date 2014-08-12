/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;

import java.io.Serializable;

/**
 *
 * @author Docente 17082011
 */
public class PSPhone implements IPlaystationNetwork, iSmartPhone, Serializable {

    private int num;
    
    public PSPhone(int n){
        num = n;
    }
    
    @Override
    public boolean connect() {
        System.out.println("Connecting from PSPhone");
        return true;
    }

    @Override
    public void uploadUpdate() {
        System.out.println("Uploading from PSPHone");
    }

    @Override
    public void loadAppStore() {
        System.out.println("Loading Google Play from " + num);
    }
    
}
