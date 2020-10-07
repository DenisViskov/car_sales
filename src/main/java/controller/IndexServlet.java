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
import java.io.IOException;
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
        super.doGet(req, resp);
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
            JSONObject userJson = new JSONObject();
            Announcement announcement;
        });
        return null;
    }
}
