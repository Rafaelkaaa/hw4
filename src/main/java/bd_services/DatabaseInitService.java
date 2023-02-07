package bd_services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseInitService {
    public static final String INIT_BD_FILENAME = "src/main/resources/sql/init_db.sql";

    public static void main(String[] args) throws SQLException {
        new DatabaseInitService().initDb();
    }

    public void initDb() throws SQLException {
        {
            String sql = null;
            try {
                sql = String.join("\n", Files.readAllLines(Paths.get(INIT_BD_FILENAME)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try (Connection connection = Database.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql);
            ) {
                statement.executeUpdate();
            }
        }
    }
}
