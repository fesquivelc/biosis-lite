/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.renders;

import java.awt.Component;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 *
 * @author RyuujiMD
 */
public class RenderFecha extends DefaultListCellRenderer{

    private static final DateFormat dfFecha = new SimpleDateFormat("dd/MM/yyyy");
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        String fecha;
        if(value instanceof Date){
            fecha = dfFecha.format((Date)value);
            return super.getListCellRendererComponent(list, fecha, index, isSelected, cellHasFocus); //To change body of generated methods, choose Tools | Templates.
        }else{
            return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
}
