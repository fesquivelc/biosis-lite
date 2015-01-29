/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.biosislite;

import controladores.DepartamentoControlador;
import controladores.EmpleadoControlador;
import controladores.MarcacionControlador;
import entidades.Departamento;
import entidades.Empleado;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author RyuujiMD
 */
public class pruebaAnio {

    public static void main(String[] args) {
        MarcacionControlador mc = new MarcacionControlador();
        EmpleadoControlador ec = new EmpleadoControlador();
        DepartamentoControlador dc = new DepartamentoControlador();
        List<Departamento> deps = dc.buscarXNombre("");
        
        System.out.println("DEPS: "+deps.size());
        System.exit(0);
    }

    public static String conCeros(int dni) {
        if (dni <= 9) {
            return "0000000" + dni;
        } else if (dni <= 99) {
            return "000000" + dni;
        } else if (dni <= 999) {
            return "00000" + dni;
        } else {
            return "0000" + dni;
        }
    }
}
