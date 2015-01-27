/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.modelos;

import entidades.Rol;
import com.personal.utiles.ModeloTabla;
import java.util.List;

/**
 *
 * @author RyuujiMD
 */
public class MTRol extends ModeloTabla<Rol>{

    public MTRol(List<Rol> datos) {
        super(datos);
        this.nombreColumnas = new String[]{"Código","Nombre"};
    }

    @Override
    public Object getValorEn(int rowIndex, int columnIndex) {
        Rol rol = this.datos.get(rowIndex);
        switch(columnIndex){
            case 0:
                return rol.getCodigo();
            case 1:
                return rol.getNombre();
            default:
                return null;
        }
    }
    
}
