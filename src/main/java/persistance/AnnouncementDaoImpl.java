package persistance;

import model.Announcement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

/**
 * Class is an announcement dao implementation
 *
 * @author Денис Висков
 * @version 1.0
 * @since 05.10.2020
 */
public class AnnouncementDaoImpl implements StoreDAO<Announcement> {
    /**
     * Session Factory
     */
    private final SessionFactory sf;
    /**
     * Logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(AnnouncementDaoImpl.class);

    public AnnouncementDaoImpl(SessionFactory sf) {
        this.sf = sf;
    }

    /**
     * Method add new announcement to DB
     *
     * @param some
     * @return Announcement
     */
    @Override
    public Announcement add(Announcement some) {
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
     * Method update announcement in DB
     *
     * @param some
     * @return boolean
     */
    @Override
    public boolean update(Announcement some) {
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
     * Method delete announcement from DB
     *
     * @param some
     * @return boolean
     */
    @Override
    public boolean delete(Announcement some) {
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
     * Method return all announcement from DB
     *
     * @return List
     */
    @Override
    public List<Announcement> findAll() {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            List result = session.createQuery("from model.Announcement").list();
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
     * Method return announcement by given ID from DB
     *
     * @param id
     * @return Optional
     */
    @Override
    public Optional<Announcement> find(long id) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            Announcement result = session.get(Announcement.class, id);
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
