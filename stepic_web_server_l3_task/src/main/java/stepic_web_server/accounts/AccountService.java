package stepic_web_server.accounts;

import stepic_web_server.exceptions.DBException;
import stepic_web_server.services.DBService;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private final DBService dbService;

    public AccountService() {
        dbService = new DBService();
    }

    public void addNewUser(UserProfile userProfile) {
        loginToProfile.put(userProfile.getLogin(), userProfile);
    }

    public long getUserByLogin(String login) {
        long result = -1;
        try {
            result = dbService.getUserId(login);
        } catch (DBException e) {
            e.printStackTrace();
        }
        return result;
    }

    public UserProfile getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
}
