/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.modelos;

import entidades.ConexionBean;
import com.personal.utiles.ModeloTabla;
import java.util.List;

/**
 *
 * @author fesquivelc
 */
public class MTConfiguracion extends ModeloTabla<ConexionBean> {

    public MTConfiguracion(List<ConexionBean> datos) {
        super(datos);
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public int getRowCount() {
        return 1; //To change body of generated methods, choose Tools | Templates.
    }
    
    

    @Override
    public Object getValorEn(int rowIndex, int columnIndex) {
        ConexionBean conexion = this.datos.get(0);
        switch(columnIndex){
            case 0:
                switch(rowIndex){
                    case 0: return "URL";
                    case 1: return "DRIVER";
                    case 2: return "USUARIO";
                    case 3: return "PASSWORD";  
                    default: return null;
                }
            case 1:
                switch(rowIndex){
                    case 0: return conexion.getUrl();
                    case 1: return conexion.getDriver();
                    case 2: return conexion.getUser();
                    case 3: return conexion.getPassword();  
                    default: return null;
                }
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        super.setValueAt(aValue, rowIndex, columnIndex); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
