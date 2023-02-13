package db_services;

import model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientService {

    private final static String SELECT_ALL_CLIENTS = "SELECT * FROM CLIENT";
    private final static String SELECT_MAX_INDEX_FROM_CLIENT = "SELECT MAX (ID) FROM CLIENT";
    private final static String INSERT_CLIENT = "INSERT INTO CLIENT (NAME) VALUES (?)";
    private final static String DELETE_CLIENT_BY_ID = "DELETE FROM CLIENT WHERE ID = ?";
    private final static String UPDATE_CLIENT_NAME_BY_ID = "UPDATE CLIENT SET NAME = ? WHERE ID = ?";


    public long create(String name) {
        validationClientName(name);
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_CLIENT)) {
            statement.setString(1, name);
            statement.executeUpdate();
            System.out.println(connection.isClosed());
            System.out.println(statement.isClosed());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return maxIndex();
    }


    public List<Client> listAll() {
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_CLIENTS);
             ResultSet resultSet = statement.executeQuery()) {
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
        }
        throw new NullPointerException();
    }

    public String getById(long id) {
        return clientByValidId(id).toString();
    }

    public void deleteById(long id) {
        clientByValidId(id);
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CLIENT_BY_ID)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void setName(long id, String name) {
        validationClientName(name);
        clientByValidId(id);
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CLIENT_NAME_BY_ID)) {
            statement.setString(1, name);
            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

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

    private long maxIndex() {
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_MAX_INDEX_FROM_CLIENT);
             ResultSet resultSet = statement.executeQuery()) {
            resultSet.next();
            return resultSet.getLong("MAX(ID)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new NullPointerException();
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


}