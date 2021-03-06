package model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Class is an announcement
 *
 * @author Денис Висков
 * @version 1.0
 * @since 05.10.2020
 */
@Entity
@Table(name = "announcements")
public class Announcement {
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
     * Description
     */
    @Column(name = "description")
    private String description;
    /**
     * Created date
     */
    @Column(name = "created")
    private LocalDateTime created;
    /**
     * Name photo
     */
    @Column(name = "photo")
    private String photo;
    /**
     * Status of announcement
     */
    @Column(name = "status")
    private boolean status;
    /**
     * Car on sale
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private Car car;

    public Announcement() {
    }

    public Announcement(long id, String name, String description, LocalDateTime created, String photo, boolean status, Car car) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
        this.photo = photo;
        this.status = status;
        this.car = car;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Announcement that = (Announcement) o;
        return id == that.id &&
                status == that.status &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(created, that.created) &&
                Objects.equals(photo, that.photo) &&
                Objects.equals(car, that.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, created, photo, status, car);
    }
}
