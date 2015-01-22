/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.modelos;

import biz.juvitec.entidades.Permiso;
import com.personal.utiles.ModeloTabla;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author fesquivelc
 */
public class MTPermiso extends ModeloTabla<Permiso>{
    private final DateFormat dfFecha;
    private final DateFormat dfHora;

    public MTPermiso(List<Permiso> datos) {
        super(datos);
        this.nombreColumnas = new String[]{"Tipo","Inicio","Fin","Motivo",""};
        dfFecha = new SimpleDateFormat("dd/MM/yyyy");
        dfHora = new SimpleDateFormat("HH:mm:ss");
        
    }

    @Override
    public Object getValorEn(int rowIndex, int columnIndex) {
        Permiso permiso = this.datos.get(rowIndex);
        switch(columnIndex){
            case 0:
                return clase(permiso.getTipoPermiso().getClase());
            case 1:
                if(permiso.isPorFecha()){
                    return dfFecha.format(permiso.getFechaInicio());
                }else{
                    return dfFecha.format(permiso.getFechaInicio()) + " " +dfHora.format(permiso.getHoraInicio());                    
                }
            case 2:
                if(permiso.isPorFecha()){
                    return dfFecha.format(permiso.getFechaFin());
                }else{
                    return dfFecha.format(permiso.getFechaInicio()) + " " +dfHora.format(permiso.getHoraFin());                    
                }
            case 3:
                return permiso.getMotivo();
            case 4:
                return tipoDescuento(permiso.getTipoPermiso().getTipoDescuento());
            default:
                return null;
        }
    }
    
    private String clase(char clase) {
        switch (clase) {
            case 'C':
                return "COMISION DE SERVICIOS";
            case 'P':
                return "PERMISO";
            case 'L':
                return "LICENCIA";
            default:
                return "";
        }
    }

    private String tipoDescuento(char tipo) {
        switch (tipo) {
            case 'C':
                return "CON GOCE";
            case 'S':
                return "SIN GOCE";
            case 'V':
                return "CTA DE VACACIONES";
            default:
                return "";
        }
    }
    
}
