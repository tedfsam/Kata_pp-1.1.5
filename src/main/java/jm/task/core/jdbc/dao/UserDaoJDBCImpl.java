package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.Util;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final Connection connection = Util.getConnection();


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String command = "CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(21) NOT NULL , lastName VARCHAR(21) NOT NULL , age TINYINT)";
        try (PreparedStatement ps = connection.prepareStatement(command);) {

            ps.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement();) {

            statement.executeUpdate("DROP TABLE IF EXISTS users");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement("INSERT INTO users(name, lastName, age) VALUES (?,?,?)")) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();

            System.out.println("Create user with name " + name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement ps
                     = connection.prepareStatement("DELETE FROM users WHERE id=?");) {

            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users")) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                User user = new User(name, lastName, age);
                user.setId(id);
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement();) {

            statement.executeUpdate("TRUNCATE TABLE users");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
