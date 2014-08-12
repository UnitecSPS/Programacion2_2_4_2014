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
public class Uncharted extends PS3Game implements IPlaystationNetwork, Cloneable, Playable {

    @Override
    public boolean connect() {
        System.out.println("Connecting from Uncharted Game");
        return true;
    }

    @Override
    public void uploadUpdate() {
        System.out.println("Updating from Uncharted");
    }

    @Override
    protected Object clone()throws CloneNotSupportedException {
        return super.clone();
    }
    
    
    
}
