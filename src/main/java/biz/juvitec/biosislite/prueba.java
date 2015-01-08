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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
//        UsuarioControlador uc = new UsuarioControlador();
//        Rol rol = rc.buscarPorId("ADM");
//        
//        Usuario usuario = new Usuario();
//        usuario.setLogin("fesquivelc");
//        usuario.setPassword(Encriptador.encrypt("Elhacker12"));
//        usuario.setEmpleado("10001020");
//        usuario.setRol(rol);
//        usuario.setActivo(true);
//        uc.guardar(usuario);
        System.out.println(CRUDGrupoHorario.class.getClass().getName());
        System.out.println(CRUDGrupoHorario.class.getSimpleName());
    }

}
