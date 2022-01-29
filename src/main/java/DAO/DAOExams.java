package DAO;

import database.DBConnection;
import model.Exams;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOExams implements DAO<Exams> {
    Connection connection = DBConnection.getConnection();
    Statement statement = connection.createStatement();

    public DAOExams() throws SQLException {
    }

    @Override
    public List<Exams> get(int id) throws SQLException {
        String sql = "SELECT * FROM exams WHERE exam_id = " + id;
        return examsDatabaseCommends(sql);
    }

    @Override
    public List<Exams> getAll() throws SQLException {
        String sql = "SELECT * FROM exams";
        return examsDatabaseCommends(sql);
    }

    public List<Exams> getAllTodayClassExams(int class_id, String exam_date) throws SQLException {
        String sql = "SELECT exams.* FROM plan INNER JOIN exams ON plan.plan_id = exams.plan_id INNER JOIN class ON " +
                "exams.class_id = class.class_id WHERE class.class_id = " + class_id + " AND " +
                "exams.exam_date = '" + exam_date + "'";
        return examsDatabaseCommends(sql);
    }

    @Override
    public void save(Exams exams) throws SQLException {
        int exam_id = exams.getExam_id();
        int class_id = exams.getClass_id();
        int plan_id = exams.getPlan_id();
        String exam_description = exams.getExam_description();
        String exam_date = exams.getExam_date().toString().substring(0, 10) + " 00:00:00";
        String sql = "INSERT INTO exams(exam_id, class_id, plan_id, exam_description, exam_date) VALUES(" + exam_id + ", "
                + class_id + ", " + plan_id + ", '" + exam_description + "', '" + exam_date + "')";
        statement.executeUpdate(sql);
    }

    @Override
    public void update(Exams exams) throws SQLException {
        int new_exam_id = exams.getExam_id();
        int class_id = exams.getClass_id();
        int new_plan_id = exams.getPlan_id();
        String new_exam_description = exams.getExam_description();
        String new_exam_date = exams.getExam_date().toString().substring(0, 19);
        String sql = "UPDATE exams SET exam_id = " + new_exam_id + ", class_id = " + class_id + ", plan_id = " + new_plan_id
                + ", exam_description = '" + new_exam_description + "', exam_date = '" + new_exam_date + "' WHERE exam_id = "
                + new_exam_id;
        statement.executeUpdate(sql);
    }

    @Override
    public void delete(Exams exams) throws SQLException {
        int exam_id = exams.getExam_id();
        String sql = "DELETE FROM exams WHERE exam_id = " + exam_id;
        statement.executeUpdate(sql);
    }

    private List<Exams> examsDatabaseCommends(String sql) throws SQLException {
        ResultSet resultSet = statement.executeQuery(sql);
        List<Exams> exams = new ArrayList<>();
        while (resultSet.next()) {
            int exam_id = resultSet.getInt(1);
            int class_id = resultSet.getInt(2);
            int plan_id = resultSet.getInt(3);
            String exam_description = resultSet.getString(4);
            Timestamp exam_date = resultSet.getTimestamp(5);
            exams.add(new Exams(exam_id, class_id, plan_id, exam_description, exam_date));
        }
        return exams;
    }
}
