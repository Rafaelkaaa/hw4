package DBservices;

import Model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {

    public ResultSet resultOfSQLRequest(Database database, String path){
        String sql = null;
        try {
            sql = String.join("\n", Files.readAllLines(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return database.statementQuery(sql);
    }

    public List<MaxProjectCountClient> findMaxProjectsClient(Database database) throws SQLException {
        ResultSet resultSet = resultOfSQLRequest
                (database, "src/main/resources/SQL/find_max_projects_client.sql");
        List<MaxProjectCountClient> MPCCList  = new ArrayList();
        while (resultSet.next()){
            MaxProjectCountClient MPCC = new MaxProjectCountClient();
            MPCC.setName(resultSet.getString("NAME"));
            MPCC.setProjectCount(resultSet.getInt("COUNT(CLIENT_ID)"));
            MPCCList.add(MPCC);
        }
        return MPCCList;
    }

    public List<LongestProject> findLongestProject (Database database) throws SQLException {
        ResultSet resultSet = resultOfSQLRequest
                (database, "src/main/resources/SQL/find_longest_project.sql");
        List<LongestProject> LPList  = new ArrayList();
        while (resultSet.next()){
            LongestProject LP = new LongestProject();
            LP.setName(resultSet.getString("NAME"));
            LP.setCountMonth(resultSet.getInt("MONTH_COUNT"));
            LPList.add(LP);
        }
        return LPList;
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker (Database database) throws SQLException {
        ResultSet resultSet = resultOfSQLRequest
                (database, "src/main/resources/SQL/find_max_salary_worker.sql");
        List<MaxSalaryWorker> MSWList  = new ArrayList();
        while (resultSet.next()){
            MaxSalaryWorker MSW = new MaxSalaryWorker();
            MSW.setName(resultSet.getString("NAME"));
            MSW.setSalary(resultSet.getInt("SALARY"));
            MSWList.add(MSW);
        }
        return MSWList;
    }

    public List<YoungestEldestWorker> findYoungestEldestWorkers (Database database) throws SQLException {
        ResultSet resultSet = resultOfSQLRequest
                (database, "src/main/resources/SQL/find_youngest_eldest_workers.sql");
        List<YoungestEldestWorker> YEWList  = new ArrayList();
        while (resultSet.next()){
            YoungestEldestWorker YEW = new YoungestEldestWorker();
            YEW.setType(resultSet.getString("TYPE"));
            YEW.setName(resultSet.getString("NAME"));
            YEW.setBirthday(resultSet.getString("BIRTHDAY"));
            YEWList.add(YEW);
        }
        return YEWList;
    }

    public List<ProjectPrice> printProjectPrices (Database database) throws SQLException {
        ResultSet resultSet = resultOfSQLRequest
                (database, "src/main/resources/SQL/print_project_prices.sql");
        List<ProjectPrice> PPList  = new ArrayList();
        while (resultSet.next()){
            ProjectPrice PP = new ProjectPrice();
            PP.setName(resultSet.getString("NAME"));
            PP.setPrise(resultSet.getInt("PRICE"));
            PPList.add(PP);
        }
        return PPList;
    }



}
