package controller;

import DAO.StoreDAO;
import DAO.UserDaoImpl;
import model.User;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 06.10.2020
 */
@WebServlet(urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        JSONObject result = new JSONObject();
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (check(login)) {
            result.put("0", false);
            writer.print(result);
            writer.flush();
            return;
        }
        register(name, login, password);
        result.put("0", true);
        writer.print(result);
        writer.flush();
    }

    private boolean check(String login) {
        StoreDAO userDao = (UserDaoImpl) getServletContext().getAttribute("userDao");
        List<User> all = userDao.findAll();
        return all.stream()
                .anyMatch(user -> user.getLogin().equals(login));
    }

    private void register(String name, String login, String password) {
        StoreDAO userDao = (UserDaoImpl) getServletContext().getAttribute("userDao");
        userDao.add(new User(0, name, login, password));
    }
}
