/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.controladores;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author fesquivelc
 */
public class prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        
        System.out.println("DIA: "+cal.get(Calendar.DAY_OF_WEEK));
    }
    
}
