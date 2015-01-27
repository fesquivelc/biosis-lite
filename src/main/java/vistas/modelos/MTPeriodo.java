/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.modelos;

import entidades.Periodo;
import com.personal.utiles.ModeloTabla;
import java.util.List;

/**
 *
 * @author fesquivelc
 */
public class MTPeriodo extends ModeloTabla<Periodo>{

    public MTPeriodo(List<Periodo> datos, String[] nombreColumnas) {
        super(datos, nombreColumnas);
    }

    @Override
    public Object getValorEn(int rowIndex, int columnIndex) {
        Periodo periodo = this.datos.get(rowIndex);
        switch(columnIndex){
            case 0:
                return periodo.getAnio();
            case 1:
                return periodo.getNombre();
            case 2:
                return periodo.isVigente();
            default:
                return null;
        }
    }  

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 2:
                return Boolean.class;
            default:
                return String.class;
        }
    }
    
    
}
