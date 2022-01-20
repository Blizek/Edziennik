package DAO;

import database.DBConnection;
import model.Mark;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOMark implements DAO<Mark> {
    Connection connection = DBConnection.getConnection();
    Statement statement = connection.createStatement();

    public DAOMark() throws SQLException {
    }

    @Override
    public List<Mark> get(int id) throws SQLException {
        String sql = "SELECT * FROM mark WHERE mark_id = " + id;
        return markDatabaseCommends(sql);
    }

    @Override
    public List<Mark> getAll() throws SQLException {
        String sql = "SELECT * FROM mark";
        return markDatabaseCommends(sql);
    }

    public List<Mark> getByStudent(int student_id) throws SQLException {
        String sql = "SELECT * FROM mark WHERE student_id = " + student_id;
        return markDatabaseCommends(sql);
    }

    public List<Mark> getByTeacher(int teacher_id) throws SQLException {
        String sql = "SELECT * FROM mark WHERE teacher_id = " + teacher_id;
        return markDatabaseCommends(sql);
    }

    public List<Mark> getAllStudentMarksFromSubject(int subject_id) throws SQLException {
        String sql = "SELECT mark.* FROM student INNER JOIN mark ON student.student_id = mark.student_id INNER JOIN " +
                "teacher ON teacher.teacher_id = mark.teacher_id INNER JOIN school_subject ON school_subject.subject_id " +
                "= teacher.subject_id WHERE school_subject.subject_id = " + subject_id;
        return markDatabaseCommends(sql);
    }

    @Override
    public void save(Mark mark) throws SQLException {
        int mark_id = mark.getMark_id();
        int student_id = mark.getStudent_id();
        int teacher_id = mark.getTeacher_id();
        float mark_value = mark.getMark_value();
        String mark_description = mark.getMark_description();
        String sql = "INSERT INTO mark(mark_id, student_id, teacher_id, mark_value, mark_description) VALUES(" + mark_id
                + ", " + student_id + ", " + teacher_id + ", " + mark_value + ", '" + mark_description + "')";
        statement.executeUpdate(sql);
    }

    @Override
    public void update(Mark mark) throws SQLException {
        int new_mark_id = mark.getMark_id();
        int new_student_id = mark.getStudent_id();
        int new_teacher_id = mark.getTeacher_id();
        float new_mark_value = mark.getMark_value();
        String new_mark_description = mark.getMark_description();
        String sql = "UPDATE mark SET mark_id = " + new_mark_id + ", student_id = " + new_student_id + ", teacher_id = "
                + new_teacher_id + ", mark_value = " + new_mark_value + ", mark_description = '" + new_mark_description
                + "' WHERE mark_id = " + new_mark_id;
        statement.executeUpdate(sql);
    }

    @Override
    public void delete(Mark mark) throws SQLException {
        int mark_id = mark.getMark_id();
        String sql = "DELETE FROM mark WHERE mark_id = " + mark_id;
        statement.executeUpdate(sql);
    }

    private List<Mark> markDatabaseCommends(String sql) throws SQLException {
        ResultSet resultSet = statement.executeQuery(sql);
        List<Mark> marks = new ArrayList<>();
        while (resultSet.next()) {
            int mark_id = resultSet.getInt(1);
            int student_id = resultSet.getInt(2);
            int teacher_id = resultSet.getInt(3);
            float mark_value = resultSet.getFloat(4);
            String mark_description = resultSet.getString(5);
            marks.add(new Mark(mark_id, student_id, teacher_id, mark_value, mark_description));
        }
        return marks;
    }
}
