/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.controladores;

import biz.juvitec.entidades.GrupoHorario;
import biz.juvitec.entidades.DetalleGrupoHorario;
import java.util.ArrayList;

/**
 *
 * @author fesquivelc
 */
public class GrupoHorarioControlador extends Controlador<GrupoHorario>{

    public GrupoHorarioControlador() {
        super(GrupoHorario.class);
    }

    @Override
    public void prepararCrear() {
        super.prepararCrear(); //To change body of generated methods, choose Tools | Templates.
        this.getSeleccionado().setDetalleGrupoHorarioList(new ArrayList<DetalleGrupoHorario>());
    }
    
}
