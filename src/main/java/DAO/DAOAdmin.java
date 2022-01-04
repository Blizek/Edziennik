package DAO;

import database.DBConnection;
import model.Admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOAdmin implements DAO<Admin>{
    Connection connection = DBConnection.getConnection();
    Statement statement = connection.createStatement();

    public DAOAdmin() throws SQLException {
    }

    @Override
    public List<Admin> get(int id) throws SQLException {
        String sql = "SELECT * FROM admin WHERE admin_id = " + id;
        return adminDatabaseCommends(sql);
    }

    @Override
    public List<Admin> getAll() throws SQLException {
        String sql = "SELECT * FROM admin";
        return adminDatabaseCommends(sql);
    }

    public List<Admin> getByUserID(int id) throws SQLException {
        String sql = "SELECT * FROM admin WHERE user_id = " + id;
        return adminDatabaseCommends(sql);
    }

    @Override
    public void save(Admin admin) throws SQLException {
        int admin_id = admin.getAdmin_id();
        int user_id = admin.getUser_id();
        String name = admin.getAdmin_name();
        String surname = admin.getAdmin_surname();
        String sql = "INSERT INTO admin (admin_id, user_id, admin_name, admin_surname) VALUES (" + admin_id + ", " + user_id +
                ", '" + name + "', '" + surname + "')";
        statement.executeUpdate(sql);
    }

    @Override
    public void update(Admin admin) throws SQLException {
        int new_admin_id = admin.getAdmin_id();
        int new_user_id = admin.getUser_id();
        String new_admin_name = admin.getAdmin_name();
        String new_admin_surname = admin.getAdmin_surname();
        String sql = "UPDATE admin SET admin_id = " + new_admin_id +", user_id = " + new_user_id + ", admin_name = '" +
                new_admin_name + "', admin_surname = '" + new_admin_surname + "' WHERE admin_id = " + new_admin_id;
        statement.executeUpdate(sql);
    }

    @Override
    public void delete(Admin admin) throws SQLException {
        int admin_id = admin.getAdmin_id();
        String sql = "DELETE FROM admin WHERE admin_id = " + admin_id;
        statement.executeUpdate(sql);
    }

    private List<Admin> adminDatabaseCommends(String sql) throws SQLException{
        ResultSet resultSet = statement.executeQuery(sql);
        List<Admin> admins = new ArrayList<>();
        while (resultSet.next()) {
            int admin_id = resultSet.getInt(1);
            int user_id = resultSet.getInt(2);
            String admin_name = resultSet.getString(3);
            String admin_surname = resultSet.getString(4);
            admins.add(new Admin(admin_id, user_id, admin_name, admin_surname));
        }
        return admins;
    }
}
