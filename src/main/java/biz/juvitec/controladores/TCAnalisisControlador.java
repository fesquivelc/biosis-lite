/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.controladores;

import biz.juvitec.entidades.TCAnalisis;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author fesquivelc
 */
public class TCAnalisisControlador extends Controlador<TCAnalisis>{

    public TCAnalisisControlador() {
        super(TCAnalisis.class);
    }

    public void retrocederTiempo(List<String> dnis, Date fecha) {
        String jpql = "DELETE FROM DetalleRegistroAsistencia d WHERE d.registroAsistencia.fecha >= :fecha "
                + "AND d.registroAsistencia.empleado in :dnis";
        
        String jpql2 = "DELETE FROM RegistroAsistencia r WHERE r.fecha >= :fecha AND r.empleado in :dnis";
        
        Calendar cal = Calendar.getInstance();
        cal.set(1970, 0, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        
        String jpql3 = "UPDATE TCAnalisis tc SET tc.fecha = :fecha AND tc.hora = :hora WHERE tc.empleado in :dnis";
        
        this.getDao().getEntityManager().createQuery(jpql).setParameter("dnis", dnis).setParameter("fecha", fecha).executeUpdate();
        this.getDao().getEntityManager().createQuery(jpql2).setParameter("dnis", dnis).setParameter("fecha", fecha).executeUpdate();
        this.getDao().getEntityManager().createQuery(jpql3).setParameter("dnis", dnis).setParameter("fecha", fecha).setParameter("hora", cal.getTime()).executeUpdate();
    }
    
}
