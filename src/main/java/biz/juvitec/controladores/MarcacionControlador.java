/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.controladores;

import biz.juvitec.dao.DAOBIOSTAR;
import biz.juvitec.entidades.Marcacion;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author fesquivelc
 */
public class MarcacionControlador extends Controlador<Marcacion> {

    public MarcacionControlador() {
        super(Marcacion.class, new DAOBIOSTAR(Marcacion.class));
    }

    public List<Marcacion> buscarXFecha(String dni, Date fechaInicio, Date fechaFin) {
        String jpql = "SELECT m FROM Marcacion m WHERE CONCAT(:ceros,m.empleado) = :dni AND m.fecha BETWEEN :fechaInicio AND :fechaFin";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("dni", dni);
        mapa.put("fechaInicio", fechaInicio);
        mapa.put("fechaFin", fechaFin);
        mapa.put("ceros", this.ceros(dni));
        return this.getDao().buscar(jpql, mapa);
    }

    public List<Marcacion> buscarXFecha(Date fechaInicio, Date fechaFin) {
        String jpql = "SELECT m FROM Marcacion m WHERE m.fecha BETWEEN :fechaInicio AND :fechaFin";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("fechaInicio", fechaInicio);
        mapa.put("fechaFin", fechaFin);
        return this.getDao().buscar(jpql, mapa);
    }
    
    private String ceros(String dni) {
        String ceros = "";
        int nDNI = Integer.parseInt(dni);

        int tamanio1 = dni.length();
        int tamanio2 = (nDNI + "").length();

        for (int i = 1; i <= tamanio1 - tamanio2; i++) {
            ceros += "0";
        }
//        System.out.println("CEROS: "+ceros);
        return ceros;
    }

}
