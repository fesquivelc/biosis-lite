/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Rol;
import entidades.RolAcceso;
import java.util.ArrayList;

/**
 *
 * @author RyuujiMD
 */
public class RolControlador extends Controlador<Rol>{

    public RolControlador() {
        super(Rol.class);
    }

    @Override
    public void prepararCrear() {
        super.prepararCrear();
        this.getSeleccionado().setRolAccesoList(new ArrayList<RolAcceso>());//To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
