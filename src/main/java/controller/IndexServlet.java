package controller;

import DAO.AnnouncementDaoImpl;
import DAO.CarDaoImpl;
import DAO.StoreDAO;
import DAO.UserDaoImpl;
import model.Announcement;
import model.Car;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 07.10.2020
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        String getRequest = req.getParameter("GET");
        if (getRequest.equals("Get announcements")) {
            writer.print(collectJSON());
            writer.flush();
        } else {
            writer.print(getSessionAnnouncements(req.getSession()));
            writer.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("announcement");
        User user = (User) req.getSession().getAttribute("user");
        StoreDAO userDao = (UserDaoImpl) getServletContext().getAttribute("userDao");
        StoreDAO carDao = (CarDaoImpl) getServletContext().getAttribute("carDao");
        StoreDAO announcementDao = (AnnouncementDaoImpl) getServletContext().getAttribute("announcementDao");
        Announcement announcement = user.getAnnouncements().stream()
                .filter(announce -> announce.getName().equals(name))
                .findFirst()
                .get();
        user.getAnnouncements().remove(announcement);
        user.getCars().remove(announcement.getCar());
        userDao.update(user);
        announcementDao.delete(announcement);
    }

    private JSONArray collectJSON() {
        StoreDAO userDao = (UserDaoImpl) getServletContext().getAttribute("userDao");
        JSONArray json = new JSONArray();
        List<User> all = userDao.findAll();
        all.forEach(user -> {
            user.getAnnouncements().forEach(announcement -> {
                JSONObject announcementJson = new JSONObject();
                announcementJson.put("Announcement", announcement.getName());
                announcementJson.put("Car brand", announcement.getCar().getName());
                announcementJson.put("Car model", announcement.getCar().getModel());
                announcementJson.put("Car created", announcement.getCar().getCreated());
                announcementJson.put("Car body type", announcement.getCar().getBodyType());
                announcementJson.put("Car mileage", announcement.getCar().getMileage());
                announcementJson.put("description", announcement.getDescription());
                announcementJson.put("photo", announcement.getPhoto());
                announcementJson.put("status", announcement.isStatus());
                announcementJson.put("owner", user.getName());
                json.put(announcementJson);
            });
        });
        return json;
    }

    private JSONObject getSessionAnnouncements(HttpSession session) {
        Optional<User> box = Optional.ofNullable((User) session.getAttribute("user"));
        JSONObject json = new JSONObject();
        box.ifPresent(user -> user.getAnnouncements()
                .forEach(announcement -> json.put(announcement.getName(), announcement.getName())));
        return json;
    }
}
