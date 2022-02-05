package controller;

import DAO.DAOClass;
import DAO.DAOExams;
import DAO.DAOPlan;
import DAO.DAOSchoolSubject;
import enumTypes.DatabaseTablesName;
import features.FormatDay;
import features.GetMaxID;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import loaders.CreateExamsScreenView;
import model.Exams;
import model.Plan;
import routings.ChoosePlanLessonForExamMain;
import routings.ManageExamMain;
import variables.PlanIDForExam;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class ManageExamController {
    @FXML
    TextField examDateTextField, examScheduledLessonTextField, examDescriptionTextField;

    @FXML
    Text scheduledLessonError, examDescriptionError;

    public static Exams exam;
    public static boolean editing;

    public static boolean someError = false;

    public void initialize() throws SQLException {
        if (editing) PlanIDForExam.setID(exam.getPlan_id());
        else PlanIDForExam.setID(-1);
        fillTextFields(true);
    }

    public void selectScheduledLesson() throws IOException {
        new ChoosePlanLessonForExamMain().runThis();
    }

    public void refresh() throws SQLException {
        fillTextFields(false);
    }

    public void submit() throws SQLException {
        if (PlanIDForExam.getID() == -1) {
            scheduledLessonError.setVisible(true);
            someError = true;
        } else {
            scheduledLessonError.setVisible(false);
        }

        if (examDescriptionTextField.getText().length() > 100) {
            examDescriptionError.setVisible(true);
            someError = true;
        } else {
            examDescriptionError.setVisible(false);
        }

        if (!someError) {
            exam.setExam_description(examDescriptionTextField.getText());
            if (editing) {
                exam.setPlan_id(PlanIDForExam.getID());
                new DAOExams().update(exam);
            } else {
                exam.setExam_id(GetMaxID.get(DatabaseTablesName.EXAMS) + 1);
                exam.setPlan_id(PlanIDForExam.getID());
                exam.setExam_date(Timestamp.valueOf(LocalDateTime.of(CreateExamsScreenView.date, LocalTime.now())));
                new DAOExams().save(exam);
            }
            new ManageExamMain().closeStageByAddingExam();
        }
    }

    private void fillTextFields(boolean editDescription) throws SQLException {
        String examDate = FormatDay.formatDate(exam.getExam_date().toString());

        String lessonInformation= "";

        List<Plan> planLessons = new DAOPlan().get(PlanIDForExam.getID());

        if (planLessons.size() > 0) {
            Plan actualPlanLesson = planLessons.get(0);

            String subjectName = new DAOSchoolSubject().getLessonNameFromPlan(PlanIDForExam.getID()).get(0).getSubject_name();
            String className = new DAOClass().get(exam.getClass_id()).get(0).getClass_name();
            String lessonTime = actualPlanLesson.getStart_hour() + " - " + actualPlanLesson.getFinish_hour();
            int classroomNumber = actualPlanLesson.getClassroom_number();

            lessonInformation = subjectName + ", " + className + ", " + lessonTime + ", " + classroomNumber;
        }

        examDateTextField.setText(examDate);
        examScheduledLessonTextField.setText(lessonInformation);

        if (editDescription) examDescriptionTextField.setText(exam.getExam_description());
    }
}
