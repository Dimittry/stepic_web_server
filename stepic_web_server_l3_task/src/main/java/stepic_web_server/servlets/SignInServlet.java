package stepic_web_server.servlets;


import stepic_web_server.accounts.AccountService;
import stepic_web_server.accounts.UserProfile;
import stepic_web_server.dataSets.UsersDataSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    private final AccountService accountService;

    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        UsersDataSet userDataSet = accountService.getUserByLogin(login);
        System.out.println(userDataSet);
        System.out.println("login = " + login + " password = " + password);
        if(userDataSet == null
                || !validateUser(userDataSet, new UsersDataSet(login, password))) {
            sendResponse(resp, "Unauthorized", HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            sendResponse(resp, "Authorized: " + userDataSet.getName(), HttpServletResponse.SC_OK);
        }
    }

    private boolean validateUser(UsersDataSet existingUser, UsersDataSet newUser) {
        return existingUser.equals(newUser);
    }

    private void sendResponse(HttpServletResponse resp, String message, int statusCode)
            throws IOException{
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println(message);
        resp.setStatus(statusCode);
    }
}

