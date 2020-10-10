package controller;

import persistance.UserDaoImpl;
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
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

public class SignServletTest {

    @Test
    public void whenWeHaveUserDoPostTest() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        PrintWriter writer = mock(PrintWriter.class);
        HttpSession session = mock(HttpSession.class);
        ServletContext context = mock(ServletContext.class);
        ServletConfig config = mock(ServletConfig.class);
        UserDaoImpl userDao = mock(UserDaoImpl.class);
        when(resp.getWriter()).thenReturn(writer);
        when(req.getParameter("login")).thenReturn("login");
        when(req.getParameter("password")).thenReturn("password");
        when(config.getServletContext()).thenReturn(context);
        when(context.getAttribute("userDao")).thenReturn(userDao);
        when(userDao.findAll()).thenReturn(List.of(new User(0, "name", "login", "password")));
        when(req.getSession()).thenReturn(session);
        SignServlet signServlet = new SignServlet();
        signServlet.init(config);
        signServlet.doPost(req, resp);
        verify(writer).flush();
    }

    @Test
    public void whenWeDontHaveUserDoPostTest() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        PrintWriter writer = mock(PrintWriter.class);
        HttpSession session = mock(HttpSession.class);
        ServletContext context = mock(ServletContext.class);
        ServletConfig config = mock(ServletConfig.class);
        UserDaoImpl userDao = mock(UserDaoImpl.class);
        when(resp.getWriter()).thenReturn(writer);
        when(req.getParameter("login")).thenReturn("login");
        when(req.getParameter("password")).thenReturn("password");
        when(config.getServletContext()).thenReturn(context);
        when(context.getAttribute("userDao")).thenReturn(userDao);
        when(userDao.findAll()).thenReturn(List.of(new User(0,"name","asdasd","asdasd")));
        when(req.getSession()).thenReturn(session);
        SignServlet signServlet = new SignServlet();
        signServlet.init(config);
        signServlet.doPost(req, resp);
        verify(writer).flush();
    }
}