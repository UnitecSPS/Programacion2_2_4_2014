/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;

import java.util.Scanner;

/**
 *
 * @author Docente 17082011
 */
public interface IPlaystationNetwork {
    int VERSION = 123;
    Scanner lea = new Scanner(System.in);
    boolean connect();
    void uploadUpdate();
}
