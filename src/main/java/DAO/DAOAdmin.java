package DAO;

import database.DBConnection;
import model.Admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
    public List<Admin> getAll(int id) throws SQLException {
        String sql = "SELECT * FROM admin";
        return adminDatabaseCommends(sql);
    }

    @Override
    public void save(Admin daoAdmin) throws SQLException {
        int admin_id = daoAdmin.getAdmin_id();
        int user_id = daoAdmin.getUser_id();
        String name = daoAdmin.getAdmin_name();
        String surname = daoAdmin.getAdmin_surname();
        String sql = "INSERT INTO admin (admin_id, user_id, admin_name, admin_surname) VALUES (" + admin_id + ", " + user_id +
                ", '" + name + "', '" + surname + "')";
        statement.executeUpdate(sql);
    }

    @Override
    public void update(String[] params) throws SQLException {

    }

    @Override
    public void delete(Admin daoAdmin) throws SQLException {

    }

    private List<Admin> adminDatabaseCommends(String sql) throws SQLException{
        return null;
    }
}
