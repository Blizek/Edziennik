package DAO;

import database.DBConnection;
import model.Teacher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOTeacher implements DAO<Teacher> {
    Connection connection = DBConnection.getConnection();
    Statement statement = connection.createStatement();

    public DAOTeacher() throws SQLException {
    }

    @Override
    public List<Teacher> get(int id) throws SQLException {
        String sql = "SELECT * FROM teacher WHERE teacher_id = " + id;
        return teacherDatabaseCommends(sql);
    }

    @Override
    public List<Teacher> getAll() throws SQLException {
        String sql = "SELECT * FROM teacher";
        return teacherDatabaseCommends(sql);
    }

    public List<Teacher> getByTeachersSubject(String subject) throws SQLException {
        String sql = "SELECT * FROM teacher WHERE teacher_subject = " + subject;
        return teacherDatabaseCommends(sql);
    }

    public List<Teacher> getByUserID(int id) throws SQLException {
        String sql = "SELECT * FROM teacher WHERE user_id = " + id;
        return teacherDatabaseCommends(sql);
    }

    @Override
    public void save(Teacher teacher) throws SQLException {
        int teacher_id = teacher.getTeacher_id();
        int user_id = teacher.getUser_id();
        int school_id = teacher.getSchool_id();
        String teacher_name = teacher.getTeacher_name();
        String teacher_surname = teacher.getTeacher_surname();
        String teacher_subject = teacher.getTeacher_subject();
        String sql = "INSERT INTO teacher(teacher_id, user_id, school_id, teacher_name, teacher_surname, teacher_subject) VALUES("
                + teacher_id + ", " + user_id + ", " + school_id + ", '" + teacher_name + "', '" + teacher_surname + "', '"
                + teacher_subject + "')";
        statement.executeUpdate(sql);
    }

    @Override
    public void update(Teacher teacher) throws SQLException {
        int new_teacher_id = teacher.getTeacher_id();
        int new_user_id = teacher.getUser_id();
        int new_school_id = teacher.getSchool_id();
        String new_teacher_name = teacher.getTeacher_name();
        String new_teacher_surname = teacher.getTeacher_surname();
        String new_teacher_subject = teacher.getTeacher_subject();
        String sql = "UPDATE teacher SET teacher_id = " + new_teacher_id + ", user_id = " + new_user_id + ", school_id = "
                + new_school_id + ", teacher_name = '" + new_teacher_name + "', teacher_surname = '" + new_teacher_surname
                + "', teacher_subject = '" + new_teacher_subject + "' WHERE teacher_id = " + new_teacher_id;
        statement.executeUpdate(sql);
    }

    @Override
    public void delete(Teacher teacher) throws SQLException {
        int teacher_id = teacher.getTeacher_id();
        String sql = "DELETE FROM teacher WHERE teacher_id = " +  teacher_id;
        statement.executeUpdate(sql);
    }

    private List<Teacher> teacherDatabaseCommends(String sql) throws SQLException {
        ResultSet resultSet = statement.executeQuery(sql);
        List<Teacher> teachers = new ArrayList<>();
        while (resultSet.next()) {
            int teacher_id = resultSet.getInt(1);
            int user_id = resultSet.getInt(2);
            int school_id = resultSet.getInt(3);
            String teacher_name = resultSet.getString(4);
            String teacher_surname = resultSet.getString(5);
            String teacher_subject = resultSet.getString(6);
            teachers.add(new Teacher(teacher_id, user_id, school_id, teacher_name, teacher_surname, teacher_subject));
        }
        return teachers;
    }
}
