package DAO;

import database.DBConnection;
import model.Class;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DAOClass implements DAO<Class> {
    Connection connection = DBConnection.getConnection();
    Statement statement = connection.createStatement();

    public DAOClass() throws SQLException {
    }

    @Override
    public List<Class> get(int id) throws SQLException {
        String sql = "SELECT * FROM class WHERE class_id = " + id;
        return classDatabaseCommends(sql);
    }

    @Override
    public List<Class> getAll() throws SQLException {
        String sql = "SELECT * FROM class";
        return classDatabaseCommends(sql);
    }

    @Override
    public void save(Class daoClass) throws SQLException {
        int class_id = daoClass.getClass_id();
        int class_supervising_teacher_id = daoClass.getClass_supervising_teacher();
        String class_name = daoClass.getClass_name();
        String sql = "INSERT INTO class(class_id, class_supervising_teacher, class_name) VALUES(" + class_id + ", " + class_supervising_teacher_id
                + ", '" + class_name + "')";
        statement.executeUpdate(sql);
    }

    @Override
    public void update(Class daoClass) throws SQLException {

    }

    @Override
    public void delete(Class daoClass) throws SQLException {

    }

    private List<Class> classDatabaseCommends(String sql) throws SQLException {
        return null;
    }
}
