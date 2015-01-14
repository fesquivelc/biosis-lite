/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.vistas.modelos;

import biz.juvitec.entidades.Horario;
import com.personal.utiles.ModeloTabla;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author RyuujiMD
 */
public class MTHorarioRA extends ModeloTabla<Horario>{

    private final DateFormat dfHora;
    public MTHorarioRA(List<Horario> datos) {
        super(datos);
        dfHora = new SimpleDateFormat("HH:mm");
        this.nombreColumnas = new String[]{"HE","HSR","HER","HS","L","M","M","J","V","S","D"};
    }

    @Override
    public Object getValorEn(int rowIndex, int columnIndex) {
        Horario horario = this.datos.get(rowIndex);
        switch(columnIndex){
            case 0:
                return dfHora.format(horario.getJornada().getTurnoHE());
            case 1:
                return dfHora.format(horario.getJornada().getRefrigerioHS());
            case 2:
                return dfHora.format(horario.getJornada().getRefrigerioHE());
            case 3:
                return dfHora.format(horario.getJornada().getTurnoHS());
            case 4:
                return horario.isLunes();
            case 5:
                return horario.isMartes();
            case 6:
                return horario.isMiercoles();
            case 7:
                return horario.isJueves();
            case 8:
                return horario.isViernes();
            case 9:
                return horario.isSabado();
            case 10:
                return horario.isDomingo();
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex >= 4){
            return Boolean.class;
        }else{
            return String.class;
        }
    }
    
}
