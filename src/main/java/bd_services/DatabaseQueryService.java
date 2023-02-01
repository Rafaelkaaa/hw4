package bd_services;

import model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {

    public ResultSet resultOfSQLRequest(Database database, String path) {
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
        List<MaxProjectCountClient> mpccList = new ArrayList();
        while (resultSet.next()) {
            MaxProjectCountClient mpcc = new MaxProjectCountClient();
            mpcc.setName(resultSet.getString("NAME"));
            mpcc.setProjectCount(resultSet.getInt("COUNT(CLIENT_ID)"));
            mpccList.add(mpcc);
        }
        return mpccList;
    }

    public List<LongestProject> findLongestProject(Database database) throws SQLException {
        ResultSet resultSet = resultOfSQLRequest
                (database, "src/main/resources/SQL/find_longest_project.sql");
        List<LongestProject> lpList = new ArrayList();
        while (resultSet.next()) {
            LongestProject lp = new LongestProject();
            lp.setName(resultSet.getString("NAME"));
            lp.setCountMonth(resultSet.getInt("MONTH_COUNT"));
            lpList.add(lp);
        }
        return lpList;
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker(Database database) throws SQLException {
        ResultSet resultSet = resultOfSQLRequest
                (database, "src/main/resources/SQL/find_max_salary_worker.sql");
        List<MaxSalaryWorker> mswList = new ArrayList();
        while (resultSet.next()) {
            MaxSalaryWorker msw = new MaxSalaryWorker();
            msw.setName(resultSet.getString("NAME"));
            msw.setSalary(resultSet.getInt("SALARY"));
            mswList.add(msw);
        }
        return mswList;
    }

    public List<YoungestEldestWorker> findYoungestEldestWorkers(Database database) throws SQLException {
        ResultSet resultSet = resultOfSQLRequest
                (database, "src/main/resources/SQL/find_youngest_eldest_workers.sql");
        List<YoungestEldestWorker> yewList = new ArrayList();
        while (resultSet.next()) {
            YoungestEldestWorker yew = new YoungestEldestWorker();
            yew.setType(resultSet.getString("TYPE"));
            yew.setName(resultSet.getString("NAME"));
            yew.setBirthday(resultSet.getString("BIRTHDAY"));
            yewList.add(yew);
        }
        return yewList;
    }

    public List<ProjectPrice> printProjectPrices(Database database) throws SQLException {
        ResultSet resultSet = resultOfSQLRequest
                (database, "src/main/resources/SQL/print_project_prices.sql");
        List<ProjectPrice> ppList = new ArrayList();
        while (resultSet.next()) {
            ProjectPrice pp = new ProjectPrice();
            pp.setName(resultSet.getString("NAME"));
            pp.setPrise(resultSet.getInt("PRICE"));
            ppList.add(pp);
        }
        return ppList;
    }


}
