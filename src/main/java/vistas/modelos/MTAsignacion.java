/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.modelos;

import biz.juvitec.controladores.EmpleadoControlador;
import biz.juvitec.entidades.AsignacionHorario;
import biz.juvitec.entidades.Empleado;
import com.personal.utiles.ModeloTabla;
import java.util.List;

/**
 *
 * @author fesquivelc
 */
public class MTAsignacion extends ModeloTabla<AsignacionHorario>{
    private EmpleadoControlador ec;

    public MTAsignacion(List<AsignacionHorario> datos, String[] nombreColumnas) {
        super(datos, nombreColumnas);
        ec = new EmpleadoControlador();
    }

    @Override
    public Object getValorEn(int rowIndex, int columnIndex) {
        AsignacionHorario seleccion = this.datos.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return seleccion.getHorario().getNombre();
            case 1:
                return seleccion.getHorario().getJornada().getNombre();
            case 2:
                return seleccion.getHorario().isLunes();
            case 3:
                return seleccion.getHorario().isMartes();
            case 4:
                return seleccion.getHorario().isMiercoles();
            case 5:
                return seleccion.getHorario().isJueves();
            case 6:
                return seleccion.getHorario().isViernes();
            case 7:
                return seleccion.getHorario().isSabado();
            case 8:
                return seleccion.getHorario().isDomingo();
            case 9:
                if(seleccion.isPorGrupo()){
                    return seleccion.getGrupoHorario().getNombre();                    
                }else{
                    Empleado empleado = ec.buscarPorId(seleccion.getEmpleado());
                    return empleado.getApellidoPaterno() + " " + empleado.getApellidoMaterno() + " " + empleado.getNombre();
                }
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 2: case 3: case 4: case 5: case 6: case 7: case 8:
                return Boolean.class;
            default:
                return String.class;
        }
    }
    
    
    
}
