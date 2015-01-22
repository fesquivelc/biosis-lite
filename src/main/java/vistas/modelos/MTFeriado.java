/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.modelos;

import biz.juvitec.entidades.Feriado;
import com.personal.utiles.ModeloTabla;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author RyuujiMD
 */
public class MTFeriado extends ModeloTabla<Feriado>{

    private final DateFormat dfFecha;
    public MTFeriado(List<Feriado> datos) {
        super(datos);
        dfFecha = new SimpleDateFormat("dd/MM");
        this.nombreColumnas = new String[]{"Nombre","F. Inicio","F. Fin"};
    }

    @Override
    public Object getValorEn(int rowIndex, int columnIndex) {
        Feriado feriado = this.datos.get(rowIndex);
        switch(columnIndex){
            case 0:
                return feriado.getNombre();
            case 1:
                return dfFecha.format(feriado.getFechaInicio());
            case 2:
                return dfFecha.format(feriado.getFechaFin());
            default:
                return null;
        }
    }
    
}
