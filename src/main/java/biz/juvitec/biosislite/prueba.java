/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.biosislite;

import biz.juvitec.controladores.EmpleadoBeanControlador;
import biz.juvitec.controladores.EmpleadoControlador;
import biz.juvitec.controladores.RolControlador;
import biz.juvitec.controladores.UsuarioControlador;
import biz.juvitec.dao.DAOMINEDU;
import biz.juvitec.entidades.Empleado;
import biz.juvitec.entidades.EmpleadoBean;
import biz.juvitec.entidades.Rol;
import biz.juvitec.entidades.Usuario;
import vistas.mantenimientos.CRUDGrupoHorario;
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
import java.util.Random;
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

    public void getPrueba() {
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
//        RolControlador rc = new RolControlador();
//        UsuarioControlador uc = new UsuarioControlador();
//        Rol rol = rc.buscarPorId("ADM");
//        
//        Usuario usuario = new Usuario();
//        usuario.setLogin("admin");
//        usuario.setPassword(Encriptador.encrypt("admin"));
//        usuario.setEmpleado("46557081");
//        usuario.setRol(rol);
//        usuario.setActivo(true);
//        uc.guardar(usuario);

//        System.exit(0);
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
//        Date fecha1 = new Date();
//        System.out.println("FECHA 1 "+fecha1.toString());
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(fecha1);
//        cal.add(Calendar.MINUTE, 34);
//        cal.add(Calendar.SECOND, 14);        
//        Date fecha2 = cal.getTime();
//        System.out.println("FECHA 2 "+fecha2.toString());
//        Long dif = fecha2.getTime() - fecha1.getTime();
//        
//        cal.setTime(fecha1);
//        cal.add(Calendar.MILLISECOND, dif.intValue());
//        System.out.println("FECHA 3 "+cal.getTime().toString());
        Calendar calContrato = Calendar.getInstance();

//        calContrato.set(1990, 0, 1, 0, 0, 0);

        Calendar calFechaNacimiento = Calendar.getInstance();

        System.out.println("RANDOM: " + randInt(1, 28));
        System.out.println("RANDOM: " + randInt(1, 28));
        System.out.println("RANDOM: " + randInt(1, 28));
        System.out.println("RANDOM: " + randInt(0, 11));
        System.out.println("RANDOM: " + randInt(0, 11));

        EmpleadoBeanControlador cont = new EmpleadoBeanControlador();
        List<EmpleadoBean> empleados = new ArrayList<>();
        EmpleadoBean empleado;

        for (int i = 1; i <= 4000; i++) {
            calContrato.set(randInt(1990, 2014), randInt(0, 11), randInt(1, 28), 0, 0, 0);
            calFechaNacimiento.set(randInt(1970, 1990), randInt(0, 11), randInt(1, 28), 0, 0, 0);
            empleado = new EmpleadoBean();
            empleado.setApellidoPaterno("PATERNO " + i);
            empleado.setApellidoMaterno("MATERNO " + i);
            empleado.setNombre("NOMBRE " + i);
            empleado.setFechaInicioContrato(calContrato.getTime());
            empleado.setFechaNacimiento(calFechaNacimiento.getTime());
            empleado.setRegimenLaboral("CAS");
            empleado.setNroDocumento(conCeros(i));
            empleado.setTipoDocumento("DNI");
            empleado.setCodigoModular(conCeros(10000 - i));
            empleados.add(empleado);
        }
        
        cont.guardarLote(empleados);

        System.exit(0);
    }
    static Random rand;

    public static int randInt(int min, int max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        if (rand == null) {
            rand = new Random();
        }

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public static String conCeros(int dni) {
        if (dni <= 9) {
            return "0000000" + dni;
        } else if (dni <= 99) {
            return "000000" + dni;
        } else if (dni <= 999) {
            return "00000" + dni;
        } else {
            return "0000" + dni;
        }
    }

    
}
