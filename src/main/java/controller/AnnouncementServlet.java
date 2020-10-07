package controller;

import DAO.AnnouncementDaoImpl;
import DAO.CarDaoImpl;
import DAO.StoreDAO;
import DAO.UserDaoImpl;
import model.Announcement;
import model.Car;
import model.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.OperationsException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 06.10.2020
 */
@WebServlet("/announcement")
public class AnnouncementServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(AnnouncementServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> out = parseRequest(req);
        User user = (User) req.getSession().getAttribute("user");
        StoreDAO userDAO = (UserDaoImpl) getServletContext().getAttribute("userDao");
        StoreDAO carDAO = (CarDaoImpl) getServletContext().getAttribute("carDao");
        StoreDAO announcementDAO = (AnnouncementDaoImpl) getServletContext().getAttribute("announcementDao");
        if (!out.isEmpty()) {
            Announcement announcement = initAnnouncement(out);
            Car car = initCar(out);
            announcementDAO.add(announcement);
            carDAO.add(car);
            user.addAnnouncement(announcement);
            user.addCar(car);
            userDAO.update(user);
        }

    }

    private Map<String, String> parseRequest(HttpServletRequest req) {
        Map<String, String> result = new HashMap<>();
        try {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
            items.forEach(item -> {
                if (item.isFormField()) {
                    result.put(item.getFieldName(), item.getString());
                } else {
                    String fileName = FilenameUtils.getName(item.getName());
                    try {
                        saveFile(item.getInputStream(), fileName);
                    } catch (IOException e) {
                        LOG.error(e.getMessage(), e);
                        e.printStackTrace();
                        result.clear();
                    }
                    result.put("file", fileName);
                }
            });
        } catch (FileUploadException e) {
            result.clear();
            e.printStackTrace();
        }
        return result;
    }

    private void saveFile(InputStream inputStream, String fileName) throws IOException {
        File folder = new File(System.getProperty("catalina.base")
                + File.separator
                + "images");
        if (!folder.exists()) {
            folder.mkdir();
            folder.createNewFile();
        }
        File file = new File(folder + File.separator + fileName);
        file.createNewFile();
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
            out.write(inputStream.readAllBytes());
        } catch (IOException e) {
            LOG.error("File not saved", e);
            e.printStackTrace();
        }
    }

    private Announcement initAnnouncement(Map<String, String> out) {
        Announcement announcement = new Announcement();
        announcement.setId(0);
        announcement.setCreated(LocalDateTime.now());
        announcement.setStatus(false);
        out.forEach((key, value) -> {
            if (key.equals("announcementName")) {
                announcement.setName(value);
            }
            if (key.equals("comment")) {
                announcement.setDescription(value);
            }
            if (key.equals("file")) {
                announcement.setPhoto(value);
            }
        });
        return announcement;
    }

    private Car initCar(Map<String, String> out) {
        Car car = new Car();
        car.setId(0);
        out.forEach((key, value) -> {
            if (key.equals("carBrand")) {
                car.setName(value);
            }
            if (key.equals("carModel")) {
                car.setModel(value);
            }
            if (key.equals("carBody")) {
                car.setBodyType(value);
            }
            if (key.equals("carMileage")) {
                car.setMileage(Long.parseLong(value));
            }
            if (key.equals("carCreated")) {
                car.setCreated(LocalDate.parse(value));
            }
        });
        return car;
    }
}
