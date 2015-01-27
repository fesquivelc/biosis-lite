/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.modelos;

import entidades.DetalleRegistroAsistencia;
import com.personal.utiles.ModeloTabla;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author RyuujiMD
 */
public class MTDetalleRegistroAsistencia extends ModeloTabla<DetalleRegistroAsistencia>{
    private final DateFormat dfHora = new SimpleDateFormat("HH:mm:ss");
    public MTDetalleRegistroAsistencia(List<DetalleRegistroAsistencia> datos) {
        super(datos);
        this.nombreColumnas = new String[]{"Tipo","Inicio","Fin","Tardanza",""};
    }

    @Override
    public Object getValorEn(int rowIndex, int columnIndex) {
        DetalleRegistroAsistencia detalle = this.datos.get(rowIndex);
        switch(columnIndex){
            case 0:
                return tipo(detalle.getTipoRegistro());
            case 1:
                if(detalle.getHoraInicio() == null){
                    return "FALTA";
                }else{
                    return dfHora.format(detalle.getHoraInicio());
                }
                
            case 2:
                if(detalle.getHoraFin() == null){
                    return "FALTA";
                }else{
                    return dfHora.format(detalle.getHoraFin());
                }
            case 3:
                if(detalle.getResultado() != 'F'){
                    return detalle.getMinTardanza();
                }
            case 4:
                if(detalle.getTipoRegistro() == 'P'){
                    return detalle.getPermiso().getTipoPermiso().getNombre();
                }
                
            default:
                return "";
            
        }
    }
    
    public String evento(char ev){
        switch(ev){
            case 'E':
                return "ENTRADA";
            case 'S':
                return "SALIDA";
            default:
                return "";
        }
    }
    
    public String tipo(char t){
        switch(t){
            case 'P':
                return "PERMISO";
            case 'R':
                return "REFRIGERIO";
            case 'T':
                return "JORNADA";
            default:
                return "";
        }
    }
    
    public String resultado(char r){
        switch(r){
            case 'T':
                return "TARDANZA";
            case 'R':
                return "REGULAR";
            case 'F':
                return "FALTA";
            default:
                return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 3){
            return BigDecimal.class;
        }else{
            return String.class;
        }
    }
    
}
