/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.controladores;

import biz.juvitec.dao.DAOMINEDU;
import biz.juvitec.entidades.Empleado;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author fesquivelc
 */
public class EmpleadoControlador extends Controlador<Empleado> {

    public EmpleadoControlador() {
        super(Empleado.class, new DAOMINEDU(Empleado.class));
    }

    public List<Empleado> buscarXPatron(String patron) {
        String jpql = "SELECT e FROM Empleado e WHERE "
                + "UPPER(CONCAT(nombre,apellidoPaterno,apellidoMaterno)) LIKE CONCAT('%',UPPER(:patron),'%') OR e.nroDocumento = UPPER(:patron) "
                + "ORDER BY e.apellidoPaterno,e.apellidoMaterno,e.nombre";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("patron", patron);
        return this.getDao().buscar(jpql, mapa);
    }
    
    public List<Empleado> buscarXPatron(String patron, int desde, int tamanio) {
        String jpql = "SELECT e FROM Empleado e WHERE "
                + "UPPER(CONCAT(nombre,apellidoPaterno,apellidoMaterno)) LIKE CONCAT('%',UPPER(:patron),'%') OR e.nroDocumento = UPPER(:patron) "
                + "ORDER BY e.apellidoPaterno,e.apellidoMaterno,e.nombre";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("patron", patron);
        return this.getDao().buscar(jpql, mapa, desde, tamanio);
    }
    
    public int totalXPatron(String patron){
        String jpql = "SELECT COUNT(e.nroDocumento) FROM Empleado e WHERE "
                + "UPPER(CONCAT(nombre,apellidoPaterno,apellidoMaterno)) LIKE CONCAT('%',UPPER(:patron),'%') OR e.nroDocumento = UPPER(:patron) ";
        Long cont = (Long)this.getDao().getEntityManager().createQuery(jpql)
                .setParameter("patron", patron).getSingleResult();
        int conteo = cont.intValue();
        return conteo;
    }

    public List<Empleado> buscarPorLista(List<String> lista) {
        String jpql = "SELECT e FROM Empleado e WHERE "
                + "e.nroDocumento IN :lista";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("lista", lista);
        return this.getDao().buscar(jpql, mapa);
    }

    public Empleado buscarPorId(int id) {
        String jpql = "SELECT e FROM Empleado e WHERE "
                + "CAST(e.nroDocumento AS integer) = :id ";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("id", id);

        List<Empleado> empleados = this.getDao().buscar(jpql, mapa, -1, 1);
        if (empleados.isEmpty()) {
            return null;
        } else {
            return empleados.get(0);
        }
    }

}
