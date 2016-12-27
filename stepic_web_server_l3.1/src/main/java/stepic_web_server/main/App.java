package stepic_web_server.main;

import stepic_web_server.dataSets.UsersDataSet;
import stepic_web_server.exceptions.DBException;
import stepic_web_server.services.DBService;

public class App
{
    public static void main( String[] args ) throws Exception
    {
        DBService dbService = new DBService();
        dbService.printConnectionInfo();

        try {
            long userId = dbService.addUser("test");
            System.out.println("Added user id: " + userId);

            UsersDataSet dataSet = dbService.getUser(userId);
            System.out.println("User data set: " + dataSet);

            dbService.cleanUp();
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
