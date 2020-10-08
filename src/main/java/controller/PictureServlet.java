package controller;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Class is a picture servlet
 * Execute sending picture on the client
 *
 * @author Денис Висков
 * @version 1.0
 * @since 07.10.2020
 */
@WebServlet("/picture")
public class PictureServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("picture");
        resp.setContentType("image=" + name);
        resp.setContentType("image/jpg");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + name + "\"");
        File file = new File(System.getenv("CATALINA_HOME")
                + File.separator
                + "bin"
                + File.separator
                + "images"
                + File.separator
                + name);
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
             BufferedOutputStream out = new BufferedOutputStream(resp.getOutputStream())) {
            out.write(in.readAllBytes());
            out.flush();
        }
    }
}
