package servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.ResourceService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResourcePageServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    public static final String PAGE_URL = "/resources";

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getParameter("path");
        logger.info("Path parameter :" + path);

        if(path == null) return;

        try {
            ResourceService resourceService = new ResourceService(path);
            resourceService.configureJMX();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
