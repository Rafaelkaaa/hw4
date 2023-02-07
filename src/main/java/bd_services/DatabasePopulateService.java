package bd_services;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabasePopulateService {

    public static void main(String[] args) {
        new DatabasePopulateService().populateBD();
    }

    private void populateTableWorker()  {
            try (Connection connection = Database.getConnection();
                 PreparedStatement statement = connection
                         .prepareStatement("INSERT INTO WORKER " +
                                 "(NAME, BIRTHDAY, LEVEL, SALARY) VALUES (?, ?, ?, ?)");
            ) {
                JsonArray jsonArray = fileToJsonArray
                        ("src/main/resources/json/populate_table_worker.json");
                for (JsonElement element :
                            jsonArray) {
                        JsonObject object = element.getAsJsonObject();
                        statement.setString(1,object.get("name").getAsString());
                        statement.setString(2,object.get("birthday").getAsString());
                        statement.setString(3,object.get("level").getAsString());
                        statement.setString(4,object.get("salary").getAsString());
                        statement.addBatch();

                }
                statement.executeBatch();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
    }

    private void populateTableClient()  {
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("INSERT INTO CLIENT " +
                             "(NAME) VALUES (?)");
        ) {
            JsonArray jsonArray = fileToJsonArray
                    ("src/main/resources/json/populate_table_client.json");
            for (JsonElement element :
                    jsonArray) {
                JsonObject object = element.getAsJsonObject();
                statement.setString(1,object.get("name").getAsString());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void populateTableProject() {
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("INSERT INTO PROJECT (CLIENT_ID, START_DATE, FINISH_DATE)" +
                             "VALUES (?, ?, ?)");
        ) {
            JsonArray jsonArray = fileToJsonArray
                    ("src/main/resources/json/populate_table_project.json");
            for (JsonElement element :
                    jsonArray) {
                JsonObject object = element.getAsJsonObject();
                statement.setInt(1,object.get("clientId").getAsInt());
                statement.setString(2,object.get("startDate").getAsString());
                statement.setString(3,object.get("finishDate").getAsString());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void populateTableProjectWorker() {
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("INSERT INTO PROJECT_WORKER (PROJECT_ID, WORKER_ID)" +
                             "VALUES(?,?)");
        ) {
            JsonArray jsonArray = fileToJsonArray
                    ("src/main/resources/json/populate_table_project_worker.json");
            for (JsonElement element :
                    jsonArray) {
                JsonObject object = element.getAsJsonObject();
                statement.setInt(1,object.get("projectId").getAsInt());
                statement.setInt(2,object.get("workerId").getAsInt());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void populateBD(){
        populateTableClient();
        populateTableWorker();
        populateTableProject();
        populateTableProjectWorker();
    }


    private static JsonArray fileToJsonArray(String path) {
        try (FileReader reader = new FileReader
                (path)) {
           return JsonParser.parseReader(reader).getAsJsonArray();
        } catch (IOException e) {
            e.printStackTrace();}
        return null;
    }
}

