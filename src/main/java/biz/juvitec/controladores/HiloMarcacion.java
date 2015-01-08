/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.controladores;

import biz.juvitec.entidades.Marcacion;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RyuujiMD
 */
public class HiloMarcacion extends Thread {

    private final MarcacionControlador mc;
    private String dni;
    private final Date fecha;
    private final List<Marcacion> lista;

    public HiloMarcacion(String dni, Date fecha, List<Marcacion> lista) {
        this.mc = new MarcacionControlador();
        this.dni = dni;
        this.fecha = fecha;
        this.lista = lista;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    
    

    @Override
    public void run() {
        while (true) {
            lista.clear();
            List<Marcacion> listado;
            if (dni == null) {
                listado = mc.buscarXFecha(fecha, fecha);
            } else {
                listado = mc.buscarXFecha(dni, fecha);
            }
            if (!listado.isEmpty()) {
                System.out.println("NO ESTA VACIA");
                lista.addAll(listado);
            }else{
                System.out.println("ESTA VACIA");
            }

            try {
                Thread.sleep(Long.parseLong("1000"));
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloMarcacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
