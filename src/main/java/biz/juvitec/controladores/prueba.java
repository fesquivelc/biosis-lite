/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.controladores;

import algoritmo.AnalisisAsistencia;
import biz.juvitec.entidades.Empleado;
import com.personal.utiles.FechaUtil;
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
//        EmpleadoControlador ec = new EmpleadoControlador();
//        AnalisisAsistencia aa = new AnalisisAsistencia();
//        List<Empleado> empleados = ec.buscarXPatron("10001020");
//        if(!empleados.isEmpty()){
//            aa.analizarEmpleados(empleados);
//        }
//        
//        System.exit(0);
        MarcacionControlador mc = new MarcacionControlador();
        System.out.println("TAMAÑO: "+mc.buscarXFecha(FechaUtil.soloFecha(new Date())).size());
        
    }
    
}
