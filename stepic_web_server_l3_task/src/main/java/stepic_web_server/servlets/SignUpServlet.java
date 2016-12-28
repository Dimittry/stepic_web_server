package stepic_web_server.servlets;

import stepic_web_server.accounts.AccountService;
import stepic_web_server.accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {
    private final AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        UserProfile userProfile = accountService.getUserByLogin(login);
        if(userProfile == null) {
            accountService.addNewUser(new UserProfile(login, password, login));
        }
    }
}
