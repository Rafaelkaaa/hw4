package DBservices;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

public class DatabasePopulateService {
    public static final String POPULATE_BD_FILENAME = "src/main/resources/SQL/populate_db.sql";

    public static void main(String[] args) {
        Database database = Database.getINSTANCE();
        new DatabasePopulateService().populateDB(database);
    }

    public void populateDB (Database database){
        String  sql = null;
        try {
            sql = String.join("\n", Files.readAllLines(Paths.get(POPULATE_BD_FILENAME)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        database.statementUpdate(sql);
    }
}
