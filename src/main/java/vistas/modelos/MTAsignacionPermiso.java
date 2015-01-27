/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.modelos;

import entidades.AsignacionPermiso;
import entidades.Permiso;
import com.personal.utiles.ModeloTabla;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author fesquivelc
 */
public class MTAsignacionPermiso extends ModeloTabla<AsignacionPermiso>{
    private final DateFormat dfFecha;
    private final DateFormat dfHora;

    public MTAsignacionPermiso(List<AsignacionPermiso> datos) {
        super(datos);
        this.nombreColumnas = new String[]{"Nro de documento","Tipo de permiso","Inicio","Fin","Motivo",""};
        dfFecha = new SimpleDateFormat("dd/MM/yyyy");
        dfHora = new SimpleDateFormat("HH:mm:ss");
        
    }

    @Override
    public Object getValorEn(int rowIndex, int columnIndex) {
        AsignacionPermiso asignacion = this.datos.get(rowIndex);
        switch(columnIndex){
            case 0:
                return asignacion.getEmpleado();
            case 1:
                return clase(asignacion.getPermiso().getTipoPermiso().getClase());
            case 2:
                if(asignacion.getPermiso().isPorFecha()){
                    return dfFecha.format(asignacion.getPermiso().getFechaInicio());
                }else{
                    return dfFecha.format(asignacion.getPermiso().getFechaInicio()) + " " +dfHora.format(asignacion.getPermiso().getHoraInicio());                    
                }
            case 3:
                if(asignacion.getPermiso().isPorFecha()){
                    return dfFecha.format(asignacion.getPermiso().getFechaFin());
                }else{
                    return dfFecha.format(asignacion.getPermiso().getFechaInicio()) + " " +dfHora.format(asignacion.getPermiso().getHoraFin());                    
                }
            case 4:
                return asignacion.getPermiso().getMotivo();
            case 5:
                return tipoDescuento(asignacion.getPermiso().getTipoPermiso().getTipoDescuento());
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
