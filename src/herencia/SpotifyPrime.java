/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package herencia;

/**
 *
 * @author Docente 17082011
 */
public class SpotifyPrime extends ItemForRent {

    @Override
    public double rent(int days) {
        return price*days;
    }
    
}
