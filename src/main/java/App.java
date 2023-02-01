import bd_services.Database;
import bd_services.DatabaseQueryService;

import java.sql.SQLException;
import java.util.List;


public class App {
    public static void main(String[] args) throws SQLException {
        Database database = Database.getINSTANCE();
        DatabaseQueryService databaseQueryService = new DatabaseQueryService();

        List maxProjectCountClient = databaseQueryService.
                findMaxProjectsClient(database);
        System.out.println("Max Project Count Client" + maxProjectCountClient.toString());

        List longestProject = databaseQueryService
                .findLongestProject(database);
        System.out.println("Longest Project" + longestProject.toString());

        List maxSalaryWorkers = databaseQueryService
                .findMaxSalaryWorker(database);
        System.out.println("Max Salary Worker" + maxSalaryWorkers.toString());

        List youngestEldestWorker = databaseQueryService
                .findYoungestEldestWorkers(database);
        System.out.println("Youngest and Eldest Workers" + youngestEldestWorker.toString());

        List projectPrices = databaseQueryService
                .printProjectPrices(database);
        System.out.println("Projects Prices" + projectPrices.toString());

        database.connection.close();
    }
}
