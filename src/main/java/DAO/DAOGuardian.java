package DAO;

import database.DBConnection;
import model.Guardian;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DAOGuardian implements DAO<Guardian> {
    Connection connection = DBConnection.getConnection();
    Statement statement = connection.createStatement();

    public DAOGuardian() throws SQLException {
    }

    @Override
    public List<Guardian> get(int id) throws SQLException {
        String sql = "SELECT * FROM guardian WHERE guardian_id = " + id;
        return guardianDatabaseCommends(sql);
    }

    @Override
    public List<Guardian> getAll() throws SQLException {
        String sql = "SELECT * FROM guardian";
        return guardianDatabaseCommends(sql);
    }

    public List<Guardian> getByUserID(int id) throws SQLException {
        String sql = "SELECT * FROM guardian WHERE user_id = " + id;
        return guardianDatabaseCommends(sql);
    }

    @Override
    public void save(Guardian guardian) throws SQLException {
        int guardian_id = guardian.getGuardian_id();
        int user_id = guardian.getUser_id();
        String guardian_name = guardian.getGuardian_name();
        String guardian_surname = guardian.getGuardian_surname();
        LocalDate guardian_date_of_birth = guardian.getGuardian_date_of_birth();
        String sql = "INSERT INTO guardian(guardian_id, user_id, guardian_name, guardian_surname, guardian_date_of_birth) VALUES("
                + guardian_id + ", " + user_id + ", '" + guardian_name + "', '" + guardian_surname + "', '" + guardian_date_of_birth + "')";
        statement.executeUpdate(sql);
    }

    @Override
    public void update(Guardian guardian) throws SQLException {
        int new_guardian_id = guardian.getGuardian_id();
        int new_user_id = guardian.getUser_id();
        String new_guardian_name = guardian.getGuardian_name();
        String new_guardian_surname = guardian.getGuardian_surname();
        LocalDate new_guardian_date_of_birth = guardian.getGuardian_date_of_birth();
        String sql = "UPDATE guardian SET guardian_id = " + new_guardian_id + ", user_id = " + new_user_id + ", guardian_name = '"
                + new_guardian_name + "', guardian_surname = '" + new_guardian_surname + "', guardian_date_of_birth = '"
                + new_guardian_date_of_birth + "' WHERE guardian_id = " + new_guardian_id;
        statement.executeUpdate(sql);
    }

    @Override
    public void delete(Guardian guardian) throws SQLException {
        int guardian_id = guardian.getGuardian_id();
        String sql = "DELETE FROM guardian WHERE guardian_id = " + guardian_id;
        statement.executeUpdate(sql);
    }

    private List<Guardian> guardianDatabaseCommends(String sql) throws SQLException{
        ResultSet resultSet = statement.executeQuery(sql);
        List<Guardian> guardians = new ArrayList<>();
        while (resultSet.next()) {
            int guardian_id = resultSet.getInt(1);
            int user_id = resultSet.getInt(2);
            String guardian_name = resultSet.getString(3);
            String guardian_surname = resultSet.getString(4);
            LocalDate guardian_date_of_birth = resultSet.getDate(5).toLocalDate();
            guardians.add(new Guardian(guardian_id, user_id, guardian_name, guardian_surname, guardian_date_of_birth));
        }
        return guardians;
    }
}
