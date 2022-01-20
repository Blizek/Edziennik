package DAO;

import database.DBConnection;
import model.Class;
import model.Teacher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOClass implements DAO<Class> {
    Connection connection = DBConnection.getConnection();
    Statement statement = connection.createStatement();

    public DAOClass() throws SQLException {
    }

    @Override
    public List<Class> get(int id) throws SQLException {
        String sql = "SELECT * FROM class WHERE class_id = " + id;
        return classDatabaseCommends(sql);
    }

    @Override
    public List<Class> getAll() throws SQLException {
        String sql = "SELECT * FROM class";
        return classDatabaseCommends(sql);
    }

    public List<Class> getBySupervisingTeacher(int class_supervising_teacher_id) throws SQLException {
        String sql = "SELECT * FROM class WHERE class_supervising_teacher = " + class_supervising_teacher_id;
        return classDatabaseCommends(sql);
    }

    public List<Class> getByClassName(String class_name) throws SQLException {
        String sql = "SELECT * FROM class WHERE class_name = '" + class_name + "'";
        return classDatabaseCommends(sql);
    }

    public List<Class> getAllTeacherLearningClasses(int teacher_id) throws SQLException {
        String sql = "SELECT class.* FROM class INNER JOIN plan ON plan.class_id = class.class_id INNER JOIN teacher ON " +
                "teacher.teacher_id = plan.teacher_id WHERE teacher.teacher_id = " + teacher_id;
        return classDatabaseCommends(sql);
    }

    public boolean checkIfItIsClassSupervisingTeacher(int user_id) throws SQLException {
        Teacher lookingTeacher = new DAOTeacher().getByUserID(user_id).get(0);
        List<Class> teachersClasses = getBySupervisingTeacher(lookingTeacher.getTeacher_id());
        return teachersClasses.size() > 0;
    }

    @Override
    public void save(Class daoClass) throws SQLException {
        int class_id = daoClass.getClass_id();
        int class_supervising_teacher_id = daoClass.getClass_supervising_teacher();
        String class_name = daoClass.getClass_name();
        String sql = "INSERT INTO class(class_id, class_supervising_teacher, class_name) VALUES(" + class_id + ", " + class_supervising_teacher_id
                + ", '" + class_name + "')";
        statement.executeUpdate(sql);
    }

    @Override
    public void update(Class daoClass) throws SQLException {
        int new_class_id = daoClass.getClass_id();
        String new_class_name = daoClass.getClass_name();
        String sql = "UPDATE class SET class_id = " + new_class_id + ", class_name = '" + new_class_name
                + "' WHERE class_id = " + new_class_id;
        statement.executeUpdate(sql);
    }

    @Override
    public void delete(Class daoClass) throws SQLException {
        int class_id = daoClass.getClass_id();
        String sql = "DELETE FROM class WHERE class_id = " + class_id;
        statement.executeUpdate(sql);
    }

    private List<Class> classDatabaseCommends(String sql) throws SQLException {
        ResultSet resultSet = statement.executeQuery(sql);
        List<Class> classes = new ArrayList<>();
        while (resultSet.next()) {
            int class_id = resultSet.getInt(1);
            int class_supervising_teacher = resultSet.getInt(2);
            String class_name = resultSet.getString(3);
            classes.add(new Class(class_id, class_supervising_teacher, class_name));
        }
        return classes;
    }
}
