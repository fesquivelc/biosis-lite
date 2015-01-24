/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.renders;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author RyuujiMD
 */
public class RenderAsistencia extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        
        Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
        this.setOpaque(true);
        System.out.println("ROW AND COLUMN: " + row + " " + column);
        String valor = table.getValueAt(row, 3).toString();
        System.out.println("VALOR: " + valor);
        if (valor.equalsIgnoreCase("FALTA")) {
            comp.setForeground(Color.RED);
        } else if (valor.equalsIgnoreCase("REGULAR")) {
            comp.setForeground(Color.GREEN);
        } else if (valor.equalsIgnoreCase("TARDANZA")) {
            comp.setForeground(Color.YELLOW);
        }
        return comp;
    }

}
