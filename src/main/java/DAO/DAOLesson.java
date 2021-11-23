package DAO;

import database.DBConnection;
import model.Lesson;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        String lesson_subject = lesson.getLesson_subject();
        boolean lesson_presence = lesson.isStudent_presence();
        String sql = "INSERT INTO lesson(lesson_id, lesson_subject, lesson_presence) VALUES(" + lesson_id + ", '" + lesson_subject
                + "', " + lesson_presence + ")";
        statement.executeUpdate(sql);
    }

    @Override
    public void update(Lesson lesson) throws SQLException {
        int new_lesson_id = lesson.getLesson_id();
        String new_lesson_subject = lesson.getLesson_subject();
        boolean new_lesson_presence = lesson.isStudent_presence();
        String sql = "UPDATE lesson SET lesson_id = " + new_lesson_id + ", lesson_subject = '" + new_lesson_subject + "', lesson_presence = "
                + new_lesson_presence + " WHERE lesson_id = " + new_lesson_id;
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
            String lesson_subject = resultSet.getString(2);
            boolean lesson_presence = resultSet.getBoolean(3);
            lessons.add(new Lesson(lesson_id, lesson_subject, lesson_presence));
        }
        return lessons;
    }
}
