import bd_services.Database;
import bd_services.DatabaseQueryService;

import java.sql.SQLException;
import java.util.List;


public class App {
    public static void main(String[] args) throws SQLException {

        DatabaseQueryService databaseQueryService = new DatabaseQueryService();

        List maxProjectCountClient = databaseQueryService.
                findMaxProjectsClient();
        System.out.println("Max Project Count Client" + maxProjectCountClient.toString());

        List longestProject = databaseQueryService
                .findLongestProject();
        System.out.println("Longest Project" + longestProject.toString());

        List maxSalaryWorkers = databaseQueryService
                .findMaxSalaryWorker();
        System.out.println("Max Salary Worker" + maxSalaryWorkers.toString());

        List youngestEldestWorker = databaseQueryService
                .findYoungestEldestWorkers();
        System.out.println("Youngest and Eldest Workers" + youngestEldestWorker.toString());

        List projectPrices = databaseQueryService
                .printProjectPrices();
        System.out.println("Projects Prices" + projectPrices.toString());
    }
}
