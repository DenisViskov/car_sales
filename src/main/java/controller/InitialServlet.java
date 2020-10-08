package controller;

import DAO.*;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Class is an initial servlet
 * Execute initialization servlet context and given him DAO storage
 *
 * @author Денис Висков
 * @version 1.0
 * @since 06.10.2020
 */
@WebServlet(name = "InitialServlet", urlPatterns = "/hidden", loadOnStartup = 0)
public class InitialServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        final SessionFactory sf = HibernateUtil.getSessionFactory();
        final StoreDAO userDao = new UserDaoImpl(sf);
        final StoreDAO carDao = new CarDaoImpl(sf);
        final StoreDAO announcementDao = new AnnouncementDaoImpl(sf);
        getServletContext().setAttribute("userDao", userDao);
        getServletContext().setAttribute("carDao", carDao);
        getServletContext().setAttribute("announcementDao", announcementDao);
    }
}
