package controller;

import persistance.AnnouncementDaoImpl;
import persistance.CarDaoImpl;
import persistance.UserDaoImpl;
import model.Announcement;
import model.Car;
import model.User;
import org.junit.Test;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

public class IndexServletTest {

    @Test
    public void whenDoGetGetAnnouncementsTest() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        PrintWriter writer = mock(PrintWriter.class);
        ServletContext context = mock(ServletContext.class);
        ServletConfig config = mock(ServletConfig.class);
        UserDaoImpl userDao = mock(UserDaoImpl.class);
        User user = new User(0, "name", "login", "password");
        Car car = new Car();
        user.addCar(car);
        user.addAnnouncement(new Announcement(0,
                "name",
                "desc",
                LocalDateTime.now(),
                "photo",
                false,
                car));
        when(req.getParameter("GET")).thenReturn("Get announcements");
        when(config.getServletContext()).thenReturn(context);
        when(context.getAttribute("userDao")).thenReturn(userDao);
        when(userDao.findAll()).thenReturn(List.of(user));
        when(resp.getWriter()).thenReturn(writer);
        IndexServlet servlet = new IndexServlet();
        servlet.init(config);
        servlet.doGet(req,resp);
        verify(writer).flush();
    }

    @Test
    public void whenDoGetSessionsReqTest() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        PrintWriter writer = mock(PrintWriter.class);
        ServletContext context = mock(ServletContext.class);
        ServletConfig config = mock(ServletConfig.class);
        HttpSession session = mock(HttpSession.class);
        User user = new User(0, "name", "login", "password");
        Car car = new Car();
        user.addCar(car);
        user.addAnnouncement(new Announcement(0,
                "name",
                "desc",
                LocalDateTime.now(),
                "photo",
                false,
                car));
        when(req.getParameter("GET")).thenReturn("Get session announcements");
        when(config.getServletContext()).thenReturn(context);
        when(resp.getWriter()).thenReturn(writer);
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        IndexServlet servlet = new IndexServlet();
        servlet.init(config);
        servlet.doGet(req,resp);
        verify(writer).flush();
    }

    @Test
    public void whenDoGetGetOnlyWithPhotoReqTest() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        PrintWriter writer = mock(PrintWriter.class);
        ServletContext context = mock(ServletContext.class);
        ServletConfig config = mock(ServletConfig.class);
        HttpSession session = mock(HttpSession.class);
        User user = new User(0, "name", "login", "password");
        Car car = new Car();
        user.addCar(car);
        user.addAnnouncement(new Announcement(0,
                "name",
                "desc",
                LocalDateTime.now(),
                "photo",
                false,
                car));
        when(req.getParameter("GET")).thenReturn("Get only with photo");
        when(config.getServletContext()).thenReturn(context);
        when(resp.getWriter()).thenReturn(writer);
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        IndexServlet servlet = new IndexServlet();
        servlet.init(config);
        servlet.doGet(req,resp);
        verify(writer).flush();
    }

    @Test
    public void doPostTest() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ServletContext context = mock(ServletContext.class);
        ServletConfig config = mock(ServletConfig.class);
        UserDaoImpl userDao = mock(UserDaoImpl.class);
        CarDaoImpl carDao = mock(CarDaoImpl.class);
        AnnouncementDaoImpl announcementDao = mock(AnnouncementDaoImpl.class);
        when(req.getParameter("announcement")).thenReturn("name");
        when(req.getSession()).thenReturn(session);
        when(config.getServletContext()).thenReturn(context);
        when(context.getAttribute("userDao")).thenReturn(userDao);
        when(context.getAttribute("carDao")).thenReturn(carDao);
        when(context.getAttribute("announcementDao")).thenReturn(announcementDao);
        User user = new User(0, "name", "login", "password");
        Car car = new Car();
        user.addCar(car);
        user.addAnnouncement(new Announcement(0,
                "name",
                "desc",
                LocalDateTime.now(),
                "photo",
                false,
                car));
        when(session.getAttribute("user")).thenReturn(user);
        IndexServlet servlet = new IndexServlet();
        servlet.init(config);
        servlet.doPost(req,resp);
        verify(userDao).update(user);
        verify(announcementDao).delete(any());
    }
}