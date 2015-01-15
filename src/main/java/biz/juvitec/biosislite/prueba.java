/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.biosislite;

import biz.juvitec.controladores.EmpleadoControlador;
import biz.juvitec.controladores.RolControlador;
import biz.juvitec.controladores.UsuarioControlador;
import biz.juvitec.dao.DAOMINEDU;
import biz.juvitec.entidades.Empleado;
import biz.juvitec.entidades.Rol;
import biz.juvitec.entidades.Usuario;
import biz.juvitec.vistas.mantenimientos.CRUDGrupoHorario;
import com.personal.utiles.ReporteUtil;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.internal.SessionFactoryImpl;
import utiles.Encriptador;

/**
 *
 * @author fesquivelc
 */
public class prueba {

    /**
     * @param args the command line arguments
     */
    private static final Logger LOG = Logger.getLogger(prueba.class.getName());
    
    public void getPrueba(){
        System.out.println(getClass().getClassLoader().getResource("."));
    }

    public static void main(String[] args) {
        // TODO code application logic here
//        DAOMINEDU dao = new DAOMINEDU(Empleado.class);
//        EntityManager em = dao.getEntityManager();
//        Session sesion = (Session) em.getDelegate();
//        SessionFactoryImpl sessionFactory = (SessionFactoryImpl) sesion.getSessionFactory();
//        Connection connection;
//        try {
//            connection = sessionFactory.getConnectionProvider().getConnection();
//            Statement st = connection.createStatement();
//            ResultSet rs = st.executeQuery("SELECT * FROM v_empleado");
//            int conteo = 0;
//            while(rs.next()){
//                LOG.warn(rs.getString(1));
//                conteo++;
//            }
//            LOG.warn("CONTEO "+conteo);
//        } catch (SQLException e) {
//            LOG.error("ERROR " + e.getMessage());
//        }
        RolControlador rc = new RolControlador();
        UsuarioControlador uc = new UsuarioControlador();
        Rol rol = rc.buscarPorId("ADM");
        
        Usuario usuario = new Usuario();
        usuario.setLogin("admin");
        usuario.setPassword(Encriptador.encrypt("admin"));
        usuario.setEmpleado("46557081");
        usuario.setRol(rol);
        usuario.setActivo(true);
        uc.guardar(usuario);
        
        System.exit(0);
//        ReporteUtil reporteador = new ReporteUtil();
//        
//        File reporte=  new File("reportes/r_registro_asistencia_consolidado.jasper");
//        List<String> lista = new ArrayList<>();
//        lista.add("10001020");
//        String usuario = "fesquivelc";
//        Map<String, Object> parametros = new HashMap<>();
//        parametros.put("lista", lista);
//        parametros.put("usuario", usuario);
//        parametros.put("CONEXION_EMPLEADOS", dao.getConexion());
//        
//        reporteador.setConn(rc.getDao().getConexion());
//        reporteador.generarReporte(reporte, parametros, null);
        Date fecha1 = new Date();
        System.out.println("FECHA 1 "+fecha1.toString());
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha1);
        cal.add(Calendar.MINUTE, 34);
        cal.add(Calendar.SECOND, 14);        
        Date fecha2 = cal.getTime();
        System.out.println("FECHA 2 "+fecha2.toString());
        Long dif = fecha2.getTime() - fecha1.getTime();
        
        cal.setTime(fecha1);
        cal.add(Calendar.MILLISECOND, dif.intValue());
        System.out.println("FECHA 3 "+cal.getTime().toString());
    }

}
