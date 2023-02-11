package db_services;

import org.flywaydb.core.Flyway;

class DatabaseMigrationService {

    public static void main(String[] args){
        new DatabaseMigrationService().initDb();
    }

    public void initDb()  {
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:h2:~/HW3", "sa", "")
                .load();

        flyway.migrate();
    }
}
