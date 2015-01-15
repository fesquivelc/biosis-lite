/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.biosislite;

import algoritmo.AnalisisAsistencia;
import biz.juvitec.controladores.EmpleadoControlador;
import biz.juvitec.entidades.Empleado;
import java.util.List;

/**
 *
 * @author RyuujiMD
 */
public class PruebaAnalisis {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        EmpleadoControlador ec= new EmpleadoControlador();
        AnalisisAsistencia analisis = new AnalisisAsistencia();
        List<Empleado> empleados = ec.buscarTodos();
        analisis.analizarEmpleados(empleados);
        System.exit(0);
    }
    
}
