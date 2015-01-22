/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.modelos;

import biz.juvitec.entidades.TipoPermiso;
import com.personal.utiles.ModeloTabla;
import java.util.List;

/**
 *
 * @author fesquivelc
 */
public class MTTipoPermiso extends ModeloTabla<TipoPermiso> {

    public MTTipoPermiso(List<TipoPermiso> datos, String[] nombreColumnas) {
        super(datos, nombreColumnas);
    }

    @Override
    public Object getValorEn(int rowIndex, int columnIndex) {
        TipoPermiso seleccionado = this.datos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return seleccionado.getCodigo();
            case 1:
                return seleccionado.getNombre();
            case 2:
                return clase(seleccionado.getClase());
            case 3:
                return tipoDescuento(seleccionado.getTipoDescuento());
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
