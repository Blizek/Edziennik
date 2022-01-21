package controller;

import DAO.DAOMark;
import model.Mark;
import routings.DeleteGradeMain;

import java.sql.SQLException;

public class DeleteGradeController {
    public static Mark mark;

    public void deleteGrade() throws SQLException {
        new DAOMark().delete(mark);
        new DeleteGradeMain().closeStageByDeletingGrade();
    }

    public void notDeleteGrade() {
        new DeleteGradeMain().closeStageByDeletingGrade();
    }
}
