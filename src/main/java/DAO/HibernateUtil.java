package DAO;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class is an Hibernate util
 *
 * @author Денис Висков
 * @version 1.0
 * @since 05.10.2020
 */
public class HibernateUtil {
    /**
     * Session factory
     */
    private static final SessionFactory sessionFactory;
    /**
     * Logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(HibernateUtil.class);

    static {
        StandardServiceRegistry registry = null;
        try {
            registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
        } catch (Throwable e) {
            StandardServiceRegistryBuilder.destroy(registry);
            LOG.error("Initial SessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * Method return session factory
     *
     * @return SessionFactory
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
