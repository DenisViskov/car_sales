package controller;

import DAO.StoreDAO;
import DAO.UserDaoImpl;
import model.Announcement;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
        writer.print(collectJSON());
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
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
                announcementJson.put("photo", System.getProperty("catalina.base")
                        + File.separator
                        + "images"
                        + File.separator
                        + announcement.getPhoto());
                announcementJson.put("status", announcement.isStatus());
                announcementJson.put("owner", user.getName());
                json.put(announcementJson);
            });
        });
        return json;
    }
}
