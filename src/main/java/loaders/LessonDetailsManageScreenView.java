package loaders;

import DAO.DAOAbsences;
import DAO.DAOLesson;
import DAO.DAOPlan;
import DAO.DAOSchoolSubject;
import com.jfoenix.controls.JFXButton;
import enumTypes.DatabaseTablesName;
import features.*;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.*;
import variables.MainText;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class LessonDetailsManageScreenView {
    public static void load(AnchorPane mainPane, ScrollPane scroll, AnchorPane scrollAnchor, String planID) throws SQLException {
        scroll.setVvalue(0);
        scrollAnchor.setPrefHeight(544);

        scrollAnchor.getChildren().clear();

        User user = GetUser.get();
        Plan planLesson = new DAOPlan().get(Integer.parseInt(planID)).get(0);
        SchoolSubject subject = new DAOSchoolSubject().getTeacherSubject(planLesson.getTeacher_id()).get(0);

        MainText.main.setText("Lesson: " + subject.getSubject_name() + " " + FormatDay.formatDate(LessonManageScreenView.date.toString())
        + " " + planLesson.getStart_hour() + " - " + planLesson.getFinish_hour());

        JFXButton getBackButton = CreateRightCornerButton.create(38, "Go back to schedule");
        EventHandler<MouseEvent> goBack = e -> {
            try {
                LocalDate lastDate = LessonManageScreenView.date;
                LessonManageScreenView.view(mainPane, scroll, scrollAnchor, lastDate);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        };
        getBackButton.addEventHandler(MouseEvent.MOUSE_CLICKED, goBack);
        mainPane.getChildren().add(getBackButton);

        if (!user.getUser_role().equals("TEACHER")) viewNotForTeacher(scroll, scrollAnchor, Integer.parseInt(planID), user);
        else viewForTeacher(mainPane, scroll, scrollAnchor, Integer.parseInt(planID));
    }

    private static void viewNotForTeacher(ScrollPane scroll, AnchorPane scrollAnchor, int planID, User user) throws SQLException {
        List<Lesson> thisLesson = new DAOLesson().getLessonByPlanIDAndDate(planID, LessonManageScreenView.date.toString());
        Plan planLesson = new DAOPlan().get(planID).get(0);

        Student student;

        if (user.getUser_role().equals("STUDENT")) student = GetStudent.getForStudent(user.getUser_id());
        else student = GetStudent.getForGuardian(user.getUser_id());

        String schoolSubjectName = new DAOSchoolSubject().getTeacherSubject(planLesson.getTeacher_id()).get(0).getSubject_name();
        String lessonTime = planLesson.getStart_hour() + " - " + planLesson.getFinish_hour();
        String teachersNameAndSurname = GetNameAndSurnameByTableID.getTeacher(planLesson.getTeacher_id());
        int lessonClassroom = planLesson.getClassroom_number();

        String lessonSubject;
        String studentPresence;

        if (thisLesson.size() == 0) {
            lessonSubject = "No data";
            studentPresence = "No data";
        } else {
            List<Absences> lessonPresence = new DAOAbsences().getStudentLessonPresence(thisLesson.get(0).getLesson_id(), student.getStudent_id());
            lessonSubject = thisLesson.get(0).getLesson_subject();
            if (lessonPresence.size() == 0) studentPresence = "No data";
            else {
                if (lessonPresence.get(0).isStudent_absence()) {
                    if (lessonPresence.get(0).isExcused_absence()) studentPresence = "Excused absence";
                    else studentPresence = "Unexcused absence";
                } else studentPresence = "Present";
            }
        }

        CreateLine.create(scrollAnchor, 40);

        createText(64, "Lesson subject: " + lessonSubject, scrollAnchor);
        createText(96, "Subject: " + schoolSubjectName, scrollAnchor);
        createText(128, "Lesson time: " + lessonTime, scrollAnchor);
        createText(160, "Teacher name: " + teachersNameAndSurname, scrollAnchor);
        createText(192, "Classroom number: " + lessonClassroom, scrollAnchor);
        createText(224, "Student presence: " + studentPresence, scrollAnchor);

        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    private static void viewForTeacher(AnchorPane mainAnchor, ScrollPane scroll, AnchorPane scrollAnchor, int planID) throws SQLException {
        List<Lesson> thisLesson = new DAOLesson().getLessonByPlanIDAndDate(planID, LessonManageScreenView.date.toString());
        Plan planLesson = new DAOPlan().get(planID).get(0);

        String schoolSubjectName = new DAOSchoolSubject().getTeacherSubject(planLesson.getTeacher_id()).get(0).getSubject_name();
        String lessonTime = planLesson.getStart_hour() + " - " + planLesson.getFinish_hour();
        String teachersNameAndSurname = GetNameAndSurnameByTableID.getTeacher(planLesson.getTeacher_id());
        int lessonClassroom = planLesson.getClassroom_number();

        String saveOrEditButtonText;
        boolean save;

        if (thisLesson.size() == 0) {
            saveOrEditButtonText = "Save";
            save = true;
        } else {
            saveOrEditButtonText = "Edit";
            save = false;
        }

        CreateLine.create(scrollAnchor, 50);

        createText(84, "Lesson subject: ", scrollAnchor);
        createText(116, "Subject: " + schoolSubjectName, scrollAnchor);
        createText(148, "Lesson time: " + lessonTime, scrollAnchor);
        createText(180, "Teacher name: " + teachersNameAndSurname, scrollAnchor);
        createText(212, "Classroom number: " + lessonClassroom, scrollAnchor);

        TextField lessonSubjectField = new TextField();
        lessonSubjectField.setLayoutX(234);
        lessonSubjectField.setLayoutY(59);
        lessonSubjectField.setPrefSize(716, 37);
        lessonSubjectField.setFont(Font.font("Calibri", 20));
        lessonSubjectField.setStyle("-fx-border-color: #3c56bc; -fx-background-radius: 10; -fx-border-radius: 10;");
        scrollAnchor.getChildren().add(lessonSubjectField);

        if (!save) lessonSubjectField.setText(thisLesson.get(0).getLesson_subject());

        JFXButton saveOrEditButton = CreateRightCornerButton.create(2, saveOrEditButtonText);
        EventHandler<MouseEvent> sendToDatabase = e -> {
            try {
                LocalDate date = LessonManageScreenView.date;
                if (save) {
                    Lesson lesson = new Lesson(GetMaxID.get(DatabaseTablesName.LESSON) + 1, planLesson.getTeacher_id(), planID,
                            lessonSubjectField.getText(), Timestamp.valueOf(LocalDateTime.of(date, LocalTime.now())));
                    new DAOLesson().save(lesson);
                } else {
                    Lesson lesson = thisLesson.get(0);
                    lesson.setLesson_subject(lessonSubjectField.getText());
                    new DAOLesson().update(lesson);
                }
                LessonManageScreenView.view(mainAnchor, scroll, scrollAnchor, date);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        };
        saveOrEditButton.addEventHandler(MouseEvent.MOUSE_CLICKED, sendToDatabase);
        scrollAnchor.getChildren().add(saveOrEditButton);
    }

    private static void createText(int YPosition, String textValue, AnchorPane paneToAdd) {
        Text text = new Text(71, YPosition, textValue);
        text.setFont(Font.font("Calibri", FontWeight.BOLD, 25));
        text.setFill(Color.rgb(60, 86, 188));
        paneToAdd.getChildren().add(text);
    }
}
