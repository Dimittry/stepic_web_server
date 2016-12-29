package stepic_web_server.accounts;

import stepic_web_server.dataSets.UsersDataSet;
import stepic_web_server.exceptions.DBException;
import stepic_web_server.services.DBService;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private final DBService dbService;

    public AccountService() {
        dbService = new DBService();
    }

    public void addNewUser(UsersDataSet user) {
        try {
            dbService.addUser(user);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    public UsersDataSet getUserByLogin(String login) {
        UsersDataSet result = null;
        try {
            result = dbService.getUserByName(login);
        } catch (DBException e) {
            e.printStackTrace();
        }
        return result;
    }
/*
    public UserProfile getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
*/
}
