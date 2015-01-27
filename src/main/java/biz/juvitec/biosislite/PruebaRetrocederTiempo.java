/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.biosislite;

import controladores.DetalleRegistroAsistenciaControlador;
import controladores.TCAnalisisControlador;
import entidades.DetalleRegistroAsistencia;
import entidades.TCAnalisis;
import com.personal.utiles.FechaUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author RyuujiMD
 */
public class PruebaRetrocederTiempo {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        DetalleRegistroAsistenciaControlador dc = new DetalleRegistroAsistenciaControlador();
        TCAnalisisControlador tca = new TCAnalisisControlador();
        
        List<String> dnis = new ArrayList<>();
        dnis.add("46557081");
        
        
        Calendar cal = Calendar.getInstance();
        cal.set(2015, 0, 1);
        Date desde = cal.getTime();
        tca.retrocederTiempo(dnis, desde);
        System.exit(0);
        
    }
    
}
