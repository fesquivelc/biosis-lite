/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Periodo;
import java.util.List;

/**
 *
 * @author fesquivelc
 */
public class PeriodoControlador extends Controlador<Periodo>{

    public PeriodoControlador() {
        super(Periodo.class);
    }

    public List<Periodo> buscarTodosOrden() {
        String jpql = "SELECT p FROM Periodo p ORDER BY p.anio DESC";
        return this.getDao().buscar(jpql);
    }
    
}
