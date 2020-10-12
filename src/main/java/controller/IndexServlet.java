package controller;

import persistance.*;
import model.Announcement;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

/**
 * Class is an index servlet
 *
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
        StoreDAO userDao = (UserDaoImpl) getServletContext().getAttribute("userDao");
        UserFilterDao filterDao = (UserFilterDao) userDao;
        String getRequest = req.getParameter("GET");
        if (getRequest.equals(Key.GET_announcements.name())) {
            writer.print(collectJSON(userDao.findAll()));
            writer.flush();
        }
        if (getRequest.equals(Key.GET_session_announcements.name())) {
            writer.print(getSessionAnnouncements(req.getSession()));
            writer.flush();
        }
        if (getRequest.equals(Key.GET_only_with_photo.name())) {
            writer.print(collectJSON(filterDao.getUsersWithAnnouncementsHasPhoto()));
            writer.flush();
        }
        if (getRequest.equals(Key.GET_only_the_last_day.name())) {
            writer.print(collectJSON(filterDao.getUsersWhoPostedLastDay()));
            writer.flush();
        }
        if (getRequest.equals(Key.GET_cars_by_brand.name())) {
            String carName = req.getParameter("carName");
            writer.print(collectJSON(filterDao.getUsersWithCarDefinedBrand(carName)));
            writer.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("announcement");
        User user = (User) req.getSession().getAttribute("user");
        StoreDAO userDao = (UserDaoImpl) getServletContext().getAttribute("userDao");
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

    /**
     * Method return ready json for send to client on the GET request
     *
     * @param target list
     * @return JSONArray
     */
    private JSONArray collectJSON(List<User> target) {
        JSONArray json = new JSONArray();
        target.forEach(user -> {
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

    /**
     * Method return ready json for send to client on the GET request
     *
     * @param session
     * @return JSONObject
     */
    private JSONObject getSessionAnnouncements(HttpSession session) {
        Optional<User> box = Optional.ofNullable((User) session.getAttribute("user"));
        JSONObject json = new JSONObject();
        box.ifPresent(user -> user.getAnnouncements()
                .forEach(announcement -> json.put(announcement.getName(), announcement.getName())));
        return json;
    }

    /**
     * Enum keys request
     */
    private enum Key {
        GET_announcements,
        GET_session_announcements,
        GET_only_with_photo,
        GET_only_the_last_day,
        GET_cars_by_brand
    }
}
