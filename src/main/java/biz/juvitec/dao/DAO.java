/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.internal.SessionFactoryImpl;

/**
 *
 * @author gabriel
 * @param <T>
 */
public class DAO<T> {

    protected String PU = "biosis-PU";
    protected EntityManager em;
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
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(this.PU);
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
            getEntityManager().getTransaction().rollback();
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
            getEntityManager().getTransaction().rollback();
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
            getEntityManager().getTransaction().rollback();
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
            LOG.error("ERROR AL ELIMINAR: " + e.getLocalizedMessage() + " " + e.getMessage());
            return null;
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
}
