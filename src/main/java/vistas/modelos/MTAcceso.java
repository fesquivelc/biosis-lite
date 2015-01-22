/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.modelos;

import biz.juvitec.entidades.Acceso;
import biz.juvitec.entidades.Rol;
import biz.juvitec.entidades.RolAcceso;
import com.personal.utiles.ModeloTabla;
import java.util.List;

/**
 *
 * @author RyuujiMD
 */
public class MTAcceso extends ModeloTabla<Acceso>{
    private final boolean[] accesoSeleccionado;
    private List<RolAcceso> datosExistentes;
    private Rol rolSeleccionado;

    public MTAcceso(List<Acceso> datos, List<RolAcceso> datosExistentes, Rol rol) {
        super(datos);
        this.nombreColumnas = new String[]{"Seleccionado","Acceso"};
        accesoSeleccionado = new boolean[datos.size()];
        this.rolSeleccionado = rol;
        this.datosExistentes = datosExistentes;
        
    }

    public Rol getRolSeleccionado() {
        return rolSeleccionado;
    }

    public void setRolSeleccionado(Rol rolSeleccionado) {
        this.rolSeleccionado = rolSeleccionado;
    }

    public List<RolAcceso> getDatosExistentes() {
        return datosExistentes;
    }

    public void setDatosExistentes(List<RolAcceso> datosExistentes) {
        this.datosExistentes = datosExistentes;
        this.fireTableDataChanged();
    }

    
    
    @Override
    public Object getValorEn(int rowIndex, int columnIndex) {
        Acceso acceso = this.datos.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                this.accesoSeleccionado[rowIndex] = this.contiene(acceso, datosExistentes) >= 0;
                return this.accesoSeleccionado[rowIndex];
            case 1:
                return acceso.getNombre();
            default:
                return null;
        }
    }
    
    private int contiene(Acceso acceso, List<RolAcceso> rolAcceso){
        
        for(int i = 0; i< rolAcceso.size(); i++){
            if(rolAcceso.get(i).getAcceso().equals(acceso)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 0;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(columnIndex == 0){
            boolean aceptado = Boolean.parseBoolean(aValue.toString());
            if(aceptado){
                RolAcceso ra = new RolAcceso();
                ra.setAcceso(this.datos.get(rowIndex));
                ra.setRol(this.rolSeleccionado);
                this.datosExistentes.add(ra);
            }else{
                int content = this.contiene(this.datos.get(rowIndex), datosExistentes);
                if(content != -1){
                    this.datosExistentes.remove(content);
                }
            }
            this.fireTableRowsUpdated(rowIndex, rowIndex);
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 0){
            return Boolean.class;
        }else{
            return String.class;
        }
    }
    
    

    
}
