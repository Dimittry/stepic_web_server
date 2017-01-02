package stepic_web_server.accountServer;

public interface AccountServerControllerMBean {
    int getUsers();

    int getUsersLimit();

    void setUsersLimit(int usersLimit);
}
