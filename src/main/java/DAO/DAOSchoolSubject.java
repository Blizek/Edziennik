package DAO;

import database.DBConnection;
import model.Absences;
import model.SchoolSubject;
import model.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOSchoolSubject implements DAO<SchoolSubject> {
    Connection connection = DBConnection.getConnection();
    Statement statement = connection.createStatement();

    public DAOSchoolSubject() throws SQLException {
    }

    @Override
    public List<SchoolSubject> get(int id) throws SQLException {
        String sql = "SELECT * FROM school_subject WHERE subject_id = " + id;
        return schoolSubjectDatabaseCommends(sql);
    }

    @Override
    public List<SchoolSubject> getAll() throws SQLException {
        String sql = "SELECT * FROM school_subject";
        return schoolSubjectDatabaseCommends(sql);
    }

    public List<SchoolSubject> getAllStudentSubjects(Student student) throws SQLException {
        int student_id = student.getStudent_id();
        String sql = "SELECT school_subject.* FROM student INNER JOIN plan ON student.class_id = plan.class_id INNER " +
                "JOIN teacher ON plan.teacher_id = teacher.teacher_id INNER JOIN school_subject ON teacher.subject_id = " +
                "school_subject.subject_id WHERE student.student_id = " + student_id;
        return schoolSubjectDatabaseCommends(sql);
    }

    public List<SchoolSubject> getSubjectFromAbsence(int absence_id) throws SQLException {
        String sql = "SELECT school_subject.* FROM absences INNER JOIN lesson ON absences.lesson_id = lesson.lesson_id " +
                "INNER JOIN teacher ON lesson.teacher_id = teacher.teacher_id INNER JOIN school_subject ON " +
                "school_subject.subject_id = teacher.subject_id WHERE absences.absence_id = " + absence_id;
        return schoolSubjectDatabaseCommends(sql);
    }

    public List<SchoolSubject> getTeacherSubject(int teacher_id) throws SQLException {
        String sql = "SELECT school_subject.* FROM school_subject INNER JOIN teacher ON" +
                " school_subject.subject_id = teacher.subject_id WHERE teacher.teacher_id = " + teacher_id;
        return schoolSubjectDatabaseCommends(sql);
    }

    public List<SchoolSubject> getLessonSubject(int lesson_id) throws SQLException {
        String sql = "SELECT school_subject.* FROM school_subject INNER JOIN teacher ON " +
                "school_subject.subject_id = teacher.subject_id INNER JOIN lesson ON " +
                "teacher.teacher_id = lesson.teacher_id WHERE lesson.lesson_id = " + lesson_id;
        return schoolSubjectDatabaseCommends(sql);
    }

    @Override
    public void save(SchoolSubject subject) throws SQLException {
        int subject_id = subject.getSubject_id();
        String subject_name = subject.getSubject_name();
        String sql = "INSERT INTO school_subject(subject_id, subject_name) VALUES(" + subject_id + ", '" + subject_name
                + "')";
        statement.executeUpdate(sql);
    }

    @Override
    public void update(SchoolSubject subject) throws SQLException {
        int new_subject_id = subject.getSubject_id();
        String new_subject_name = subject.getSubject_name();
        String sql = "UPDATE school_subject SET subject_id = " + new_subject_id + ", subject_name = '" + new_subject_name
                + "' WHERE subject_id = " + new_subject_id;
        statement.executeUpdate(sql);
    }

    @Override
    public void delete(SchoolSubject subject) throws SQLException {
        int subject_id = subject.getSubject_id();
        String sql = "DELETE FROM school_subject WHERE subject_id = " + subject_id;
        statement.executeUpdate(sql);
    }

    private List<SchoolSubject> schoolSubjectDatabaseCommends(String sql) throws SQLException {
        ResultSet resultSet = statement.executeQuery(sql);
        List<SchoolSubject> schoolSubjects = new ArrayList<>();
        while (resultSet.next()) {
            int subject_id = resultSet.getInt(1);
            String subject_name = resultSet.getString(2);
            schoolSubjects.add(new SchoolSubject(subject_id, subject_name));
        }
        return schoolSubjects;
    }
}
