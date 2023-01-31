import DBservices.Database;
import DBservices.DatabaseQueryService;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;


public class App {
    public static void main(String[] args) throws SQLException {
        Database database = Database.getINSTANCE();
        DatabaseQueryService databaseQueryService = new DatabaseQueryService();

        List maxProjectCountClient = databaseQueryService.
                findMaxProjectsClient(database);
        System.out.println(maxProjectCountClient.toString());

        List longestProject = databaseQueryService
                .findLongestProject(database);
        System.out.println(longestProject.toString());

        List maxSalaryWorkers = databaseQueryService
                .findMaxSalaryWorker(database);
        System.out.println(maxSalaryWorkers.toString());

        List youngestEldestWorker = databaseQueryService
                .findYoungestEldestWorkers(database);
        System.out.println(youngestEldestWorker.toString());

        List projectPrices = databaseQueryService
                .printProjectPrices(database);
        System.out.println(projectPrices.toString());

        database.connection.close();
    }
}
