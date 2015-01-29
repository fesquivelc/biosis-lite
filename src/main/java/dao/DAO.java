/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.personal.utiles.ParametrosUtil;
import com.personal.utiles.PropertiesUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.internal.SessionFactoryImpl;
import utiles.Encriptador;

/**
 *
 * @author gabriel
 * @param <T>
 */
public class DAO<T> {

    protected String PU = "biosis-PU";
    protected static EntityManager em;
    protected Class<T> clase;
    private static final Logger LOG = Logger.getLogger(DAO.class.getName());

    public DAO(Class<T> clase) {
        this.clase = clase;
    }

    public DAO() {
        this.clase = null;
    }

    public EntityManager getEntityManager() {
        if (em == null) {
            Properties configuracion = PropertiesUtil.cargarProperties("config/biosis-config.properties");
            int tipoBD = Integer.parseInt(configuracion.getProperty("tipo"));

            String driver = ParametrosUtil.obtenerDriver(tipoBD);
            String url = configuracion.getProperty("url");
            String usuario = configuracion.getProperty("usuario");
            String password = configuracion.getProperty("password");
            String accion = configuracion.getProperty("action");

            Map<String, String> properties = new HashMap<>();
            properties.put("javax.persistence.jdbc.user", usuario);
            properties.put("javax.persistence.jdbc.password", Encriptador.decrypt(password));
            properties.put("javax.persistence.jdbc.driver", driver);
            properties.put("javax.persistence.jdbc.url", url);
            properties.put("javax.persistence.schema-generation.database.action", accion);
            
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(this.PU,properties);
            em = emf.createEntityManager();
        }
        return em;
    }

    public Connection getConexion() {
        Session sesion = (Session) getEntityManager().getDelegate();
        SessionFactoryImpl sessionFactory = (SessionFactoryImpl) sesion.getSessionFactory();
        Connection connection = null;
        try {
            connection = sessionFactory.getConnectionProvider().getConnection();
            if (connection == null) {
                LOG.error("NO SE PUDO OBTENER LA CONEXION");
            }
        } catch (SQLException e) {
            LOG.error("ERROR " + e.getMessage());
        }
        return connection;

    }

    public boolean guardar(T objeto) {
        try {
            getEntityManager().getTransaction().begin();
            getEntityManager().persist(objeto);
            getEntityManager().getTransaction().commit();
            return true;
        } catch (Exception e) {
            LOG.error("ERROR EN EL GUARDADO: " + e.getLocalizedMessage() + " " + e.getMessage());
            return false;
        }

    }
    
    public boolean guardarLote(List<T> lote){
        try {
            getEntityManager().getTransaction().begin();
            for(T objeto : lote){
                getEntityManager().persist(objeto);
            }
            getEntityManager().getTransaction().commit();
            return true;
        } catch (Exception e) {
            LOG.error("ERROR EN EL GUARDADO POR LOTE: " + e.getLocalizedMessage() + " " + e.getMessage());
            return false;
        }
    }

    public boolean modificar(T objeto) {
        try {
            getEntityManager().getTransaction().begin();
            getEntityManager().merge(objeto);
            getEntityManager().getTransaction().commit();
            return true;
        } catch (Exception e) {
            LOG.error("ERROR AL MODIFICAR: " + e.getLocalizedMessage() + " " + e.getMessage());
            return false;
        }

    }

    public boolean eliminar(T objeto) {
        try {
            getEntityManager().getTransaction().begin();
            getEntityManager().remove(objeto);
            getEntityManager().getTransaction().commit();
            return true;
        } catch (Exception e) {
            LOG.error("ERROR AL ELIMINAR: " + e.getLocalizedMessage() + " " + e.getMessage());
            return false;
        }
    }

    public List<T> buscar(String queryJPQL) {
        return this.buscar(queryJPQL, null, -1, -1);
    }

    public List<T> buscar(String queryJPQL, Map<String, Object> parametros) {
        return this.buscar(queryJPQL, parametros, -1, -1);
    }

    public List<T> buscar(String queryJPQL, Map<String, Object> parametros, int inicio, int tamanio) {
        try {
            Query query = getEntityManager().createQuery(queryJPQL);

            if (parametros != null) {
                for (Map.Entry<String, Object> entry : parametros.entrySet()) {
                    query.setParameter(entry.getKey(), entry.getValue());
                }
            }

            if (inicio != -1) {
                query.setFirstResult(inicio);
            }

            if (tamanio != -1) {
                query.setMaxResults(tamanio);
            }

            List<T> lista = query.getResultList();

            return lista;
        } catch (Exception e) {
            LOG.error("ERROR AL BUSCAR: " + e.getLocalizedMessage() + " " + e.getMessage());
            return null;
        }

    }
    
    public int contar(String queryJPQL, Map<String, Object> parametros) {
        try {
            Query query = getEntityManager().createQuery(queryJPQL);

            if (parametros != null) {
                for (Map.Entry<String, Object> entry : parametros.entrySet()) {
                    query.setParameter(entry.getKey(), entry.getValue());
                }
            }

            Long conteo = (Long)query.getSingleResult();

            return conteo.intValue();
        } catch (Exception e) {
            LOG.error("ERROR AL CONTAR: " + e.getLocalizedMessage() + " " + e.getMessage());
            return 0;
        }

    }

    public List<T> buscarTodos() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(clase));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public int contar() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(clase);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public T buscarPorId(Object id) {
        return getEntityManager().find(clase, id);
    }

    private void rollback() {
        getEntityManager().getTransaction().begin();
        getEntityManager().getTransaction().rollback();
    }
}
