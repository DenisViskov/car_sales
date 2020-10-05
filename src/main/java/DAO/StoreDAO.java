package DAO;

import java.util.List;

/**
 * Интерфейс реализующий способность
 *
 * @author Денис Висков
 * @version 1.0
 * @since 05.10.2020
 */
public interface StoreDAO<V> {
    V add(V some);
    boolean update(V some);
    boolean delete(V some);
    List<V> findAll();
    V find(long id);
}
