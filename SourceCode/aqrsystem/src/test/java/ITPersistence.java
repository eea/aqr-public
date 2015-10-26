import eu.europa.ec.aqrmodeluser.Country;
import eu.europa.ec.util.PersistenceFactory;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.eclipse.persistence.internal.jpa.EntityManagerImpl;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ITPersistence {

    private static EntityManagerFactory emf;

    /**
     * Executed on build machine. Therefore can't reach the database via persistence.xml,
     * so we have to override the connection string.
     */
    @BeforeClass
    public static void getEntityManager() {
        String dbhost = System.getProperty("db.url");
        HashMap<String, String> props = new HashMap<String, String>();
        System.out.println("Overriding the JDBC Url with: " + dbhost);
        props.put("javax.persistence.jdbc.url", dbhost);

        emf = Persistence.createEntityManagerFactory("Aqrsystem", props);
    }

    @AfterClass
    public static void closeEntityManager() {
        emf.close();
        emf = null;
    }

    @Test
    public void getConnection() throws Exception {
        try {
            EntityManager em = emf.createEntityManager();
            Object o = em.getDelegate();
            Connection connection = ((EntityManagerImpl) (em.getDelegate())).getServerSession().getAccessor().getConnection();
            Properties props = connection.getClientInfo();

            Set<Object> keys = props.keySet();
            for (Object key : keys) {
                System.out.println("DB info:" + key + ":" + props.get(key));
            }
        } catch (Exception e) {
            System.out.println("timed out");
        }
    }

    @Test
    public void getprops() throws Exception {
        try {
            Map<String, Object> props = emf.getProperties();
            Set<String> keys = props.keySet();
            for (String key : keys) {
                System.out.println("Entity manager property: " + key + ":" + props.get(key));
            }
        } catch (Exception e) {
            System.out.println("timed out");
        }
    }

    /**
     * Since the UserManager is not using the overriden EntityManager, it won't
     * be able to connect to the database.
     */
    @Test
    public void getCountries() throws Exception {
        try {
            EntityManager em = emf.createEntityManager();
            Query q = em.createNamedQuery("Country.findAll");
            List<Country> countries = q.getResultList();

            String expected = "Finland";
            boolean expectedFound = false;
            for (Country country : countries) {
                if (expected.equals(country.getCountryname())) {
                    expectedFound = true;
                }
            }
            assertTrue(expectedFound);
        } catch (Exception e) {
            System.out.println("timed out");
        }
    }
}
