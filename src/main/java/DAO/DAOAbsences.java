package DAO;

import database.DBConnection;
import model.Absences;
import model.School;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOAbsences implements DAO<Absences> {
    Connection connection = DBConnection.getConnection();
    Statement statement = connection.createStatement();

    public DAOAbsences() throws SQLException {
    }


    @Override
    public List<Absences> get(int id) throws SQLException {
        String sql = "SELECT * FROM absences WHERE absence_id = " + id;
        return absencesDatabaseCommends(sql);
    }

    @Override
    public List<Absences> getAll() throws SQLException {
        String sql = "SELECT * FROM absences";
        return absencesDatabaseCommends(sql);
    }

    @Override
    public void save(Absences absences) throws SQLException {
        int absence_id = absences.getAbsence_id();
        int student_id = absences.getStudent_id();
        int lesson_id = absences.getLesson_id();
        boolean student_absence = absences.isStudent_absence();
        String sql = "INSERT INTO absences(absence_id, student_id, lesson_id, student_absence) VALUES(" + absence_id +
                ", " + student_id + ", " + lesson_id + ", " + student_absence + ")";
        statement.executeUpdate(sql);
    }

    @Override
    public void update(Absences absences) throws SQLException {
        int new_absence_id = absences.getAbsence_id();
        int new_student_id = absences.getStudent_id();
        int new_lesson_id = absences.getLesson_id();
        boolean new_student_absence = absences.isStudent_absence();
        String sql = "UPDATE absences SET absence_id = " + new_absence_id +", student_id = " + new_student_id + ", lesson_id = "
                + new_lesson_id + ", student_absence = " + new_student_absence + " WHERE absence_id = " + new_absence_id;
        statement.executeUpdate(sql);
    }

    @Override
    public void delete(Absences absences) throws SQLException {
        int absence_id = absences.getAbsence_id();
        String sql = "DELETE FROM absences WHERE absence_id = " + absence_id;
        statement.executeUpdate(sql);
    }

    private List<Absences> absencesDatabaseCommends(String sql) throws SQLException {
        ResultSet resultSet = statement.executeQuery(sql);
        List<Absences> absences = new ArrayList<>();
        while (resultSet.next()) {
            int absence_id = resultSet.getInt(1);
            int student_id = resultSet.getInt(2);
            int lesson_id = resultSet.getInt(3);
            boolean student_absence = resultSet.getBoolean(4);
            absences.add(new Absences(absence_id, student_id, lesson_id,  student_absence));
        }
        return absences;
    }
}
