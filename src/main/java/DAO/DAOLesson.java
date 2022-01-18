package DAO;

import database.DBConnection;
import model.Lesson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOLesson implements DAO<Lesson> {
    Connection connection = DBConnection.getConnection();
    Statement statement = connection.createStatement();

    public DAOLesson() throws SQLException {
    }

    @Override
    public List<Lesson> get(int id) throws SQLException {
        String sql = "SELECT * FROM lesson WHERE lesson_id = "  + id;
        return lessonDatabaseCommends(sql);
    }

    @Override
    public List<Lesson> getAll() throws SQLException {
        String sql = "SELECT * FROM lesson";
        return lessonDatabaseCommends(sql);
    }

    @Override
    public void save(Lesson lesson) throws SQLException {
        int lesson_id = lesson.getLesson_id();
        int teacher_id = lesson.getTeacher_id();
        String lesson_subject = lesson.getLesson_subject();
        String lesson_date = lesson.getLesson_date().toString().substring(0, 19);
        String sql = "INSERT INTO lesson(lesson_id, teacher_id, lesson_subject, lesson_date) VALUES (" + lesson_id + ", "
                + teacher_id + ", '" + lesson_subject + "', '" + lesson_date + "')";
        statement.executeUpdate(sql);
    }

    @Override
    public void update(Lesson lesson) throws SQLException {
        int new_lesson_id = lesson.getLesson_id();
        int new_teacher_id = lesson.getTeacher_id();
        String new_lesson_subject = lesson.getLesson_subject();
        String new_lesson_date = lesson.getLesson_date().toString().substring(0, 19);
        String sql = "UPDATE lesson SET lesson_id = " + new_lesson_id + ", teacher_id = " + new_teacher_id + ", lesson_subject = '"
                + new_lesson_subject + "', lesson_date = '" + new_lesson_date + "' WHERE lesson_id = " + new_lesson_id;
        statement.executeUpdate(sql);
    }

    @Override
    public void delete(Lesson lesson) throws SQLException {
        int lesson_id = lesson.getLesson_id();
        String sql = "DELETE FROM lesson WHERE lesson_id = " + lesson_id;
        statement.executeUpdate(sql);
    }

    private List<Lesson> lessonDatabaseCommends(String sql) throws SQLException {
        ResultSet resultSet = statement.executeQuery(sql);
        List<Lesson> lessons = new ArrayList<>();
        while (resultSet.next()) {
            int lesson_id = resultSet.getInt(1);
            int teacher_id = resultSet.getInt(2);
            String lesson_subject = resultSet.getString(3);
            Timestamp lesson_date = resultSet.getTimestamp(4);
            lessons.add(new Lesson(lesson_id, teacher_id, lesson_subject, lesson_date));
        }
        return lessons;
    }
}
