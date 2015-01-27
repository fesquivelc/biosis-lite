/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.modelos;

import entidades.Vacacion;
import com.personal.utiles.ModeloTabla;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author fesquivelc
 */
public class MTAsignarVacacion extends ModeloTabla<Vacacion>{
    private final DateFormat dfFecha;    

    public MTAsignarVacacion(List<Vacacion> datos) {
        super(datos);
        dfFecha = new SimpleDateFormat("dd/MM/yyyy");
        this.nombreColumnas = new String[]{"Nro de documento","Fecha de inicio","Fecha de fin","¿Interrumpida?","Fecha de interrupción"};
    }

    @Override
    public Object getValorEn(int rowIndex, int columnIndex) {
        Vacacion vacacion = this.datos.get(rowIndex);
        switch(columnIndex){
            case 0:
                return vacacion.getEmpleado();
            case 1:
                return dfFecha.format(vacacion.getFechaInicio());
            case 2:
                return dfFecha.format(vacacion.getFechaFin());
            case 3:
                return vacacion.isHayInterrupcion();
            case 4:
                if(vacacion.isHayInterrupcion()){
                    return dfFecha.format(vacacion.getFechaInterrupcion());
                }else{
                    return null;
                }
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 3:
                return Boolean.class;
            default:
                return String.class;
        }
    }
    
    
    
    
    
}
