package persistance;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Class is an UserDao implementation
 *
 * @author Денис Висков
 * @version 1.0
 * @since 05.10.2020
 */
public class UserDaoImpl implements StoreDAO<User>, UserFilterDao<User> {
    /**
     * Session factory
     */
    private final SessionFactory sf;
    /**
     * Logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);

    public UserDaoImpl(SessionFactory sf) {
        this.sf = sf;
    }

    /**
     * Method add new user to DB
     *
     * @param some
     * @return User
     */
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

    /**
     * Method updates user in DB
     *
     * @param some
     * @return boolean
     */
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

    /**
     * Method delete user from DB
     *
     * @param some
     * @return boolean
     */
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

    /**
     * Method return all users from DB
     *
     * @return List
     */
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

    /**
     * Method looking for user in DB by given id
     *
     * @param id
     * @return Optional
     */
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

    /**
     * Method return filtered List by last day announcements
     *
     * @return List
     */
    @Override
    public List<User> getUsersWhoPostedLastDay() {
        LocalDateTime oneDayAgo = LocalDateTime.of(LocalDateTime.now().getYear(),
                LocalDateTime.now().getMonth(),
                LocalDateTime.now().getDayOfMonth() - 1,
                LocalDateTime.now().getHour(),
                LocalDateTime.now().getMinute());
        return find(session -> session.createQuery("select distinct u from User u " +
                "join fetch u.announcements a " +
                "where a.created <= :now and a.created >= :oneDayAgo ")
                .setParameter("now", LocalDateTime.now())
                .setParameter("oneDayAgo", oneDayAgo)
                .getResultList()
        );
    }

    /**
     * Method return filtered list with announcements who has photo
     *
     * @return List
     */
    @Override
    public List<User> getUsersWithAnnouncementsHasPhoto() {
        return find(session -> session.createQuery("select distinct u from User u " +
                "join fetch u.announcements a " +
                "where a.photo != null ")
                .getResultList()
        );
    }

    /**
     * Method return filtered list with defined car brand
     *
     * @param brand
     * @return List
     */
    @Override
    public List<User> getUsersWithCarDefinedBrand(String brand) {
        return find(session -> session.createQuery("select distinct u from User u " +
                "join fetch u.announcements a " +
                "join fetch a.car c " +
                "where c.name = :brand")
                .setParameter("brand", brand)
                .getResultList()
        );
    }

    /**
     * Method execute abstract search by given command in session
     *
     * @param command
     * @param <V>
     * @return V
     */
    private <V> V find(Function<Session, V> command) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            V result = command.apply(session);
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            e.printStackTrace();
            throw e;
        } finally {
            session.close();
        }
    }
}
