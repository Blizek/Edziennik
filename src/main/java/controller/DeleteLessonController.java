package controller;

import DAO.DAOPlan;
import model.Plan;
import routings.DeleteLessonMain;

import java.sql.SQLException;

public class DeleteLessonController {
    public static Plan lesson;

    public void deleteLesson() throws SQLException {
        new DAOPlan().delete(lesson);
        new DeleteLessonMain().closeStageByDeletingLesson();
    }

    public void notDeleteLesson() {
        new DeleteLessonMain().closeStageByDeletingLesson();
    }
}
