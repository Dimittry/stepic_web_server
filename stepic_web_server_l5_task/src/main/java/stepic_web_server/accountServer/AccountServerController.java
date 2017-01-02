package stepic_web_server.accountServer;

public class AccountServerController implements AccountServerControllerMBean {
    private IAccountServer accountServer;

    public AccountServerController(final IAccountServer accountServer) {
        this.accountServer = accountServer;
    }

    @Override
    public int getUsers() {
        return accountServer.getUsersCount();
    }

    @Override
    public int getUsersLimit() {
        return accountServer.getUsersLimit();
    }

    @Override
    public void setUsersLimit(final int usersLimit) {
        accountServer.setUsersLimit(usersLimit);
    }
}
