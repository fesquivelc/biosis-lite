/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Periodo;
import entidades.SaldoVacacional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author fesquivelc
 */
public class SaldoVacacionalControlador extends Controlador<SaldoVacacional>{

    public SaldoVacacionalControlador() {
        super(SaldoVacacional.class);
    }
    
    public SaldoVacacional buscarXPeriodo(String dni, Periodo periodo){
        String jpql = "SELECT s FROM SaldoVacacional s WHERE s.periodo = :periodo and s.empleado = :dni";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("dni", dni);
        mapa.put("periodo", periodo);
        
        List<SaldoVacacional> lista =  this.getDao().buscar(jpql, mapa, -1, 1);
        if(lista.isEmpty()){
            return null;
        }else{
            return lista.get(0);
        }
    }
}
