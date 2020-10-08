package controller;

import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 08.10.2020
 */
@WebFilter(urlPatterns = "*")
public class SessionFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(SessionFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String uri = req.getRequestURI();
        if (uri.endsWith("announcement.jsp")) {
            Optional<User> box = Optional.ofNullable((User) req.getSession().getAttribute("user"));
            box.ifPresentOrElse(user -> {
                try {
                    filterChain.doFilter(req, resp);
                    return;
                } catch (IOException | ServletException e) {
                    LOG.error(e.getMessage(), e);
                    e.printStackTrace();
                }
            }, () -> {
                try {
                    resp.sendRedirect(req.getContextPath() + "/sign.jsp");
                    return;
                } catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                    e.printStackTrace();
                }
            });
        }
        filterChain.doFilter(req, resp);
    }
}
