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
        String sql = "SELECT * FROM Admin WHERE admin_id = " + id;
        return adminDatabaseComments(sql);
    }

    @Override
    public List<Admin> getAll(int id) throws SQLException {
        return null;
    }

    @Override
    public void update(String[] params) throws SQLException {

    }

    @Override
    public void delete(Admin daoAdmin) throws SQLException {

    }

    @Override
    public void save(Admin daoAdmin) throws SQLException {

    }

    private List<Admin> adminDatabaseComments(String sql) throws SQLException{
        return null;
    }
}
