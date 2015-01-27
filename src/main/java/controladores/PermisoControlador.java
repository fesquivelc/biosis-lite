/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.AsignacionPermiso;
import entidades.Permiso;
import java.util.ArrayList;

/**
 *
 * @author fesquivelc
 */
public class PermisoControlador extends Controlador<Permiso>{

    public PermisoControlador() {
        super(Permiso.class);
    }

    @Override
    public void prepararCrear() {
        super.prepararCrear(); //To change body of generated methods, choose Tools | Templates.
        this.getSeleccionado().setAsignacionPermisoList(new ArrayList<AsignacionPermiso>());
    }
    
}
