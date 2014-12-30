/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.controladores;

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
        // TODO code application logic here
        List<String> listado = new ArrayList<>();
        listado.add("05401971");
        listado.add("05640304");
        
        EmpleadoControlador ec = new EmpleadoControlador();
        System.out.println(ec.buscarPorLista(listado).size());
        System.exit(0);
    }
    
}
