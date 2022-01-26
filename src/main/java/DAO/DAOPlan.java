package DAO;

import database.DBConnection;
import model.Mark;
import model.Plan;
import model.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOPlan implements DAO<Plan> {
    Connection connection = DBConnection.getConnection();
    Statement statement = connection.createStatement();

    public DAOPlan() throws SQLException {
    }

    @Override
    public List<Plan> get(int id) throws SQLException {
        String sql = "SELECT * FROM plan WHERE plan_id = " + id;
        return planDatabaseCommends(sql);
    }

    @Override
    public List<Plan> getAll() throws SQLException {
        String sql = "SELECT * FROM plan";
        return planDatabaseCommends(sql);
    }

    public List<Plan> getAbsencePlanLesson(int absence_id) throws SQLException {
        String sql = "SELECT plan.* FROM plan INNER JOIN lesson ON plan.plan_id = lesson.plan_id INNER JOIN absences ON" +
                " absences.lesson_id = lesson.lesson_id WHERE absences.absence_id = " + absence_id;
        return planDatabaseCommends(sql);
    }

    public List<Plan> getTeachersPlanLessonsFromWeekDay(int teacher_id, String week_day) throws SQLException {
        String sql = "SELECT plan.* FROM plan INNER JOIN teacher ON plan.teacher_id = teacher.teacher_id WHERE" +
                " teacher.teacher_id = " + teacher_id + " AND plan.day = '" + week_day + "'";
        return planDatabaseCommends(sql);
    }

    public List<Plan> getStudentsPlanLessonsFromWeekDay(int student_id, String week_day) throws SQLException {
        String sql = "SELECT plan.* FROM plan INNER JOIN class ON plan.class_id = class.class_id INNER JOIN student ON" +
                " class.class_id = student.class_id WHERE student.student_id = " + student_id +" AND plan.day = '" +
                week_day + "'";
        return planDatabaseCommends(sql);
    }

    @Override
    public void save(Plan plan) throws SQLException {
        int plan_id = plan.getPlan_id();
        int class_id = plan.getClass_id();
        int teacher_id = plan.getTeacher_id();
        String day = plan.getDay();
        String start_hour = plan.getStart_hour();
        String finish_hour = plan.getFinish_hour();
        int classroom_number = plan.getClassroom_number();
        String sql = "INSERT INTO plan(plan_id, class_id, teacher_id, day, start_hour, finish_hour, classroom_number) VALUES("
                + plan_id +", " + class_id + ", " + teacher_id + ", '" + day + "', '" + start_hour + "', '" + finish_hour +
                "', " + classroom_number + ")";
        statement.executeUpdate(sql);
    }

    @Override
    public void update(Plan plan) throws SQLException {
        int new_plan_id = plan.getPlan_id();
        int new_class_id = plan.getClass_id();
        int new_teacher_id = plan.getTeacher_id();
        String new_day = plan.getDay();
        String new_start_hour = plan.getStart_hour();
        String new_finish_hour = plan.getFinish_hour();
        int new_classroom_number = plan.getClassroom_number();
        String sql = "UPDATE plan SET plan_id = " + new_plan_id + ", class_id = " + new_class_id + ", teacher_id = " +
                new_teacher_id + ", day = '" + new_day + "', start_hour = '" + new_start_hour + "', finish_hour = '" +
                new_finish_hour + "', classroom_number = " + new_classroom_number + " WHERE plan_id = " + new_plan_id;
        statement.executeUpdate(sql);
    }

    @Override
    public void delete(Plan plan) throws SQLException {
        int plan_id = plan.getPlan_id();
        String sql = "DELETE FROM plan WHERE plan_id = " + plan_id;
        statement.executeUpdate(sql);
    }

    private List<Plan> planDatabaseCommends(String sql) throws SQLException {
        ResultSet resultSet = statement.executeQuery(sql);
        List<Plan> plans = new ArrayList<>();
        while (resultSet.next()) {
            int plan_id = resultSet.getInt(1);
            int class_id = resultSet.getInt(2);
            int teacher_id = resultSet.getInt(3);
            String day = resultSet.getString(4);
            String start_hour = resultSet.getString(5);
            String finish_hour = resultSet.getString(6);
            int classroom_number = resultSet.getInt(7);
            plans.add(new Plan(plan_id, class_id, teacher_id, day, start_hour, finish_hour, classroom_number));
        }
        return plans;
    }
}
