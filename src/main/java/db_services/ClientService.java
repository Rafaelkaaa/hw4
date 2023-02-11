package db_services;

import model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientService {

    public long create(String name) {
        validationClientName(name);
        createNewClient(name);
        return selectMaxIndexFromClient();
    }

    private void validationClientName(String name) {
        if (name.length() < 1) {
            throw new IllegalArgumentException
                    ("Name is too short, name must contain min 2 characters");
        } else if (name.length() >= 1000) {
            throw new IllegalArgumentException
                    ("Name is too long, name must contain max 1 000 characters");
        } else if (name == null) {
            throw new NullPointerException("Name must be not null value");
        }
    }

    public static PreparedStatement createStatement(String sqlRequest) throws SQLException {
        Connection connection = Database.getConnection();
        return connection
                .prepareStatement(sqlRequest);
    }

    private void createNewClient(String name) {
        try (PreparedStatement statementForCreate =
                     createStatement("INSERT INTO CLIENT (NAME) VALUES (?)")) {
            statementForCreate.setString(1, name);
            statementForCreate.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private long selectMaxIndexFromClient() {
        try (PreparedStatement statementForSelect =
                     createStatement("SELECT MAX (ID) FROM CLIENT")) {
            ResultSet resultSet = statementForSelect.executeQuery();
            resultSet.next();
            return resultSet.getLong("MAX(ID)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return -1;
        }
    }

    public String getById(long id) {
        return clientByValidId(id).toString();
    }

    private Client clientByValidId(long id) {
        List<Client> allClients = listAll();
        for (Client client : allClients) {
            if (client.getId() == id) {
                return client;
            }
        }
            throw new IllegalArgumentException("Table doesn't contain index " + id);

    }

    public void deleteById(long id) {
        clientByValidId(id);
        try (PreparedStatement statement =
                     createStatement("DELETE FROM CLIENT WHERE ID = " + id)) {
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Client> listAll() {
        try (PreparedStatement statementForSelect =
                     createStatement("SELECT * FROM CLIENT")) {
            ResultSet resultSet = statementForSelect.executeQuery();
            List<Client> clientList = new ArrayList();
            while (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getLong("ID"));
                client.setName(resultSet.getString("NAME"));
                clientList.add(client);
            }
            return clientList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public void setName(long id, String name) {
        validationClientName(name);
        clientByValidId(id);
        try (PreparedStatement statement =
                     createStatement("UPDATE CLIENT SET NAME = '"
                             + name + "' WHERE ID = " + id)) {
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}