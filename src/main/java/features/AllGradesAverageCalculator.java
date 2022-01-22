package features;

import DAO.DAOFinalGrade;
import model.FinalGrade;
import model.Student;

import java.sql.SQLException;
import java.util.List;

public class AllGradesAverageCalculator {
    public static double calculate(Student student) throws SQLException {
        List<FinalGrade> grades = new DAOFinalGrade().getAllStudentsGrades(student.getStudent_id());

        double meter = 0.0;

        for (FinalGrade grade: grades) {
            meter += grade.getGrade_value();
        }

        if (grades.size() == 0) return 0.0d;
        else return Math.round((meter / grades.size()) * 100.0) / 100.0;
    }
}
