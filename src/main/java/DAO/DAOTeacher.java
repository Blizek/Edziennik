package DAO;

import database.DBConnection;
import model.Exams;
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

    public List<Teacher> getAllSorted() throws SQLException {
        String sql = "SELECT * FROM teacher ORDER BY teacher.teacher_surname, teacher.teacher_name";
        return teacherDatabaseCommends(sql);
    }

    public List<Teacher> getByTeachersSubject(int subject_id) throws SQLException {
        String sql = "SELECT teacher.* FROM teacher INNER JOIN school_subject ON " +
                "teacher.subject_id = school_subject.subject_id WHERE school_subject.subject_id = " + subject_id +
                " ORDER BY teacher.teacher_surname, teacher.teacher_name";
        return teacherDatabaseCommends(sql);
    }

    public List<Teacher> getByUserID(int id) throws SQLException {
        String sql = "SELECT * FROM teacher WHERE user_id = " + id;
        return teacherDatabaseCommends(sql);
    }

    public List<Teacher> getExamTeacher(int exam_id) throws SQLException {
        String sql = "SELECT teacher.* FROM teacher INNER JOIN plan ON teacher.teacher_id = plan.teacher_id INNER JOIN " +
                "exams ON plan.plan_id = exams.plan_id WHERE exams.exam_id = " + exam_id;
        return teacherDatabaseCommends(sql);
    }

    public List<Teacher> getAllWithSameNameAndSurname(String name, String surname) throws SQLException {
        String sql = "SELECT * FROM teacher WHERE teacher_name = '" + name + "' AND teacher_surname = '" + surname + "'";
        return teacherDatabaseCommends(sql);
    }

    public List<Teacher> getAllScheduleTeachers(int class_id) throws SQLException {
        String sql = "SELECT teacher.* FROM teacher INNER JOIN plan ON teacher.teacher_id = plan.teacher_id INNER JOIN " +
                "class ON plan.class_id = class.class_id WHERE class.class_id = " + class_id + " ORDER BY " +
                "teacher.teacher_surname, teacher.teacher_name";
        return teacherDatabaseCommends(sql);
    }

    public List<Teacher> getSchedulePlanLessonTeacher(int plan_id) throws SQLException {
        String sql = "SELECT teacher.* FROM plan INNER JOIN teacher ON plan.teacher_id = teacher.teacher_id WHERE " +
                "plan.plan_id = " + plan_id;
        return teacherDatabaseCommends(sql);
    }

    public List<Teacher> getBySubjectName(String subject_name) throws SQLException {
        String sql = "SELECT teacher.* FROM teacher INNER JOIN school_subject ON " +
                "teacher.subject_id = school_subject.subject_id WHERE school_subject.subject_name = '" + subject_name +
                "' ORDER BY teacher.teacher_surname, teacher.teacher_name";
        return teacherDatabaseCommends(sql);
    }

    @Override
    public void save(Teacher teacher) throws SQLException {
        int teacher_id = teacher.getTeacher_id();
        int user_id = teacher.getUser_id();
        int school_id = teacher.getSchool_id();
        int subject_id = teacher.getSubject_id();
        String teacher_name = teacher.getTeacher_name();
        String teacher_surname = teacher.getTeacher_surname();
        String sql = "INSERT INTO teacher(teacher_id, user_id, school_id, subject_id, teacher_name, teacher_surname) VALUES("
                + teacher_id + ", " + user_id + ", " + school_id + ", " + subject_id +", '" + teacher_name + "', '"
                + teacher_surname + "')";
        statement.executeUpdate(sql);
    }

    @Override
    public void update(Teacher teacher) throws SQLException {
        int new_teacher_id = teacher.getTeacher_id();
        int new_user_id = teacher.getUser_id();
        int new_school_id = teacher.getSchool_id();
        int new_subject_id = teacher.getSubject_id();
        String new_teacher_name = teacher.getTeacher_name();
        String new_teacher_surname = teacher.getTeacher_surname();
        String sql = "UPDATE teacher SET teacher_id = " + new_teacher_id + ", user_id = " + new_user_id + ", school_id = "
                + new_school_id + ", subject_id = " + new_subject_id +", teacher_name = '" + new_teacher_name +
                "', teacher_surname = '" + new_teacher_surname + "' WHERE teacher_id = " + new_teacher_id;
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
            int subject_id = resultSet.getInt(4);
            String teacher_name = resultSet.getString(5);
            String teacher_surname = resultSet.getString(6);
            teachers.add(new Teacher(teacher_id, user_id, school_id, subject_id, teacher_name, teacher_surname));
        }
        return teachers;
    }
}
