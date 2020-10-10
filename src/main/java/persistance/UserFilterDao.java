package persistance;

import java.util.List;

/**
 * Interface of user filter
 *
 * @author Денис Висков
 * @version 1.0
 * @since 09.10.2020
 */
public interface UserFilterDao<V> {
    /**
     * Method should return filtered list with users who posted announcements on last day
     *
     * @return List
     */
    List<V> getUsersWhoPostedLastDay();

    /**
     * Method should return filtered list with users announcements who has photo
     *
     * @return List
     */
    List<V> getUsersWithAnnouncementsHasPhoto();

    /**
     * Method should return filtered list with users who has defined car brand
     *
     * @param brand
     * @return List
     */
    List<V> getUsersWithCarDefinedBrand(String brand);
}
