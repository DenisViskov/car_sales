package controller;

import model.User;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

public class SessionFilterTest {

    @Test
    public void whenWeHaveUserDoFilterTest() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        HttpSession session = mock(HttpSession.class);
        when(req.getRequestURI()).thenReturn("/announcement.jsp");
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(new User());
        SessionFilter filter = new SessionFilter();
        filter.doFilter(req, resp, filterChain);
        verify(filterChain).doFilter(req, resp);
    }

    @Test
    public void whenWeDontHaveUserDoFilterTest() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        HttpSession session = mock(HttpSession.class);
        when(req.getRequestURI()).thenReturn("/announcement.jsp");
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(null);
        SessionFilter filter = new SessionFilter();
        filter.doFilter(req, resp, filterChain);
        verify(resp).sendRedirect(anyString());
    }

    @Test
    public void whenWeHaveAnotherURIDoFilterTest() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        when(req.getRequestURI()).thenReturn("/sign.jsp");
        SessionFilter filter = new SessionFilter();
        filter.doFilter(req, resp, filterChain);
        verify(filterChain).doFilter(req, resp);
    }
}