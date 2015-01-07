/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.controladores;

import biz.juvitec.entidades.DetalleGrupoHorario;
import biz.juvitec.entidades.Empleado;
import biz.juvitec.entidades.GrupoHorario;
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
}
