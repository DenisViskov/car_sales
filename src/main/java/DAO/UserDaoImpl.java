package DAO;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 05.10.2020
 */
public class UserDaoImpl implements StoreDAO<User> {
    private final SessionFactory sf;
    private static final Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);

    public UserDaoImpl(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public User add(User some) {
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

    @Override
    public boolean update(User some) {
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

    @Override
    public boolean delete(User some) {
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

    @Override
    public List<User> findAll() {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            List result = session.createQuery("from model.User").list();
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

    @Override
    public Optional<User> find(long id) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            User result = session.get(User.class, id);
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
