/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.DetalleGrupoHorario;
import entidades.Empleado;
import entidades.GrupoHorario;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author fesquivelc
 */
public class DetalleGrupoControlador extends Controlador<DetalleGrupoHorario>{

    public DetalleGrupoControlador() {
        super(DetalleGrupoHorario.class);
    }
    
    public List<DetalleGrupoHorario> buscarXEmpleado(Empleado empleado){
        String jpql = "SELECT a FROM DetalleGrupoHorario a WHERE "
                + "a.empleado = :empleado";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("empleado", empleado.getNroDocumento());
        return this.getDao().buscar(jpql, mapa);
    }
    
    public List<DetalleGrupoHorario> buscarXGrupo(GrupoHorario grupo){
        String jpql = "SELECT a FROM DetalleGrupoHorario a WHERE "
                + "a.grupoHorario = :grupo";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("grupo", grupo);
        return this.getDao().buscar(jpql, mapa);
    }

    public List<DetalleGrupoHorario> buscarXEmpleado(Empleado empleado, Date fechaInicio, Date fechaFin) {
        String jpql = "SELECT a FROM DetalleGrupoHorario a WHERE "
                + "a.empleado = :empleado AND a.horario.fechaInicio ";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("empleado", empleado.getNroDocumento());
        mapa.put("fechaInicio", fechaInicio);
        mapa.put("fechaFin", fechaFin);
        
        return this.getDao().buscar(jpql, mapa);
    }

    List<DetalleGrupoHorario> buscarXEmpleados(List<String> dnis) {
        String jpql = "SELECT dgh FROM DetalleGrupoHorario dgh WHERE "
                + "dgh.empleado IN :empleados ";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("empleados", dnis);
        
        return this.getDao().buscar(jpql, mapa);
    }
}
