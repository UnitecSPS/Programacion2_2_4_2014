/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package repaso;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Docente 17082011
 */
public class TestCalendarDate {
    public static void main(String[] args) {
        //Date
        Date antes = new Date(100);
        Date ahora = new Date();
       
        System.out.println(ahora);
        System.out.println("Mili de ahora: " + ahora.getTime());
        
        if( ahora.getTime() > antes.getTime())
            System.out.println("Ok ahora paso despues q antes");
        if( ahora.after(antes) )
            System.out.println("Ok ahora paso despues q antes");
        if( antes.before(ahora))
            System.out.println("Ok antes paso antes q ahora");
        
        long diff = ahora.getTime() - antes.getTime();
        
        //Calendar
        Calendar c = Calendar.getInstance();
        //-pasarlo a Date
        System.out.println(c.getTime());
        c.set(1982, Calendar.AUGUST, 10);
        System.out.println(c.getTime());
        System.out.println(c.get(Calendar.YEAR));
        System.out.println(c.get(Calendar.DATE));
        System.out.println(c.get(Calendar.DAY_OF_YEAR));
        c.add(Calendar.YEAR, 1);
        System.out.println(c.getTime());
        
    }
}
