package bd_services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    Connection connection;

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:h2:~/HW3", "sa", "");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}


