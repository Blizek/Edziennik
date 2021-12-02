package DAO;

import database.DBConnection;
import model.School;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOSchool implements DAO<School> {
    Connection connection = DBConnection.getConnection();
    Statement statement = connection.createStatement();

    public DAOSchool() throws SQLException {
    }

    @Override
    public List<School> get(int id) throws SQLException {
        String sql = "SELECT * FROM school WHERE school_id = " + id;
        return schoolDatabaseCommends(sql);
    }

    @Override
    public List<School> getAll() throws SQLException {
        String sql = "SELECT * FROM school";
        return schoolDatabaseCommends(sql);
    }

    public List<School> getBySchoolName(String school_name) throws SQLException {
        String sql = "SELECT * FROM school WHERE school_name = '" + school_name + "'";
        return schoolDatabaseCommends(sql);
    }

    @Override
    public void save(School school) throws SQLException {
        int school_id = school.getSchool_id();
        int principal_id = school.getPrincipal_id();
        String school_name = school.getSchool_name();
        String school_place = school.getSchool_place();
        String school_email = school.getSchool_email();
        String school_phone_number = school.getSchool_phone_number();
        String sql = "INSERT INTO school(school_id, principal_id, school_name, school_place, school_email, school_phone_number) VALUES("
                + school_id + ", " + principal_id + ", '" + school_name + "', '" + school_place + "', '" + school_email
                + "', '" + school_phone_number + "')";
        statement.executeUpdate(sql);
    }

    @Override
    public void update(School school) throws SQLException {
        int new_school_id = school.getSchool_id();
        int new_principal_id = school.getPrincipal_id();
        String new_school_name  = school.getSchool_name();
        String new_school_place = school.getSchool_place();
        String new_school_email = school.getSchool_email();
        String new_school_phone_number = school.getSchool_phone_number();
        String sql = "UPDATE school SET school_id = " + new_school_id + ", principal_id = " + new_principal_id + ", school_name = '"
                + new_school_name + "', school_place = '" + new_school_place + "', school_email = '" + new_school_email
                + "', school_phone_number = '" + new_school_phone_number + "' WHERE school_id = " + new_school_id;
        statement.executeUpdate(sql);
    }

    @Override
    public void delete(School school) throws SQLException {
        int school_id = school.getSchool_id();
        String sql = "DELETE FROM school WHERE school_id = " + school_id;
        statement.executeUpdate(sql);
    }

    private List<School> schoolDatabaseCommends(String sql) throws SQLException {
        ResultSet resultSet = statement.executeQuery(sql);
        List<School> schools = new ArrayList<>();
        while (resultSet.next()) {
            int school_id = resultSet.getInt(1);
            int principal_id = resultSet.getInt(2);
            String school_name = resultSet.getString(3);
            String school_place = resultSet.getString(4);
            String school_email = resultSet.getString(5);
            String school_phone_number = resultSet.getString(6);
            schools.add(new School(school_id, principal_id, school_name, school_place, school_email, school_phone_number));
        }
        return schools;
    }
}
