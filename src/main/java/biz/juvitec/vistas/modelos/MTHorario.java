/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.vistas.modelos;

import biz.juvitec.entidades.Horario;
import com.personal.utiles.ModeloTabla;
import java.util.List;

/**
 *
 * @author fesquivelc
 */
public class MTHorario extends ModeloTabla<Horario>{

    public MTHorario(List<Horario> datos, String[] nombreColumnas) {
        super(datos, nombreColumnas);
    }

    @Override
    public Object getValorEn(int rowIndex, int columnIndex) {
        Horario horario = this.datos.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return horario.getJornada().getNombre();
            case 1:
                return horario.isLunes();
            case 2:
                return horario.isMartes();
            case 3:
                return horario.isMiercoles();
            case 4:
                return horario.isJueves();
            case 5:
                return horario.isViernes();
            case 6:
                return horario.isSabado();
            case 7:
                return horario.isDomingo();
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 0){
            return String.class;
        }else{
            return Boolean.class;
        }
    }
    
    
    
}
