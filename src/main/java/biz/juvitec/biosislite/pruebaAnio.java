/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.biosislite;

import java.util.Calendar;

/**
 *
 * @author RyuujiMD
 */
public class pruebaAnio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Calendar cal = Calendar.getInstance();
        cal.set(2012, 1, 29);
//        cal.add(Calendar.YEAR, 1);
        cal.add(Calendar.DATE, 365);
        
        System.out.println("FECHA NUEVA: "+cal.getTime());
    }
    
}
