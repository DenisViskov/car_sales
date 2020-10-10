package persistance;

import java.util.List;
import java.util.Optional;

/**
 * Interface of Store DAO
 *
 * @author Денис Висков
 * @version 1.0
 * @since 05.10.2020
 */
public interface StoreDAO<V> {
    /**
     * Method should add some object to Store
     *
     * @param some
     * @return V
     */
    V add(V some);

    /**
     * Method should update given object in DB
     *
     * @param some
     * @return boolean
     */
    boolean update(V some);

    /**
     * Method should delete some object from Store
     *
     * @param some
     * @return boolean
     */
    boolean delete(V some);

    /**
     * Method should return all objects from store
     *
     * @return List
     */
    List<V> findAll();

    /**
     * Method should return some object from DB by given id
     *
     * @param id
     * @return Optional
     */
    Optional<V> find(long id);
}
