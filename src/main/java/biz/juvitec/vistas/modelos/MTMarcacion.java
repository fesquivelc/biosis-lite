/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.vistas.modelos;

import biz.juvitec.entidades.Marcacion;
import com.personal.utiles.ModeloTabla;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author fesquivelc
 */
public class MTMarcacion extends ModeloTabla<Marcacion>{

    private final DateFormat dtFecha;
    private final DateFormat dtHora;
    public MTMarcacion(List<Marcacion> datos, String[] nombreColumnas) {
        super(datos, nombreColumnas);
        dtFecha = new SimpleDateFormat("dd/MM/yyyy");
        dtHora = new SimpleDateFormat("HH:mm:ss");
    }

    @Override
    public Object getValorEn(int rowIndex, int columnIndex) {
        Marcacion marcacion = this.datos.get(rowIndex);
        switch(columnIndex){
            case 0:
                return marcacion.getEmpleado();
            case 1:
                return dtFecha.format(marcacion.getFecha());
            case 2:
                return dtHora.format(marcacion.getHora());
            case 3:
                return marcacion.getEquipo();
            default:
                return null;
        }
    }
    
}
