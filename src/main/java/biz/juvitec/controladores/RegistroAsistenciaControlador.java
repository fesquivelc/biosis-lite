/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.controladores;

import biz.juvitec.entidades.RegistroAsistencia;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author fesquivelc
 */
public class RegistroAsistenciaControlador extends Controlador<RegistroAsistencia>{

    public RegistroAsistenciaControlador() {
        super(RegistroAsistencia.class);
    }
    
    public List<RegistroAsistencia> buscarXEmpleadoXFecha(List<String> dnis, Date fechaInicio, Date fechaFin, int desde, int tamanio){
        String jpql = "SELECT r FROM RegistroAsistencia r WHERE r.empleado in :dnis AND r.fecha BETWEEN :fechaInicio AND :fechaFin";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("dnis", dnis);
        mapa.put("fechaInicio", fechaInicio);
        mapa.put("fechaFin", fechaFin);
        return this.getDao().buscar(jpql, mapa, desde, tamanio);
    }
    
    public int contarXEmpleadoXFecha(List<String> dnis, Date fechaInicio, Date fechaFin){
        String jpql = "SELECT COUNT(r.id) FROM RegistroAsistencia r WHERE r.empleado in :dnis AND r.fecha BETWEEN :fechaInicio AND :fechaFin";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("dnis", dnis);
        mapa.put("fechaInicio", fechaInicio);
        mapa.put("fechaFin", fechaFin);
        return this.getDao().contar(jpql, mapa);
    }
    
}
