import db_services.ClientService;


public class App {
    public static void main(String[] args) {
        ClientService clientService = new ClientService();
        System.out.println(clientService.create("Robert Martin"));
        System.out.println(clientService.getById(208));
        clientService.setName(208, "Rob");
        System.out.println(clientService.getById(208));
        clientService.deleteById(168);
        System.out.println(clientService.listAll().toString());



      /*  DatabaseQueryService databaseQueryService = new DatabaseQueryService();

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
       */
    }
}
