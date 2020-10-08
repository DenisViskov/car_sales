package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Class is an User
 *
 * @author Денис Висков
 * @version 1.0
 * @since 05.10.2020
 */
@Entity
@Table(name = "owners")
public class User {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    /**
     * Name
     */
    @Column(name = "name")
    private String name;
    /**
     * Login
     */
    @Column(name = "login")
    private String login;
    /**
     * Password
     */
    @Column(name = "password")
    private String password;
    /**
     * Cars
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Car> cars = new HashSet<>();
    /**
     * Announcements
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Announcement> announcements = new HashSet<>();

    public User() {
    }

    public User(long id, String name, String login, String password) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
    }

    /**
     * Method add car to cars set
     *
     * @param car
     */
    public void addCar(Car car) {
        cars.add(car);
    }

    /**
     * Method add announcement to announcements set
     *
     * @param announcement
     */
    public void addAnnouncement(Announcement announcement) {
        announcements.add(announcement);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public Set<Announcement> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(Set<Announcement> announcements) {
        this.announcements = announcements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(name, user.name) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(cars, user.cars) &&
                Objects.equals(announcements, user.announcements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, login, password, cars, announcements);
    }
}
