package jdbc.dao;

import jdbc.entity.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarJDBCDao implements CarDAO {

    public void add(Car car) {
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try{
            int markId = getMarkId(car.getMark(), connection);

            if(markId == -1) {
                statement = connection.prepareStatement("INSERT into marks(mark) VALUES (?)");
                statement.setString(1, car.getMark());
                statement.execute();
                ResultSet resultSet = statement.executeQuery("SELECT MAX(id) FROM marks");
                resultSet.next();
                markId = resultSet.getInt(1);
            }
            statement = connection.prepareStatement(
                    "INSERT INTO cars(mark_id, model, price) VALUES (?, ?, ?)");

            statement.setInt(1, markId);
            statement.setString(2, car.getModel());
            statement.setInt(3, car.getPrice());

            statement.execute();
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

    public List<Car> getAll() {
        List<Car> allCars = new ArrayList<Car>();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try{
            statement = connection.prepareStatement(
                    "SELECT c.id, m.mark, c.model, c.price FROM cars as c"
                            + " INNER JOIN marks as m ON m.id = c.mark_id");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                long id = resultSet.getInt(1);
                String mark = resultSet.getString(2);
                String model = resultSet.getString(3);
                int price = resultSet.getInt(4);
                Car car = new Car();
                car.setId(id);
                car.setMark(mark);
                car.setModel(model);
                car.setPrice(price);
                allCars.add(car);
            }
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
        return allCars;
    }

    public Car getById(int id) {
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(
                    "SELECT m.mark, c.model, c.price FROM cars as c"
                            + "INNER JOIN marks as m ON m.id = c.marks_id WHERE c.id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                String mark = resultSet.getString(1);
                String model = resultSet.getString(2);
                int price = resultSet.getInt(3);
                Car car = new Car();
                car.setId(id);
                car.setMark(mark);
                car.setModel(model);
                car.setPrice(price);
                return car;
            }
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
        return null;
    }

    public void updatePrice(int price, int carId) {
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try{
            statement = connection.prepareStatement("UPDATE cars SET price = ? WHERE id = ?");
            statement.setInt(1, price);
            statement.setInt(2, carId);
            int updatedValues = statement.executeUpdate();
            System.out.println("Updateed values : " + updatedValues);
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

    public void remove(int carId) {
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try{
            statement = connection.prepareStatement("DELETE FROM cars WHERE id = ?");
            statement.setInt(1, carId);

            int deletedValues = statement.executeUpdate();

            System.out.println("Deleted values : " + deletedValues);
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

    @Override
    public void remove(String markName) {
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try{
            int markId = getMarkId(markName, connection);

            statement = connection.prepareStatement("DELETE FROM cars WHERE id = ?");
            statement.setInt(1, markId);

            int deletedValues = statement.executeUpdate();

            System.out.println("Deleted values : " + deletedValues);
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

    private int getMarkId(String markName, Connection connection) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement("SELECT id FROM marks WHERE mark = ?");

            statement.setString(1, markName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                return resultSet.getInt(1);
            }
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
        return -1;
    }

    private Connection getConnection() {
        Connection connection = null;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/car_shop", "root", "root");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

