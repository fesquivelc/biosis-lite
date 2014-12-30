/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.dao;

import com.personal.utiles.ParametrosUtil;
import com.personal.utiles.PropertiesUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Kyon
 * @param <T>
 */
public class DAOMINEDU<T> extends DAO<T> {
    private static EntityManager entityManagerMINEDU;

    public DAOMINEDU(Class<T> clase) {
        this.PU = "rrhh-PU";
        this.clase = clase;
    }

    @Override
    public EntityManager getEntityManager() {
        if (entityManagerMINEDU == null) {
            Properties configuracion = PropertiesUtil.cargarProperties("config/rrhh-config.properties");
            int tipoBD = Integer.parseInt(configuracion.getProperty("tipo"));

            String driver = ParametrosUtil.obtenerDriver(tipoBD);
            String url = configuracion.getProperty("url");
            String usuario = configuracion.getProperty("usuario");
            String password = configuracion.getProperty("password");

            Map<String, String> properties = new HashMap<>();
            properties.put("javax.persistence.jdbc.user", usuario);
            properties.put("javax.persistence.jdbc.password", password);
            properties.put("javax.persistence.jdbc.driver", driver);
            properties.put("javax.persistence.jdbc.url", url);

            EntityManagerFactory emf = Persistence.createEntityManagerFactory(this.PU, properties);
            entityManagerMINEDU = emf.createEntityManager();
        }
        return entityManagerMINEDU;
    }
}
