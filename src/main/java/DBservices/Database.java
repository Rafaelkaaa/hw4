package DBservices;

import java.sql.*;

public class Database {
    private static final Database INSTANCE = new Database();
    public Connection connection;

    public Database() {
         try {
            connection = DriverManager.getConnection("jdbc:h2:~/HW3", "sa", "");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static Database getINSTANCE() {
        return INSTANCE;
    }

    public Connection getConnection() {
        return connection;
    }

    public int statementUpdate(String sql){
        try (Statement statement = getConnection().createStatement()) {
            return statement.executeUpdate(sql);
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }
    public ResultSet statementQuery(String sql) {
        Statement statement = null;
        try {
             statement = getConnection().createStatement();
            return statement.executeQuery(sql);
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            if (statement != null){
                try {
                    statement.closeOnCompletion();
                } catch (SQLException throwables) {
                  throw new RuntimeException(throwables);
                }
            }
        }
        return null;
    }


}
