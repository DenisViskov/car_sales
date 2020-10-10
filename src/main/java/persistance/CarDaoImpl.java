package persistance;

import model.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

/**
 * Class is a car dao implementation
 *
 * @author Денис Висков
 * @version 1.0
 * @since 05.10.2020
 */
public class CarDaoImpl implements StoreDAO<Car> {
    /**
     * Session Factory
     */
    private final SessionFactory sf;
    /**
     * Logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(CarDaoImpl.class);

    public CarDaoImpl(SessionFactory sf) {
        this.sf = sf;
    }

    /**
     * Method add new car to DB
     *
     * @param some
     * @return Car
     */
    @Override
    public Car add(Car some) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(some);
            session.getTransaction().commit();
        } catch (Throwable e) {
            LOG.error(e.getMessage(), e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return some;
    }

    /**
     * Method update car in DB
     *
     * @param some
     * @return boolean
     */
    @Override
    public boolean update(Car some) {
        boolean result = true;
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.update(some);
            session.getTransaction().commit();
        } catch (Throwable e) {
            LOG.error(e.getMessage(), e);
            session.getTransaction().rollback();
            result = false;
        } finally {
            session.close();
        }
        return result;
    }

    /**
     * Method delete car from DB
     *
     * @param some
     * @return boolean
     */
    @Override
    public boolean delete(Car some) {
        boolean result = true;
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.delete(some);
            session.getTransaction().commit();
        } catch (Throwable e) {
            LOG.error(e.getMessage(), e);
            session.getTransaction().rollback();
            result = false;
        } finally {
            session.close();
        }
        return result;
    }

    /**
     * Method return all cars from DB
     *
     * @return List
     */
    @Override
    public List<Car> findAll() {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            List result = session.createQuery("from model.Car").list();
            session.getTransaction().commit();
            return result;
        } catch (Throwable e) {
            LOG.error(e.getMessage(), e);
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    /**
     * Method return car by given ID from DB
     *
     * @param id
     * @return Optional
     */
    @Override
    public Optional<Car> find(long id) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            Car result = session.get(Car.class, id);
            session.getTransaction().commit();
            return Optional.ofNullable(result);
        } catch (Throwable e) {
            LOG.error(e.getMessage(), e);
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
