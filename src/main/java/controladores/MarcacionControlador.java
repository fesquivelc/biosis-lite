/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.DAOBIOSTAR;
import entidades.Marcacion;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author fesquivelc
 */
public class MarcacionControlador extends Controlador<Marcacion> {

    public MarcacionControlador() {
        super(Marcacion.class, new DAOBIOSTAR(Marcacion.class));
    }

    public List<Marcacion> buscarXFecha(String dni, Date fechaInicio, Date fechaFin) {
        String jpql = "SELECT m FROM Marcacion m WHERE CONCAT(:ceros,m.empleado) = :dni AND m.fecha BETWEEN :fechaInicio AND :fechaFin";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("dni", dni);
        mapa.put("fechaInicio", fechaInicio);
        mapa.put("fechaFin", fechaFin);
        mapa.put("ceros", this.ceros(dni));
        return this.getDao().buscar(jpql, mapa);
    }

    public List<Marcacion> buscarXFecha(String dni, Date fechaInicio, Date fechaFin, int desde, int tamanio) {
        String jpql = "SELECT m FROM Marcacion m WHERE m.empleado = :dni AND m.fecha BETWEEN :fechaInicio AND :fechaFin "
                + "ORDER BY m.nombre,m.fecha,m.hora";
        LOG.error("DOCUMENTO: " + dni);
        Map<String, Object> mapa = new HashMap<>();

        mapa.put("dni", Integer.parseInt(dni));
        mapa.put("fechaInicio", fechaInicio);
        mapa.put("fechaFin", fechaFin);
        List<Marcacion> marcaciones = this.getDao().buscar(jpql, mapa, desde, tamanio);
        return marcaciones;
    }

    public List<Marcacion> buscarXFecha(Date fechaInicio, Date fechaFin) {
        String jpql = "SELECT m FROM Marcacion m WHERE m.fecha BETWEEN :fechaInicio AND :fechaFin";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("fechaInicio", fechaInicio);
        mapa.put("fechaFin", fechaFin);
        return this.getDao().buscar(jpql, mapa);
    }

    public List<Marcacion> buscarXFecha(Date fecha) {
        String jpql = "SELECT m FROM Marcacion m WHERE m.fecha = :fecha";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("fecha", fecha);
        return this.getDao().buscar(jpql, mapa);
    }

    public List<Marcacion> buscarXFecha(String dni, Date fecha) {
        String jpql = "SELECT m FROM Marcacion m WHERE m.empleado = :dni AND m.fecha = :fecha ORDER BY m.hora ASC";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("fecha", fecha);
        mapa.put("dni", Integer.parseInt(dni));
//        mapa.put("ceros", this.ceros(dni));
        return this.getDao().buscar(jpql, mapa);
    }

    public List<Marcacion> buscarXFecha(Date fechaInicio, Date fechaFin, int desde, int tamanio) {
        String jpql = "SELECT m FROM Marcacion m WHERE m.fecha BETWEEN :fechaInicio AND :fechaFin "
                + "ORDER BY m.nombre,m.fecha,m.hora";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("fechaInicio", fechaInicio);
        mapa.put("fechaFin", fechaFin);
        List<Marcacion> lista = this.getDao().buscar(jpql, mapa, desde, tamanio);
        return lista;
    }

    public List<Marcacion> buscarXFechaXHora(Date fechaInicio, Date horaInicio, Date horaFin, int desde, int tamanio) {
        String jpql = "SELECT m FROM Marcacion m WHERE m.fecha = :fechaInicio AND m.hora BETWEEN :horaInicio AND :horaFin "
                + "ORDER BY m.nombre,m.fecha,m.hora";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("fechaInicio", fechaInicio);
        mapa.put("horaInicio", horaInicio);
        mapa.put("horaFin", horaFin);
        List<Marcacion> lista = this.getDao().buscar(jpql, mapa, desde, tamanio);
        return lista;
    }

