package DAO;

import database.DBConnection;
import model.FinalGrade;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOFinalGrade implements DAO<FinalGrade> {
    Connection connection = DBConnection.getConnection();
    Statement statement = connection.createStatement();

    public DAOFinalGrade() throws SQLException {
    }

    @Override
    public List<FinalGrade> get(int id) throws SQLException {
        String sql = "SELECT * FROM final_grade WHERE grade_id = " + id;
        return finalGradeDatabaseCommends(sql);
    }

    @Override
    public List<FinalGrade> getAll() throws SQLException {
        String sql = "SELECT * FROM final_grade";
        return finalGradeDatabaseCommends(sql);
    }

    public List<FinalGrade> getStudentFinalGradeFromSuitableSubject(int student_id, int subject_id) throws SQLException {
        String sql = "SELECT final_grade.* FROM student INNER JOIN final_grade ON student.student_id = final_grade.student_id " +
                "INNER JOIN school_subject ON final_grade.subject_id = school_subject.subject_id WHERE" +
                " student.student_id = " + student_id + " AND school_subject.subject_id = " + subject_id;
        return finalGradeDatabaseCommends(sql);
    }

    @Override
    public void save(FinalGrade finalGrade) throws SQLException {
        int grade_id = finalGrade.getGrade_id();
        int student_id = finalGrade.getStudent_id();
        int subject_id = finalGrade.getSubject_id();
        int grade_value = finalGrade.getGrade_value();
        String sql = "INSERT INTO final_grade(grade_id, student_id, subject_id, grade_value) VALUES(" + grade_id + ", "
                + student_id + ", " + subject_id + ", " + grade_value + ")";
        statement.executeUpdate(sql);
    }

    @Override
    public void update(FinalGrade finalGrade) throws SQLException {
        int new_grade_id = finalGrade.getGrade_id();
        int new_student_id = finalGrade.getStudent_id();
        int new_subject_id = finalGrade.getSubject_id();
        int new_grade_value = finalGrade.getGrade_value();
        String sql = "UPDATE final_grade SET grade_id = " + new_grade_id + ", student_id = " + new_student_id + ", subject_id = "
                + new_subject_id + ", grade_value = " + new_grade_value + " WHERE grade_id = " + new_grade_id;
        statement.executeUpdate(sql);
    }

    @Override
    public void delete(FinalGrade finalGrade) throws SQLException {
        int grade_id = finalGrade.getGrade_id();
        String sql = "DELETE FROM final_grade WHERE grade_id = " + grade_id;
        statement.executeUpdate(sql);
    }

    private List<FinalGrade> finalGradeDatabaseCommends(String sql) throws SQLException{
        ResultSet resultSet = statement.executeQuery(sql);
        List<FinalGrade> finalGrades = new ArrayList<>();
        while (resultSet.next()) {
            int grade_id = resultSet.getInt(1);
            int student_id = resultSet.getInt(2);
            int subject_id = resultSet.getInt(3);
            int grade_value = resultSet.getInt(4);
            finalGrades.add(new FinalGrade(grade_id, student_id, subject_id, grade_value));
        }
        return finalGrades;
    }
}
