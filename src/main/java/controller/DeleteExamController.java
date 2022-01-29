package controller;

import DAO.DAOExams;
import model.Exams;
import routings.DeleteExamMain;

import java.sql.SQLException;

public class DeleteExamController {
    public static Exams exam;

    public void deleteExam() throws SQLException {
        new DAOExams().delete(exam);
        new DeleteExamMain().closeStageByDeletingExam();
    }

    public void notDeleteExam() {
        new DeleteExamMain().closeStageByDeletingExam();
    }
}
