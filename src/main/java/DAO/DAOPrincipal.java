package DAO;

import database.DBConnection;
import model.Principal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOPrincipal implements DAO<Principal> {
    Connection connection = DBConnection.getConnection();
    Statement statement = connection.createStatement();

    public DAOPrincipal() throws SQLException {
    }

    @Override
    public List<Principal> get(int id) throws SQLException {
        String sql = "SELECT * FROM principal WHERE principal_id = " + id;
        return principalDatabaseCommends(sql);
    }

    @Override
    public List<Principal> getAll() throws SQLException {
        String sql = "SELECT * FROM principal";
        return principalDatabaseCommends(sql);
    }

    @Override
    public void save(Principal principal) throws SQLException {
        int principal_id = principal.getPrincipal_id();
        int user_id = principal.getUser_id();
        String principal_name = principal.getPrincipal_name();
        String principal_surname = principal.getPrincipal_surname();
        String sql = "INSERT INTO principal(principal_id, user_id, principal_name, principal_surname) VALUES(" + principal_id
                + ", " + user_id + ", '" + principal_name + "', '" + principal_surname + "')";
        statement.executeUpdate(sql);
    }

    @Override
    public void update(Principal principal) throws SQLException {
        int new_principal_id = principal.getPrincipal_id();
        int new_user_id = principal.getUser_id();
        String new_principal_name = principal.getPrincipal_name();
        String new_principal_surname = principal.getPrincipal_surname();
        String sql = "UPDATE principal SET principal_id = " + new_principal_id + ", user_id = " + new_user_id + ", principal_name = '"
                + new_principal_name + "', principal_surname = '" + new_principal_surname + "' WHERE principal_id = " + new_principal_id;
        statement.executeUpdate(sql);
    }

    @Override
    public void delete(Principal principal) throws SQLException {
        int principal_id = principal.getPrincipal_id();
        String sql = "DELETE FROM principal WHERE principal_id = " + principal_id;
        statement.executeUpdate(sql);
    }

    private List<Principal> principalDatabaseCommends(String sql) throws SQLException {
        ResultSet resultSet = statement.executeQuery(sql);
        List<Principal> principals = new ArrayList<>();
        while (resultSet.next()) {
            int principal_id = resultSet.getInt(1);
            int user_id = resultSet.getInt(2);
            String principal_name = resultSet.getString(3);
            String principal_surname = resultSet.getString(4);
            principals.add(new Principal(principal_id, user_id, principal_name, principal_surname));
        }
        return principals;
    }
}
