/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.vistas.modelos;

import biz.juvitec.entidades.Jornada;
import com.personal.utiles.ModeloTabla;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author fesquivelc
 */
public class MTJornada extends ModeloTabla<Jornada>{

    private final DateFormat dtHora;
    public MTJornada(List<Jornada> datos, String[] nombreColumnas) {
        super(datos, nombreColumnas);
        dtHora = new SimpleDateFormat("HH:mm");
    }
    private static final Logger LOG = Logger.getLogger(MTJornada.class.getName());

    
    
    @Override
    public Object getValorEn(int rowIndex, int columnIndex) {
        Jornada jornada = this.datos.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return jornada.getNombre();
            case 1:
                return dtHora.format(jornada.getTurnoHE());
            case 2:
                return dtHora.format(jornada.getRefrigerioHS());
            case 3:
                return dtHora.format(jornada.getRefrigerioHE());
            case 4:
                return dtHora.format(jornada.getTurnoHS());
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class; //To change body of generated methods, choose Tools | Templates.
    }
}
