/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.controladores;

import biz.juvitec.entidades.AsignacionHorario;
import biz.juvitec.entidades.AsignacionPermiso;
import biz.juvitec.entidades.Empleado;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author fesquivelc
 */
public class AsignacionPermisoControlador extends Controlador<AsignacionPermiso> {

    public AsignacionPermisoControlador() {
        super(AsignacionPermiso.class);
    }

    public List<AsignacionPermiso> buscarXEmpleadoXFecha(String dni, Date fechaInicio, Date fechaFin, int desde, int tamanio) {
        String jpql = "SELECT a FROM AsignacionPermiso a WHERE a.empleado = :dni AND a.permiso.fechaInicio BETWEEN :fechaInicio AND :fechaFin";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("dni", dni);
        mapa.put("fechaInicio", fechaInicio);
        mapa.put("fechaFin", fechaFin);
        return this.getDao().buscar(jpql, mapa, desde, tamanio);
    }

    public List<AsignacionPermiso> buscarXFecha(Date fechaInicio, Date fechaFin, int desde, int tamanio) {
        String jpql = "SELECT a FROM AsignacionPermiso a WHERE a.permiso.fechaInicio BETWEEN :fechaInicio AND :fechaFin";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("fechaInicio", fechaInicio);
        mapa.put("fechaFin", fechaFin);
        return this.getDao().buscar(jpql, mapa, desde, tamanio);
    }

    public int contarXEmpleadoXFecha(String dni, Date fechaInicio, Date fechaFin) {
        String jpql = "SELECT COUNT(a.id) FROM AsignacionPermiso a WHERE a.empleado = :dni AND a.permiso.fechaInicio BETWEEN :fechaInicio AND :fechaFin";
        Long cont = (Long) this.getDao().getEntityManager().createQuery(jpql)
                .setParameter("dni", dni)
                .setParameter("fechaInicio", fechaInicio)
                .setParameter("fechaFin", fechaFin).getSingleResult();
        int conteo = cont.intValue();
        return conteo;
    }

    public int contarXFecha(Date fechaInicio, Date fechaFin) {
        String jpql = "SELECT COUNT(a.id) FROM AsignacionPermiso a WHERE a.permiso.fechaInicio BETWEEN :fechaInicio AND :fechaFin";
        Long cont = (Long) this.getDao().getEntityManager().createQuery(jpql)
                .setParameter("fechaInicio", fechaInicio)
                .setParameter("fechaFin", fechaFin).getSingleResult();
        int conteo = cont.intValue();
        System.out.println("CONTEO"+conteo);
        return conteo;
    }
    
    public AsignacionPermiso buscarXDia(String dni, Date dia){
        String jpql = "SELECT a FROM AsignacionPermiso a WHERE a.empleado = :dni AND a.permiso.porFecha = TRUE AND :dia BETWEEN a.permiso.fechaInicio AND a.permiso.fechaFin";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("dia", dia);
        mapa.put("dni", dni);
        List<AsignacionPermiso> asignacion = this.getDao().buscar(jpql, mapa, -1, 1);
        if(asignacion.isEmpty()){
            return null;
        }else{
            return asignacion.get(0);
        }        
    }

    public List<AsignacionPermiso> obtenerPermisosXHora(String dni, Date fecha, Date horaI, Date horaF) {
        String jpql = "SELECT a FROM AsignacionPermiso a WHERE "
                + "a.empleado = :dni AND "
                + "a.permiso.porFecha = FALSE AND "
                + "a.permiso.fechaInicio = :fecha AND "
                + "a.permiso.horaInicio BETWEEN :horaI AND :horaF";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("dni", dni);
        mapa.put("fecha", fecha);
        mapa.put("horaI", horaI);
        mapa.put("horaF", horaF);
        return this.getDao().buscar(jpql, mapa);
    }
}
