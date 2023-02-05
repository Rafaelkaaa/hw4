package bd_services;

import model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {

    public List<MaxProjectCountClient> findMaxProjectsClient() throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/HW3", "sa", "");
             Statement statement = connection.createStatement();
        ) {
            String sql = null;
            try {
                sql = String.join("\n", Files.readAllLines(Paths
                        .get("src/main/resources/sql/find_max_projects_client.sql")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            ResultSet resultSet = statement.executeQuery(sql);
            List<MaxProjectCountClient> mpccList = new ArrayList();
            while (resultSet.next()) {
                MaxProjectCountClient mpcc = new MaxProjectCountClient();
                mpcc.setName(resultSet.getString("NAME"));
                mpcc.setProjectCount(resultSet.getInt("COUNT(CLIENT_ID)"));
                mpccList.add(mpcc);
            }
            return mpccList;
        }
    }

    public List<LongestProject> findLongestProject() throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/HW3", "sa", "");
             Statement statement = connection.createStatement();
        ) {
            String sql = null;
            try {
                sql = String.join("\n", Files.readAllLines(Paths
                        .get("src/main/resources/sql/find_longest_project.sql")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            ResultSet resultSet = statement.executeQuery(sql);
            List<LongestProject> lpList = new ArrayList();
            while (resultSet.next()) {
                LongestProject lp = new LongestProject();
                lp.setName(resultSet.getString("NAME"));
                lp.setCountMonth(resultSet.getInt("MONTH_COUNT"));
                lpList.add(lp);
            }
            return lpList;
        }
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker() throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/HW3", "sa", "");
             Statement statement = connection.createStatement();
        ) {
            String sql = null;
            try {
                sql = String.join("\n", Files.readAllLines(Paths
                        .get("src/main/resources/sql/find_max_salary_worker.sql")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            ResultSet resultSet = statement.executeQuery(sql);
            List<MaxSalaryWorker> mswList = new ArrayList();
            while (resultSet.next()) {
                MaxSalaryWorker msw = new MaxSalaryWorker();
                msw.setName(resultSet.getString("NAME"));
                msw.setSalary(resultSet.getInt("SALARY"));
                mswList.add(msw);
            }
            return mswList;
        }
    }

    public List<YoungestEldestWorker> findYoungestEldestWorkers() throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/HW3", "sa", "");
             Statement statement = connection.createStatement();
        ) {
            String sql = null;
            try {
                sql = String.join("\n", Files.readAllLines(Paths
                        .get("src/main/resources/sql/find_youngest_eldest_workers.sql")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            ResultSet resultSet = statement.executeQuery(sql);
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
    }

    public List<ProjectPrice> printProjectPrices() throws SQLException {
            try (Connection connection = DriverManager.getConnection("jdbc:h2:~/HW3", "sa", "");
                 Statement statement = connection.createStatement();
            ) {
                String sql = null;
                try {
                    sql = String.join("\n", Files.readAllLines(Paths
                            .get("src/main/resources/sql/print_project_prices.sql")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ResultSet resultSet = statement.executeQuery(sql);
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
}
