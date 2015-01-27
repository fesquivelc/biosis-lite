/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.modelos;

import entidades.GrupoHorario;
import com.personal.utiles.ModeloTabla;
import java.util.List;

/**
 *
 * @author fesquivelc
 */
public class MTGrupoHorario extends ModeloTabla<GrupoHorario> {

    public MTGrupoHorario(List<GrupoHorario> datos, String[] nombreColumnas) {
        super(datos, nombreColumnas);
    }

    @Override
    public Object getValorEn(int rowIndex, int columnIndex) {
        GrupoHorario grupo = this.datos.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return grupo.getCodigo();
            case 1:
                return grupo.getNombre();
            default:
                return null;
        }
    }

}
