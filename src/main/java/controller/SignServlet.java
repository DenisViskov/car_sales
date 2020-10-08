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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

/**
 * Class is a sign servlet
 *
 * @author Денис Висков
 * @version 1.0
 * @since 06.10.2020
 */
@WebServlet("/sign")
public class SignServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        JSONObject result = new JSONObject();
        Optional<User> user = getUser(login, password);
        user.ifPresentOrElse(value -> {
                    HttpSession session = req.getSession();
                    session.setAttribute("user", value);
                    result.put("0", true);
                    writer.print(result);
                    writer.flush();
                },
                () -> {
                    result.put("0", false);
                    writer.print(result);
                    writer.flush();
                });
    }

    /**
     * Method looking for user by given login and password in DB
     *
     * @param login
     * @param password
     * @return Optional
     */
    private Optional<User> getUser(String login, String password) {
        StoreDAO userDao = (UserDaoImpl) getServletContext().getAttribute("userDao");
        List<User> all = userDao.findAll();
        return all.stream()
                .filter(user -> user.getLogin().equals(login)
                        && user.getPassword().equals(password))
                .findFirst();
    }
}
