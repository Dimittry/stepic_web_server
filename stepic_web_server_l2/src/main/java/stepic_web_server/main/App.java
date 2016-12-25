package stepic_web_server.main;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import stepic_web_server.accounts.AccountService;
import stepic_web_server.accounts.UserProfile;
import stepic_web_server.servlets.SessionsServlet;
import stepic_web_server.servlets.SignInServlet;
import stepic_web_server.servlets.SignUpServlet;
import stepic_web_server.servlets.UsersServlet;

public class App
{
    public static void main( String[] args ) throws Exception
    {
        AccountService accountService = new AccountService();

        accountService.addNewUser(new UserProfile("admin"));
        accountService.addNewUser(new UserProfile("test"));

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new UsersServlet(accountService)), "/api/v1/users");
        context.addServlet(new ServletHolder(new SessionsServlet(accountService)), "/api/v1/sessions");
        context.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");
        context.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("stepic_web_server_l2/public_html");

        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(new Handler[]{resourceHandler, context});

        Server server = new Server(8080);
        server.setHandler(handlerList);

        server.start();
        System.err.println("Server started");
        server.join();
    }
}
