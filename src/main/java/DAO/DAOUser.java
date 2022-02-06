package DAO;

import database.DBConnection;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOUser implements DAO<User> {
    Connection connection = DBConnection.getConnection();
    Statement statement = connection.createStatement();

    public DAOUser() throws SQLException {
    }

    @Override
    public List<User> get(int id) throws SQLException {
        String sql = "SELECT * FROM user WHERE user_id = " + id;
        return userDatabaseCommends(sql);
    }

    @Override
    public List<User> getAll() throws SQLException {
        String sql = "SELECT * FROM user";
        return userDatabaseCommends(sql);
    }

    public List<User> getByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM user WHERE user_email = '" + email + "'";
        return userDatabaseCommends(sql);
    }

    public List<User> getByStudentID(int student_id) throws SQLException {
        String sql = "SELECT user.* FROM user INNER JOIN student ON user.user_id = student.user_id WHERE " +
                "student.student_id = " + student_id;
        return userDatabaseCommends(sql);
    }

    @Override
    public void save(User user) throws SQLException {
        int user_id = user.getUser_id();
        String user_email = user.getUser_email();
        String user_password = user.getUser_password();
        String user_role = user.getUser_role();
        String sql = "INSERT INTO user(user_id, user_email, user_password, user_role) VALUES(" + user_id + ", '" + user_email
                + "', '" + user_password + "', '" + user_role + "')";
        statement.executeUpdate(sql);
    }

    @Override
    public void update(User user) throws SQLException {
        int new_user_id = user.getUser_id();
        String new_user_email = user.getUser_email();
        String new_user_password = user.getUser_password();
        String new_user_role = user.getUser_role();
        String sql = "UPDATE user SET user_id = " + new_user_id + ", user_email = '" + new_user_email + "', user_password = '"
                + new_user_password + "', user_role = '" + new_user_role + "' WHERE user_id = " + new_user_id;
        statement.executeUpdate(sql);
    }

    @Override
    public void delete(User user) throws SQLException {
        int user_id = user.getUser_id();
        String sql = "DELETE FROM user WHERE user_id = " + user_id;
        statement.executeUpdate(sql);
    }

    private List<User> userDatabaseCommends(String sql) throws SQLException {
        ResultSet resultSet = statement.executeQuery(sql);
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            int user_id = resultSet.getInt(1);
            String user_email = resultSet.getString(2);
            String user_password = resultSet.getString(3);
            String user_role = resultSet.getString(4);
            users.add(new User(user_id, user_email, user_password, user_role));
        }
        return users;
    }
}
