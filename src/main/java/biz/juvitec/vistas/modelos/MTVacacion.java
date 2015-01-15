/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.vistas.modelos;

import biz.juvitec.entidades.Feriado;
import biz.juvitec.entidades.Vacacion;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author RyuujiMD
 */
public class MTVacacion extends AbstractTableModel {

    private List<Vacacion> vacaciones;
    private final Calendar cal;
    private int lunesAViernes = 0;
    private int sabadoADomingo = 0;

    public MTVacacion(List<Vacacion> vacaciones) {
        this.cal = Calendar.getInstance();
        this.vacaciones = vacaciones;
        analisis(vacaciones);
    }

    public List<Vacacion> getVacaciones() {
        return vacaciones;
    }

    public void setVacaciones(List<Vacacion> vacaciones) {
        this.vacaciones = vacaciones;
        analisis(vacaciones);
        this.fireTableDataChanged();
    }
    
    

    public void analisis(List<Vacacion> vacaciones) {
        lunesAViernes = 0;
        sabadoADomingo = 0;
        for (Vacacion vacacion : vacaciones) {
            Date fechaInicio = vacacion.getFechaInicio();
            Date fechaFin = vacacion.isHayInterrupcion() ? vacacion.getFechaInterrupcion() : vacacion.getFechaFin();

            while (fechaInicio.compareTo(fechaFin) <= 0) {
                cal.setTime(fechaInicio);
                if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    sabadoADomingo++;
                } else {
                    lunesAViernes++;
                }
                cal.add(Calendar.DAY_OF_MONTH, 1);
                fechaInicio = cal.getTime();
            }
        }
    }

    @Override
    public String getColumnName(int column) {
        return ""; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getRowCount() {
        return 2;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (rowIndex) {
            case 0:
                switch (columnIndex) {
                    case 0:
                        return "Lunes a viernes:";
                    case 1:
                        return lunesAViernes;
                    default:
                        return null;
                }
            case 1:
                switch (columnIndex) {
                    case 0:
                        return "Sabado a domingo";
                    case 1:
                        return sabadoADomingo;
                    default:
                        return null;
                }
            default:
                return null;
        }

    }

}
