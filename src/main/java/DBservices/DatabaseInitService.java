package DBservices;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

public class DatabaseInitService {
    public static final String INIT_BD_FILENAME = "src/main/resources/SQL/init_db.sql";

    public static void main(String[] args) {
        Database database = Database.getINSTANCE();
        System.out.println(new DatabaseInitService().initDB(database));
    }

    public int initDB (Database database){
        String  sql = null;
        try {
          sql = String.join("\n", Files.readAllLines(Paths.get(INIT_BD_FILENAME)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return database.statementUpdate(sql);
    }

}
