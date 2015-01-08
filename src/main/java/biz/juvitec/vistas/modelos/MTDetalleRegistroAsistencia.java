/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.vistas.modelos;

import biz.juvitec.entidades.DetalleRegistroAsistencia;
import com.personal.utiles.ModeloTabla;
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
        this.nombreColumnas = new String[]{"Tipo","Evento","Hora",""};
    }

    @Override
    public Object getValorEn(int rowIndex, int columnIndex) {
        DetalleRegistroAsistencia detalle = this.datos.get(rowIndex);
        switch(columnIndex){
            case 0:
                return tipo(detalle.getTipo());
            case 1:
                return evento(detalle.getEvento());
            case 2:
                return dfHora.format(detalle.getHora());
            case 3:
                return resultado(detalle.getResultado());
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
    
}
