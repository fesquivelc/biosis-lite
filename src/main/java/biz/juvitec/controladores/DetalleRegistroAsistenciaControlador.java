/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.controladores;

import biz.juvitec.entidades.DetalleRegistroAsistencia;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 *
 * @author RyuujiMD
 */
public class DetalleRegistroAsistenciaControlador extends Controlador<DetalleRegistroAsistencia>{
    private static final Logger LOG = Logger.getLogger(DetalleRegistroAsistenciaControlador.class.getName());

    public DetalleRegistroAsistenciaControlador() {
        super(DetalleRegistroAsistencia.class);
    }
    
    public boolean viajarTiempo(Date fechaDesde){
//        String sql1 = "DELETE d FROM "
//                + "detalle_registro_asistencia d INNER JOIN registro_asistencia on d.registro_asistencia_id = r.id "
//                + "WHERE r.id in :dnis AND r.fecha >= fecha;";
//        String sql2 = "DELETE FROM registro_asistencia AS r WHERE r.fecha >= :fecha;";
//        
//        String sql3 = "UPDATE tc_analisis AS tc SET tc.fecha = :fecha, tc.hora = :hora WHERE tc.empleado_nro_documento in :dnis;";
//        
//        String sqlUltimo = sql1+s;
//        
        String jpql = "DELETE FROM DetalleRegistroAsistencia AS d WHERE d.registroAsistencia.fecha >= :fechaDesde";        
        String sql = "delete d from detalle_registro_asistencia d inner join registro_asistencia r on d.registro_asistencia_id = r.id where r.fecha >= :fechaDesde";
        try {
            this.getDao().getEntityManager().getTransaction().begin();            
            this.getDao().getEntityManager().createNativeQuery(sql).setParameter("fechaDesde", fechaDesde).executeUpdate();
            this.getDao().getEntityManager().getTransaction().commit();
            return true;
        } catch (Exception e) {
            LOG.error(e);
            return false;
        }
    }
}
