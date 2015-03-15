/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.AsignacionHorario;
import entidades.DetalleGrupoHorario;
import entidades.Empleado;
import entidades.GrupoHorario;
import entidades.Horario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author fesquivelc
 */
public class AsignacionHorarioControlador extends Controlador<AsignacionHorario> {

    private final DetalleGrupoControlador dgc;
    public AsignacionHorarioControlador() {
        super(AsignacionHorario.class);
        dgc = new DetalleGrupoControlador();
    }
    
    public List<AsignacionHorario> buscarXGrupos(List<GrupoHorario> grupos){
        String jpql = "SELECT a FROM AsignacionHorario a WHERE "
                + "a.grupoHorario in :grupos";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("grupos", grupos);
        return this.getDao().buscar(jpql, mapa);
    }
    
    public List<AsignacionHorario> buscarXGrupo(GrupoHorario grupo){
        String jpql = "SELECT a FROM AsignacionHorario a WHERE "
                + "a.grupoHorario = :grupo";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("grupo", grupo);
        return this.getDao().buscar(jpql, mapa);
    }
    
    public List<AsignacionHorario> buscarXEmpleado(Empleado empleado){
        String jpql = "SELECT a FROM AsignacionHorario a WHERE "
                + "a.empleado = :empleado";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("empleado", empleado.getNroDocumento());
        return this.getDao().buscar(jpql, mapa);
    }
    
    public List<AsignacionHorario> buscarXEmpleado(List<String> dnis ,Horario horario){
        List<DetalleGrupoHorario> detalleGrupo = dgc.buscarXEmpleados(dnis);
        List<GrupoHorario> grupos = new ArrayList<>();
        
        for(DetalleGrupoHorario detalle : detalleGrupo){
            grupos.add(detalle.getGrupoHorario());
        }
        
        String jpql = "SELECT a FROM AsignacionHorario a WHERE "
                + "(a.empleado IN :empleados OR a.grupo) AND a.horario = :horario";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("empleados", dnis);
        mapa.put("horario", horario);
        return this.getDao().buscar(jpql, mapa);
    }

}
