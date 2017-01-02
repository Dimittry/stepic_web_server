package stepic_web_server.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import stepic_web_server.accountServer.AccountServer;
import stepic_web_server.accountServer.AccountServerController;
import stepic_web_server.accountServer.AccountServerControllerMBean;
import stepic_web_server.accountServer.IAccountServer;
import stepic_web_server.servlets.AdminPageServlet;
import stepic_web_server.servlets.HomePageServlet;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) throws Exception{
        if(args.length != 1) {
            logger.error("Use port as the first ");
            System.exit(1);
        }

        String portString = args[0];
        int port = Integer.valueOf(portString);

        logger.info("Starting at http://127.0.0.1:" + port);

        IAccountServer accountServer = new AccountServer(10);
        AccountServerControllerMBean serverStatistics = new AccountServerController(accountServer);
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("ServerManager:type=AccountServerController");
        mbs.registerMBean(serverStatistics, name);

        ObjectName name1 = new ObjectName("Admin:type=AccountServerController.usersLimit");
        mbs.registerMBean(serverStatistics, name1);

        Server server = new Server(port);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new HomePageServlet(accountServer)), HomePageServlet.PAGE_URL);
        context.addServlet(new ServletHolder(new AdminPageServlet(accountServer)), AdminPageServlet.PAGE_URL);

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setResourceBase("stepic_web_server_l5_task/public_html");

        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(new Handler[]{resourceHandler, context});
        server.setHandler(handlerList);

        server.start();
        logger.info("Server started");
        server.join();

    }
}
