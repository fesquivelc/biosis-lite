/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.vistas.modelos;

import biz.juvitec.entidades.Vacacion;
import com.personal.utiles.ModeloTabla;
import java.util.List;

/**
 *
 * @author fesquivelc
 */
public class MTAsignarVacacion extends ModeloTabla<Vacacion>{

    public MTAsignarVacacion(List<Vacacion> datos) {
        super(datos);
        this.nombreColumnas = new String[]{""};
    }

    @Override
    public Object getValorEn(int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