    private static final Logger LOG = Logger.getLogger(MarcacionControlador.class.getName());

    public int totalXEmpleadoXFecha(String dni, Date fechaInicio, Date fechaFin) {
        String jpql = "SELECT COUNT(m.id) FROM Marcacion m WHERE m.empleado = :dni AND m.fecha BETWEEN :fechaInicio AND :fechaFin";
        Long cont = (Long) this.getDao().getEntityManager().createQuery(jpql)
                .setParameter("dni", Integer.parseInt(dni))
                .setParameter("fechaInicio", fechaInicio)
                .setParameter("fechaFin", fechaFin).getSingleResult();
        int conteo = cont.intValue();
        return conteo;
    }

    public int totalXFecha(Date fechaInicio, Date fechaFin) {
        String jpql = "SELECT COUNT(m.id) FROM Marcacion m WHERE m.fecha BETWEEN :fechaInicio AND :fechaFin ";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("fechaInicio", fechaInicio);
        mapa.put("fechaFin", fechaFin);
        return this.getDao().contar(jpql, mapa);
    }

    private String ceros(String dni) {
        String ceros = "";
        int nDNI = Integer.parseInt(dni);
        System.out.println("DNI: " + dni);
        int tamanio1 = dni.length();
        int tamanio2 = (nDNI + "").length();

        for (int i = 1; i <= tamanio1 - tamanio2; i++) {
            ceros += "0";
        }
//        System.out.println("CEROS: "+ceros);
        System.out.println("CEROS: " + ceros);
        return ceros;
    }

    public Marcacion buscarXFechaXhora(String dni, Date fecha, Date horaI, Date horaF) {
        String jpql = "SELECT m FROM Marcacion m WHERE "
                + "m.empleado = :dni "
                + "AND m.fecha = :fecha "
                + "AND m.hora BETWEEN :horaI AND :horaF "
                + "ORDER BY m.hora ASC";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("dni", Integer.parseInt(dni));
        mapa.put("fecha", fecha);
        mapa.put("horaI", horaI);
        mapa.put("horaF", horaF);
        List<Marcacion> marcaciones = this.getDao().buscar(jpql, mapa, -1, 1);

        if (marcaciones.isEmpty()) {
            return null;
        } else {
            return marcaciones.get(0);
        }
    }

    public List<Object[]> buscarEmpleadosXEvento(int evento, boolean dentro) {
        String sql = "SELECT u.sUserID,CONVERT(varchar,u.sUserName),CONVERT (varchar,dep.sName) FROM TB_USER u LEFT JOIN TB_USER_DEPT dep on u.nDepartmentIdn = dep.nDepartmentIdn WHERE u.sUserID";
        if (dentro) {
            sql += " IN ";
        } else {
            sql += " NOT IN ";
        }
        sql += "(SELECT "
                + "TB_USER.sUserID "
                + "FROM TB_EVENT_LOG "
                + "INNER JOIN TB_USER ON TB_EVENT_LOG.nUserID = TB_USER.nUserIdn "
                + "WHERE TB_EVENT_LOG.nEventIdn = :evento)";
        sql += " ORDER BY u.sUserID,u.sUserName";
        System.out.println("SQL: " + sql);
        List<Object[]> lista = this.getDao().getEntityManager().createNativeQuery(sql).setParameter("evento", evento).getResultList();
        return lista;
    }

    public List<Marcacion> buscarXFechaXHora(String dni, Date fechaInicio, Date horaInicio, Date horaFin, int desde, int tamanio) {
        String jpql = "SELECT m FROM Marcacion m WHERE "
                + "m.empleado = :dni "
                + "AND m.fecha = :fecha "
                + "AND m.hora BETWEEN :horaI AND :horaF "
                + "ORDER BY m.hora ASC";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("dni", Integer.parseInt(dni));
        mapa.put("fecha", fechaInicio);
        mapa.put("horaI", horaInicio);
        mapa.put("horaF", horaFin);
        List<Marcacion> marcaciones = this.getDao().buscar(jpql, mapa, desde, tamanio);
        return marcaciones;
    }
    
