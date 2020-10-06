package DAO;

import model.Announcement;
import model.Car;
import model.User;
import org.hibernate.SessionFactory;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class UserDaoImplTest {

    @Ignore
    public void addTest() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        UserDaoImpl dao = new UserDaoImpl(sessionFactory);
        CarDaoImpl carDao = new CarDaoImpl(sessionFactory);
        AnnouncementDaoImpl announcementDao = new AnnouncementDaoImpl(sessionFactory);
        User user = new User(0, "name", "name", "password");
        Announcement announcement = new Announcement(0,
                "name",
                "desc",
                LocalDateTime.now(),
                "photo",
                false);
        Car car = new Car(0,
                "toyota",
                "corolla",
                LocalDate.now(),
                "sedan",
                1250000);
        user.addCar(car);
        user.addAnnouncement(announcement);
        carDao.add(car);
        announcementDao.add(announcement);
        dao.add(user);
    }
}