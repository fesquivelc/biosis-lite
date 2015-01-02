/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.vistas.modelos;

import biz.juvitec.controladores.EmpleadoControlador;
import biz.juvitec.entidades.Empleado;
import biz.juvitec.entidades.Marcacion;
import com.personal.utiles.ModeloTabla;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author fesquivelc
 */
public class MTMarcacion extends ModeloTabla<Marcacion> {

    private final DateFormat dtFecha;
    private final DateFormat dtHora;
    private EmpleadoControlador ec;

    public MTMarcacion(List<Marcacion> datos, String[] nombreColumnas) {
        super(datos, nombreColumnas);
        dtFecha = new SimpleDateFormat("dd/MM/yyyy");
        dtHora = new SimpleDateFormat("HH:mm:ss");
        ec = new EmpleadoControlador();
    }

    @Override
    public Object getValorEn(int rowIndex, int columnIndex) {
        Marcacion marcacion = this.datos.get(rowIndex);
        Empleado e = ec.buscarPorId(marcacion.getEmpleado());;
        switch (columnIndex) {
            case 0:                
                return e.getNroDocumento();
            case 1:
                return e.getApellidoPaterno() + " " + e.getApellidoMaterno() + " " + e.getNombre();
            case 2:
                return dtFecha.format(marcacion.getFecha());
            case 3:
                return dtHora.format(marcacion.getHora());
            case 4:
                return marcacion.getEquipo();
            default:
                return null;
        }
    }

}
