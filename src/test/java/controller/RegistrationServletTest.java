package controller;

import DAO.UserDaoImpl;
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

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

public class RegistrationServletTest {

    @Test
    public void whenRegisteredNewUserTest() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        PrintWriter writer = mock(PrintWriter.class);
        ServletContext context = mock(ServletContext.class);
        ServletConfig config = mock(ServletConfig.class);
        UserDaoImpl userDao = mock(UserDaoImpl.class);
        when(req.getParameter("name")).thenReturn("name");
        when(req.getParameter("login")).thenReturn("login");
        when(req.getParameter("password")).thenReturn("password");
        when(config.getServletContext()).thenReturn(context);
        when(context.getAttribute("userDao")).thenReturn(userDao);
        when(userDao.findAll()).thenReturn(List.of(new User(0, "name", "nelogin", "nepassword")));
        when(resp.getWriter()).thenReturn(writer);
        RegistrationServlet servlet = new RegistrationServlet();
        servlet.init(config);
        servlet.doPost(req, resp);
        verify(writer).flush();
    }

    @Test
    public void whenTheSameUserAlreadyExistTest() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        PrintWriter writer = mock(PrintWriter.class);
        ServletContext context = mock(ServletContext.class);
        ServletConfig config = mock(ServletConfig.class);
        UserDaoImpl userDao = mock(UserDaoImpl.class);
        when(req.getParameter("name")).thenReturn("name");
        when(req.getParameter("login")).thenReturn("login");
        when(req.getParameter("password")).thenReturn("password");
        when(config.getServletContext()).thenReturn(context);
        when(context.getAttribute("userDao")).thenReturn(userDao);
        when(userDao.findAll()).thenReturn(List.of(new User(0, "name", "login", "nepassword")));
        when(resp.getWriter()).thenReturn(writer);
        RegistrationServlet servlet = new RegistrationServlet();
        servlet.init(config);
        servlet.doPost(req, resp);
        verify(writer).flush();
    }
}