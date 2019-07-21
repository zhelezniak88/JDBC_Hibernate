package jdbc.dao;

import jdbc.entity.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientJDBCDao implements ClientDAO {

    public void add(Client client) {
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try{
            statement = connection.prepareStatement("INSERT INTO clients(name, age, phone) VALUES (?, ?, ?)");
            statement.setString(1, client.getName());
            statement.setInt(2, client.getAge());
            statement.setString(3, client.getPhone());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null && statement != null){
                try {
                    connection.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Client> getAll() {
        List<Client> allClients = new ArrayList<Client>();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try{
            statement = connection.prepareStatement("SELECT * FROM clients");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                long id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int age = resultSet.getInt(3);
                String phone = resultSet.getString(4);
                Client client = new Client();
                client.setId(id);
                client.setName(name);
                client.setAge(age);
                client.setPhone(phone);
                allClients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(connection != null && statement != null){
                try {
                    connection.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return allClients;
    }

    public Client getClientById(int id) {
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try{
            statement = connection.prepareStatement("SELECT name, age, phone FROM clients WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Client client = new Client();
                client.setId(id);
                client.setName(resultSet.getString(1));
                client.setAge(resultSet.getInt(2));
                client.setPhone(resultSet.getString(3));
                return client;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null && statement != null){
                try {
                    connection.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public void remove(int id) {
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try{
            statement = connection.prepareStatement("DELETE FROM clients WHERE id = ?");
            statement.setInt(1, id);
            int deletedClients = statement.executeUpdate();
            System.out.println("Deleted clients: " + deletedClients);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null && statement != null){
                try{
                    connection.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void remove(String name) {
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try{
            statement = connection.prepareStatement("DELETE FROM clients WHERE name = ?");
            statement.setString(1, name);
            int deletedClients = statement.executeUpdate();
            System.out.println("Deleted clients: " + deletedClients);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null && statement != null){
                try {
                    connection.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updatePhone(String phone, int id) {
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try{
            statement = connection.prepareStatement("UPDATE clients SET phone = ? WHERE id = ?");
            statement.setString(1, phone);
            statement.setInt(2, id);
            int updatedClients = statement.executeUpdate();
            System.out.println("Updated clients: " + updatedClients);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null && statement != null) {
                try {
                    connection.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updatePhone(String phone, String name) {
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try{
            statement = connection.prepareStatement("UPDATE clients SET phone = ? WHERE name = ?");
            statement.setString(1, phone);
            statement.setString(2, name);
            int updatedClients = statement.executeUpdate();
            System.out.println("Updated clients: " + updatedClients);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null && statement != null) {
                try {
                    connection.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Connection getConnection(){
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/car_shop", "root", "root");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