    public List<Marcacion> buscarXFechaXHora(List<Integer> dni, Date fechaInicio, Date horaInicio, Date horaFin, int desde, int tamanio) {
        String jpql = "SELECT m FROM Marcacion m WHERE "
                + "m.empleado IN :dni "
                + "AND m.fecha = :fecha "
                + "AND m.hora BETWEEN :horaI AND :horaF "
                + "ORDER BY m.hora ASC";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("dni", dni);
        mapa.put("fecha", fechaInicio);
        mapa.put("horaI", horaInicio);
        mapa.put("horaF", horaFin);
        List<Marcacion> marcaciones = this.getDao().buscar(jpql, mapa, desde, tamanio);
        return marcaciones;
    }

    public List<Marcacion> buscarXFecha(List<Integer> empleados, Date fechaInicio, Date fechaFin, int desde, int tamanio) {
        String jpql = "SELECT m FROM Marcacion m WHERE m.empleado IN :dni AND m.fecha BETWEEN :fechaInicio AND :fechaFin "
                + "ORDER BY m.nombre,m.fecha,m.hora";
        Map<String, Object> mapa = new HashMap<>();

        mapa.put("dni", empleados);
        mapa.put("fechaInicio", fechaInicio);
        mapa.put("fechaFin", fechaFin);
        List<Marcacion> marcaciones = this.getDao().buscar(jpql, mapa, desde, tamanio);
        return marcaciones;
    }

    public int totalXEmpleadoXFecha(List<Integer> empleados, Date fechaInicio, Date fechaFin) {
        String jpql = "SELECT COUNT(m.id) FROM Marcacion m WHERE m.empleado IN :dni AND m.fecha BETWEEN :fechaInicio AND :fechaFin";
        Long cont = (Long) this.getDao().getEntityManager().createQuery(jpql)
                .setParameter("dni", empleados)
                .setParameter("fechaInicio", fechaInicio)
                .setParameter("fechaFin", fechaFin).getSingleResult();
        int conteo = cont.intValue();
        return conteo;
    }
    public int totalXFechaXHora(Date fechaInicio, Date horaInicio, Date horaFin) {
        String jpql = "SELECT COUNT(m.id) FROM Marcacion m WHERE m.fecha = :fechaInicio AND m.hora BETWEEN :horaInicio AND :horaFin ";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("fechaInicio", fechaInicio);
        mapa.put("horaInicio", horaInicio);
        mapa.put("horaFin", horaFin);
        return this.getDao().contar(jpql, mapa);
    }

    public int totalXFecha(List<Integer> empleados, Date fechaInicio, Date fechaFin) {
        String jpql;
        jpql = "SELECT COUNT(m.id) FROM Marcacion m WHERE m.empleado IN :dni AND m.fecha BETWEEN :fechaInicio AND :fechaFin ";
        Map<String, Object> mapa = new HashMap<>();

        mapa.put("dni", empleados);
        mapa.put("fechaInicio", fechaInicio);
        mapa.put("fechaFin", fechaFin);
        return this.getDao().contar(jpql, mapa);
    }

    public int totalXFechaXHora(List<Integer> dni, Date fechaInicio, Date horaInicio, Date horaFin) {
        String jpql = "SELECT COUNT(m.id) FROM Marcacion m WHERE "
                + "m.empleado IN :dni "
                + "AND m.fecha = :fecha "
                + "AND m.hora BETWEEN :horaI AND :horaF ";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("dni", dni);
        mapa.put("fecha", fechaInicio);
        mapa.put("horaI", horaInicio);
        mapa.put("horaF", horaFin);
        return this.getDao().contar(jpql, mapa);
    }

}
