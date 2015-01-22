/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.controladores;

import biz.juvitec.entidades.TCAnalisis;
import com.personal.utiles.FechaUtil;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author fesquivelc
 */
public class TCAnalisisControlador extends Controlador<TCAnalisis>{

    public TCAnalisisControlador() {
        super(TCAnalisis.class);
    }

    public void retrocederTiempo(List<String> dnis, Date fecha) {
        fecha = FechaUtil.soloFecha(fecha);
        Date hora = FechaUtil.soloHora(fecha);
        String jpql = "DELETE FROM DetalleRegistroAsistencia d WHERE d.registroAsistencia.fecha >= :fecha "
                + "AND d.registroAsistencia.empleado in :dnis";
        
        String jpql2 = "DELETE FROM RegistroAsistencia r WHERE r.fecha >= :fecha AND r.empleado in :dnis";
        
        
        
        String sql1 = "DELETE d FROM "
                + "detalle_registro_asistencia d INNER JOIN registro_asistencia r on d.registro_asistencia_id = r.id "
                + "WHERE r.empleado_nro_documento IN :dnis AND r.fecha >= :fecha";
        String sql2 = "DELETE FROM registro_asistencia WHERE fecha >= :fecha AND empleado_nro_documento IN :dnis";
        
        String sql3 = "UPDATE tc_analisis SET fecha = :fecha, hora = :hora WHERE empleado_nro_documento IN :dnis";
        
        Calendar cal = Calendar.getInstance();
        cal.set(1970, 0, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        
        String jpql3 = "UPDATE TCAnalisis tc SET tc.fecha = :fecha AND tc.hora = :hora WHERE tc.empleado in :dnis";
        
        EntityManager em = this.getDao().getEntityManager();
        em.getTransaction().begin();
        em.createNativeQuery(sql1).setParameter("dnis", dnis).setParameter("fecha", fecha).executeUpdate();
        em.createNativeQuery(sql2).setParameter("dnis", dnis).setParameter("fecha", fecha).executeUpdate();
        em.createNativeQuery(sql3).setParameter("dnis", dnis).setParameter("fecha", fecha).setParameter("hora", hora).executeUpdate();
        em.getTransaction().commit();
        
//        this.getDao().getEntityManager().createQuery(jpql).setParameter("dnis", dnis).setParameter("fecha", fecha).executeUpdate();
//        this.getDao().getEntityManager().createQuery(jpql2).setParameter("dnis", dnis).setParameter("fecha", fecha).executeUpdate();
//        this.getDao().getEntityManager().createQuery(jpql3).setParameter("dnis", dnis).setParameter("fecha", fecha).setParameter("hora", cal.getTime()).executeUpdate();
    }
    
}
