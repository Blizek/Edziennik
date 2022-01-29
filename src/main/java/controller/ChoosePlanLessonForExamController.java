package controller;

import DAO.DAOPlan;
import DAO.DAOTeacher;
import features.GetUser;
import features.GetWeekDay;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import loaders.CreateExamsScreenView;
import loaders.ExamsManageScreenView;
import model.Exams;
import model.Plan;
import routings.ChoosePlanLessonForExamMain;
import routings.ManageExamMain;
import variables.PlanIDForExam;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ChoosePlanLessonForExamController {
    @FXML
    ScrollPane scroll;

    @FXML
    AnchorPane scrollAnchor;

    public void initialize() throws SQLException {
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        String dayOfWeek = GetWeekDay.get(ExamsManageScreenView.date);
        int teacherID = new DAOTeacher().getByUserID(GetUser.get().getUser_id()).get(0).getTeacher_id();
        int classID = CreateExamsScreenView.classID;

        List<Plan> lessons = new DAOPlan().getTodayTeacherLessonWithSuitableClass(classID, dayOfWeek, teacherID);
        int YPosition = 14;

        for (Plan lesson : lessons) {
            createPlanBox(YPosition, lesson);

            YPosition += 102;
        }

        if (YPosition >= 563) {
            scrollAnchor.setPrefHeight(YPosition + 152);
            scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        }
    }

    public void getBack() {
        new ChoosePlanLessonForExamMain().closeStageByChoosingDateOrGettingBack();
    }

    private void createPlanBox(int YPosition, Plan planLesson) {
        AnchorPane lessonPane = new AnchorPane();
        lessonPane.setLayoutX(13);
        lessonPane.setLayoutY(YPosition);
        lessonPane.setPrefSize(318, 88);
        lessonPane.setStyle("-fx-background-color: white; -fx-border-color: #3c56bc; -fx-border-width: 2;");
        lessonPane.setId(Integer.toString(planLesson.getPlan_id()));

        Text infoText = new Text(8, 51, planLesson.getDay() + " " + planLesson.getStart_hour() + " - " + planLesson.getFinish_hour());
        infoText.setFont(Font.font("Calibri", 25));
        infoText.setTextAlignment(TextAlignment.CENTER);
        infoText.setWrappingWidth(303);
        infoText.setFill(Color.rgb(60, 86, 188));
        lessonPane.getChildren().add(infoText);

        EventHandler<MouseEvent> setPlanID = e -> {
            PlanIDForExam.setID(Integer.parseInt(lessonPane.getId()));
            new ChoosePlanLessonForExamMain().closeStageByChoosingDateOrGettingBack();
        };
        lessonPane.addEventHandler(MouseEvent.MOUSE_CLICKED, setPlanID);

        scrollAnchor.getChildren().add(lessonPane);
    }
}
