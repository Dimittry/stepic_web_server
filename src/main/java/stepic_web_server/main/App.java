package stepic_web_server.main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import stepic_web_server.servlets.AllRequestsServlet;

public class App
{
    public static void main( String[] args ) throws Exception
    {
        AllRequestsServlet allRequestsServlet = new AllRequestsServlet();

        ServletContextHandler contextHandler
                = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(allRequestsServlet), "/*");

        Server server = new Server(8080);
        server.setHandler(contextHandler);

        server.start();
        server.join();
    }
}
