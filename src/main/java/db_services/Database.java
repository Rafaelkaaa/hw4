package db_services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Database {

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:h2:~/HW3", "sa", "");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}


