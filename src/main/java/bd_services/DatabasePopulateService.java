package bd_services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabasePopulateService {
    public static final String POPULATE_BD_FILENAME = "src/main/resources/sql/populate_db.sql";

        public static void main(String[] args) throws SQLException {
        new DatabasePopulateService().populateDb();
    }

    public void populateDb() throws SQLException {
        try (Connection connection = Database.getConnection();
             Statement statement = connection.createStatement();
        ) {
            String sql = null;
            try {
                sql = String.join("\n", Files.readAllLines(Paths.get(POPULATE_BD_FILENAME)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            statement.executeUpdate(sql);
        }
    }
}