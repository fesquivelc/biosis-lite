/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.modelos;

import entidades.Empleado;
import entidades.RegistroAsistencia;
import com.personal.utiles.ModeloTabla;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author RyuujiMD
 */
public class MTRegistroAsistencia extends ModeloTabla<RegistroAsistencia>{
    private List<Empleado> empleadoList;
    private final DateFormat dfFecha;
    private final DateFormat dfMinutos;
    private final Calendar cal;

    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
    }        
    
    public MTRegistroAsistencia(List<RegistroAsistencia> datos) {
        super(datos);
        this.dfFecha = new SimpleDateFormat("dd/MM/yyyy");
        this.dfMinutos = new SimpleDateFormat("HH:mm:ss");
        this.cal = Calendar.getInstance();
        this.nombreColumnas = new String[]{"Nro de doc.","Nombre del empleado","Fecha","Tipo de asist.","Tardanza total"};
    }

    @Override
    public Object getValorEn(int rowIndex, int columnIndex) {
        RegistroAsistencia registro = this.datos.get(rowIndex);
        switch(columnIndex){
            case 0:
                return registro.getEmpleado();
            case 1:
                return this.nombreCompleto(registro.getEmpleado()).trim();
            case 2:
                return this.dfFecha.format(registro.getFecha());
            case 3:
                return this.tipoAsistencia(registro.getTipoAsistencia());
            case 4:
                return registro.getMinTardanza();
            default:
                return null;
        }
    }
    
    private String nombreCompleto(String dni){
        String nombre = "";
        for(Empleado empleado : empleadoList){
            if(empleado.getNroDocumento().equals(dni)){
                return empleado.getApellidoPaterno() + " " + empleado.getApellidoMaterno() + " " + empleado.getNombre();
            }
        }
        return nombre;
    }
    
    private String tipoAsistencia(char tipo){
        switch(tipo){
            case 'F':
                return "FALTA";
            case 'R':
                return "ASIST. REGULAR";
            case 'T':
                return "TARDANZA";
            case 'P':
                return "PERMISO";
            case 'V':
                return "VACACIONES";
            case 'E':
                return "FERIADO";
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 4){
            return BigDecimal.class;
        }else{
            return String.class;
        }
    }
    
    
    
}
