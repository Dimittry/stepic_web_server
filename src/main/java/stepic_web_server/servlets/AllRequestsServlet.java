package stepic_web_server.servlets;

import stepic_web_server.handlers.HandlerFactory;
import stepic_web_server.handlers.IHandler;
import stepic_web_server.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class AllRequestsServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(AllRequestsServlet.class.getName());
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String result = "NO value";
        try {
            IHandler handler = HandlerFactory.getInstance(request.getPathInfo(), request.getParameterMap());
            result = handler.getResult();
        } catch (IllegalStateException e) {}
        logger.info("RESULT");
        logger.info(result);
//        Map<String, Object> data = handler.getData();

        /*
        Map<String, Object> pageVariables = createPageVariablesMap(request);
        pageVariables.put("message", "");
        response.getWriter().println(buildTemplate("page.html", pageVariables));
        */
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, Object> pageVariables = createPageVariablesMap(request);

        String message = request.getParameter("message");

        response.setContentType("text/html;charset=utf-8");
        if(message == null || message.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
        }
        pageVariables.put("message", message == null ? "" : message);
        response.getWriter().println(buildTemplate("page.html", pageVariables));
    }

    private String buildTemplate(String filename, Map<String, Object> pageVariables) {
        return PageGenerator.instance().getPage(filename, pageVariables);
    }

    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("method", request.getMethod());
        pageVariables.put("URL", request.getRequestURL().toString());
        pageVariables.put("pathInfo", request.getPathInfo());
        pageVariables.put("sessionId", request.getSession().getId());
        pageVariables.put("sessionId", request.getSession().getId());
        pageVariables.put("parameters", request.getParameterMap().toString());
        return pageVariables;
    }
}
