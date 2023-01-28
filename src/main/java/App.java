import DBservices.Database;
import DBservices.DatabaseQueryService;

import java.sql.SQLException;


public class App {
    public static void main(String[] args) throws SQLException {
        Database database = Database.getINSTANCE();
        System.out.println(new DatabaseQueryService()
                .findMaxProjectsClient(database));
    }
}
