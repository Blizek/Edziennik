package DAO;

import database.DBConnection;
import model.Absences;

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

    public List<Absences> getAllStudentAbsences(int student_id) throws SQLException {
        String sql = "SELECT absences.* FROM absences INNER JOIN student ON absences.student_id = student.student_id " +
                "WHERE student.student_id = " + student_id;
        return absencesDatabaseCommends(sql);
    }

    public List<Absences> getStudentAbsencesOrPresences(int student_id, boolean student_absence, boolean excused_absence) throws SQLException {
        String sql = "SELECT absences.* FROM absences INNER JOIN student ON absences.student_id = student.student_id WHERE" +
                " student.student_id = " + student_id + " and absences.student_absence = " + student_absence
                + " and absences.excused_absence = " + excused_absence;
        return absencesDatabaseCommends(sql);
    }

    public List<Absences> getAllStudentAbsencesFromSubject(int student_id, int subject_id) throws SQLException {
        String sql = "SELECT absences.* FROM student INNER JOIN absences ON student.student_id = absences.student_id " +
                "INNER JOIN lesson ON absences.lesson_id = lesson.lesson_id INNER JOIN teacher ON" +
                " lesson.teacher_id = teacher.teacher_id INNER JOIN school_subject ON" +
                " teacher.subject_id = school_subject.subject_id WHERE student.student_id = " + student_id +
                " AND school_subject.subject_id = " + subject_id;
        return absencesDatabaseCommends(sql);
    }

    @Override
    public void save(Absences absences) throws SQLException {
        int absence_id = absences.getAbsence_id();
        int student_id = absences.getStudent_id();
        int lesson_id = absences.getLesson_id();
        boolean student_absence = absences.isStudent_absence();
        boolean excused_absence = absences.isExcused_absence();
        String sql = "INSERT INTO absences(absence_id, student_id, lesson_id, student_absence, excused_absence) VALUES("
                + absence_id + ", " + student_id + ", " + lesson_id + ", " + student_absence + ", " + excused_absence + ")";
        statement.executeUpdate(sql);
    }

    @Override
    public void update(Absences absences) throws SQLException {
        int new_absence_id = absences.getAbsence_id();
        int new_student_id = absences.getStudent_id();
        int new_lesson_id = absences.getLesson_id();
        boolean new_student_absence = absences.isStudent_absence();
        boolean new_excused_absence = absences.isExcused_absence();
        String sql = "UPDATE absences SET absence_id = " + new_absence_id +", student_id = " + new_student_id + ", lesson_id = "
                + new_lesson_id + ", student_absence = " + new_student_absence + ", excused_absence = " + new_excused_absence
                + " WHERE absence_id = " + new_absence_id;
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
            boolean excused_absence = resultSet.getBoolean(5);
            absences.add(new Absences(absence_id, student_id, lesson_id,  student_absence, excused_absence));
        }
        return absences;
    }
}
