package DAO;

import model.User;

import java.util.List;

/**
 * Интерфейс реализующий способность
 *
 * @author Денис Висков
 * @version 1.0
 * @since 09.10.2020
 */
public interface UserFilterDao<V> {
    List<V> getUsersWhoPostedLastDay();

    List<V> getUsersWithAnnouncementsHasPhoto();

    List<V> getUsersWithCarDefinedBrand(String brand);
}
