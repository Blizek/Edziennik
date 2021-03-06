package DAO;

import database.DBConnection;
import model.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DAOStudent implements DAO<Student> {
    Connection connection = DBConnection.getConnection();
    Statement statement = connection.createStatement();

    public DAOStudent() throws SQLException {
    }

    @Override
    public List<Student> get(int id) throws SQLException {
        String sql = "SELECT * FROM student WHERE student_id = " + id;
        return studentDatabaseCommends(sql);
    }

    @Override
    public List<Student> getAll() throws SQLException {
        String sql = "SELECT * FROM student";
        return studentDatabaseCommends(sql);
    }

    public List<Student> getAllSorted() throws SQLException {
        String sql = "SELECT * FROM student ORDER BY student.student_surname, student.student_name";
        return studentDatabaseCommends(sql);
    }

    public List<Student> getBySchool(int school_id) throws SQLException {
        String sql = "SELECT * FROM student WHERE school_id = " + school_id;
        return studentDatabaseCommends(sql);
    }

    public List<Student> getByClass(int class_id) throws SQLException {
        String sql = "SELECT student.* FROM class INNER JOIN student ON student.class_id = class.class_id WHERE" +
                " class.class_id = " + class_id + " ORDER BY student.student_surname, student.student_name";
        return studentDatabaseCommends(sql);
    }

    public List<Student> getByUserID(int user_id) throws SQLException {
        String sql = "SELECT * FROM student WHERE user_id = " + user_id;
        return studentDatabaseCommends(sql);
    }

    public List<Student> getAllClassmatesFromLesson(int lesson_id) throws SQLException {
        String sql = "SELECT student.* FROM lesson INNER JOIN plan ON lesson.plan_id = plan.plan_id INNER JOIN student " +
                "ON plan.class_id = student.class_id WHERE lesson.lesson_id = " + lesson_id + " ORDER BY " +
                "student.student_surname, student.student_name";
        return studentDatabaseCommends(sql);
    }

    public List<Student> getAllWithSameNameAndSurname(String student_name, String student_surname) throws SQLException {
        String sql = "SELECT * FROM student WHERE student_name = '" + student_name + "' AND student_surname = '" +
                student_surname + "'";
        return studentDatabaseCommends(sql);
    }

    public List<Student> getByGuardianID(int guardian_id) throws SQLException {
        String sql = "SELECT student.* FROM student INNER JOIN guardian ON student.student_id = guardian.student_id " +
                "WHERE guardian.guardian_id = " + guardian_id;
        return studentDatabaseCommends(sql);
    }

    @Override
    public void save(Student student) throws SQLException {
        int student_id = student.getStudent_id();
        int user_id = student.getUser_id();
        int school_id = student.getSchool_id();
        int class_id = student.getClass_id();
        String student_name = student.getStudent_name();
        String student_surname = student.getStudent_surname();
        LocalDate student_date_of_birth = student.getStudent_date_of_birth();
        String sql = "INSERT INTO student(student_id, user_id, school_id, class_id, student_name, student_surname, student_date_of_birth) VALUES("
                + student_id + ", " + user_id + ", " + school_id + ", " + class_id + ", '" + student_name + "', '" + student_surname
                + "', '" + student_date_of_birth + "')";
        statement.executeUpdate(sql);
    }

    @Override
    public void update(Student student) throws SQLException {
        int new_student_id = student.getStudent_id();
        int new_user_id = student.getUser_id();
        int new_school_id = student.getSchool_id();
        int new_class_id = student.getClass_id();
        String new_student_name = student.getStudent_name();
        String new_student_surname = student.getStudent_surname();
        LocalDate new_student_date_of_birth = student.getStudent_date_of_birth();
        String sql = "UPDATE student SET student_id = " + new_student_id + ", user_id = " + new_user_id + ", school_id = "
                + new_school_id + ", class_id = " + new_class_id + ", student_name = '" + new_student_name + "', student_surname = '"
                + new_student_surname + "', student_date_of_birth = '" + new_student_date_of_birth + "' WHERE student_id = "
                + new_student_id;
        statement.executeUpdate(sql);
    }

    @Override
    public void delete(Student student) throws SQLException {
        int student_id = student.getStudent_id();
        String sql = "DELETE FROM student WHERE student_id = " + student_id;
        statement.executeUpdate(sql);
    }

    private List<Student> studentDatabaseCommends(String sql) throws SQLException {
        ResultSet resultSet = statement.executeQuery(sql);
        List<Student> students = new ArrayList<>();
        while (resultSet.next()) {
            int student_id = resultSet.getInt(1);
            int user_id = resultSet.getInt(2);
            int school_id = resultSet.getInt(3);
            int class_id = resultSet.getInt(4);
            String student_name = resultSet.getString(5);
            String student_surname = resultSet.getString(6);
            LocalDate student_date_of_birth = resultSet.getDate(7).toLocalDate();
            students.add(new Student(student_id, user_id, school_id, class_id, student_name, student_surname, student_date_of_birth));
        }
        return students;
    }
}
