/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.vistas.modelos;

import biz.juvitec.entidades.Empleado;
import com.personal.utiles.ModeloTabla;
import java.util.List;

/**
 *
 * @author fesquivelc
 */
public class MTEmpleado extends ModeloTabla<Empleado>{

    public MTEmpleado(List<Empleado> datos, String[] nombreColumnas) {
        super(datos, nombreColumnas);
    }

    @Override
    public Object getValorEn(int rowIndex, int columnIndex) {
        Empleado empleado = this.datos.get(rowIndex);
        switch(columnIndex){
            case 0:
                return empleado.getNroDocumento();
            case 1:
                return empleado.getApellidoPaterno()
                        + " "
                        + empleado.getApellidoMaterno()
                        + " "
                        + empleado.getNombre();
                default:
                    return null;
        }
    }
    
}
