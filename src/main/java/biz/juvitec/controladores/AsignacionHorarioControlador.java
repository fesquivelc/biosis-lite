/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.controladores;

import biz.juvitec.entidades.AsignacionHorario;
import biz.juvitec.entidades.Empleado;
import biz.juvitec.entidades.GrupoHorario;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author fesquivelc
 */
public class AsignacionHorarioControlador extends Controlador<AsignacionHorario> {

    public AsignacionHorarioControlador() {
        super(AsignacionHorario.class);
    }
    
    public List<AsignacionHorario> buscarXGrupos(List<GrupoHorario> grupos){
        String jpql = "SELECT a FROM AsignacionHorario a WHERE "
                + "a.grupoHorario in :grupos";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("grupos", grupos);
        return this.getDao().buscar(jpql, mapa);
    }
    
    public List<AsignacionHorario> buscarXEmpleado(Empleado empleado){
        String jpql = "SELECT a FROM AsignacionHorario a WHERE "
                + "a.empleado = :empleado";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("empleado", empleado.getNroDocumento());
        return this.getDao().buscar(jpql, mapa);
    }

}
