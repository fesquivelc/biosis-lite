/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.controladores;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author fesquivelc
 */
public class prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        Calendar cal = Calendar.getInstance();
////        System.out.println(df.format(cal.getTime()));
//        
//        TCSistemaControlador tc = TCSistemaControlador.getInstance();
//        
//        cal.set(Calendar.HOUR_OF_DAY, 0);
//        cal.set(Calendar.MINUTE, 0);
//        cal.set(Calendar.SECOND, 0);
//        cal.set(Calendar.MILLISECOND, 0);
//        
//        cal.set(1970, 0, 1, 0, 0, 0);
//        
//        Date fechaInicio = tc.buscarPorId("BIOSIS").getHoraCero();
//        Date fechaFin = new Date();
//        System.out.println("FINICIO "+fechaInicio.getTime());
//        System.out.println("FFIN "+cal.getTimeInMillis());
//        System.out.println(df.format(fechaInicio));
//        System.out.println(df.format(cal.getTime()));
//        System.out.println("COMPARATIVA " + fechaInicio.compareTo(cal.getTime()));
//        System.exit(0);
        Calendar cal = Calendar.getInstance();
        System.out.println("DIA: "+cal.get(Calendar.DAY_OF_WEEK));
    }
    
}
