/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.vistas.modelos;

import com.personal.utiles.ModeloTabla;
import java.util.List;

/**
 *
 * @author RyuujiMD
 */
public class MTOficina extends ModeloTabla<String>{

    public MTOficina(List<String> datos) {
        super(datos);
        this.nombreColumnas = new String[]{"Oficina"};
    }

    @Override
    public Object getValorEn(int rowIndex, int columnIndex) {
        return this.datos.get(rowIndex);
    }
    
}
