package model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 05.10.2020
 */
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "model")
    private String model;
    @Temporal(TemporalType.DATE)
    @Column(name = "created")
    private LocalDate created;
    @Column(name = "body")
    private String bodyType;
    @Column(name = "mileage")
    private long mileage;
    @Column(name = "photo")
    private String photo;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Car() {
    }

    public Car(long id, String name, String model, LocalDate created, String bodyType, long mileage, String photo, User user) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.created = created;
        this.bodyType = bodyType;
        this.mileage = mileage;
        this.photo = photo;
        this.user = user;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public long getMileage() {
        return mileage;
    }

    public void setMileage(long mileage) {
        this.mileage = mileage;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id &&
                mileage == car.mileage &&
                Objects.equals(name, car.name) &&
                Objects.equals(model, car.model) &&
                Objects.equals(created, car.created) &&
                Objects.equals(bodyType, car.bodyType) &&
                Objects.equals(photo, car.photo) &&
                Objects.equals(user, car.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, model, created, bodyType, mileage, photo, user);
    }
}
