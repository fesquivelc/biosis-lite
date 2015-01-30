/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.biosislite;

import algoritmo.AnalisisAsistencia;
import controladores.DepartamentoControlador;
import controladores.EmpleadoControlador;
import entidades.Departamento;
import entidades.Empleado;
import entidades.EmpleadoBiostar;
import java.util.ArrayList;
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
        DepartamentoControlador dc = new DepartamentoControlador();
        EmpleadoControlador ec = new EmpleadoControlador();
        List<Departamento> depas = dc.buscarXNombre("CASA DE LA LITERATURA");
        for (Departamento dep : depas) {
            System.out.println("----- DEP: " + dep.getNombre() + " -----");
            List<Integer> dnis = dnis(dep.getEmpleadoList());
            List<Empleado> empleados = ec.buscarPorListaEnteros(dnis);
            System.out.println("> EMPS: " + empleados.size());
        }
        System.exit(0);
    }

    static List<Integer> dnis(List<EmpleadoBiostar> empleados) {
        List<Integer> dni = new ArrayList<>();
        for (EmpleadoBiostar e : empleados) {
            dni.add(e.getId());
        }
        return dni;
    }

}
