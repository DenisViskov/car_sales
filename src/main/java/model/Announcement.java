package model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 05.10.2020
 */
@Entity
@Table(name = "announcements")
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime created;
    @Column(name = "status")
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    public Announcement() {
    }

    public Announcement(long id, String name, String description, LocalDateTime created, boolean status, User owner) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
        this.status = status;
        this.owner = owner;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
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
                Objects.equals(owner, that.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, created, status, owner);
    }
}