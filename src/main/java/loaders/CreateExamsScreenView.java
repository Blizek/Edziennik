package loaders;

import DAO.*;
import com.jfoenix.controls.JFXButton;
import controller.DeleteExamController;
import controller.ManageExamController;
import features.FormatDay;
import features.GetStudent;
import features.GetUser;
import features.GetWeekDay;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import locations.FilesLocations;
import model.*;
import routings.DeleteExamMain;
import routings.ManageExamMain;
import variables.MainText;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CreateExamsScreenView {
    public static LocalDate date;
    public static int classID;

    public static void view(AnchorPane mainAnchor, ScrollPane scroll, AnchorPane scrollAnchor) throws SQLException {
        date = ExamsManageScreenView.date;

        scrollAnchor.getChildren().clear();

        Student student;
        Teacher teacher = new Teacher(-1, -1, -1, -1, "", "");
        User user = GetUser.get();

        String planOwnerNameAndSurname;
        List<Exams> thisDayExams;

        if (!user.getUser_role().equals("TEACHER")) {
            if (user.getUser_role().equals("GUARDIAN")) student = GetStudent.getForGuardian(user.getUser_id());
            else student = GetStudent.getForStudent(user.getUser_id());
            planOwnerNameAndSurname = student.getStudent_name() + " " + student.getStudent_surname();
            classID = student.getClass_id();
        } else {
            teacher = new DAOTeacher().getByUserID(user.getUser_id()).get(0);
            planOwnerNameAndSurname = teacher.getTeacher_name() + " " + teacher.getTeacher_surname();
        }

        thisDayExams = new DAOExams().getAllTodayClassExams(classID, date.toString());

        JFXButton refreshButton = CreateRightCornerButton.create(38, "Refresh");
        EventHandler<MouseEvent> refresh = e -> {
            try {
                mainAnchor.getChildren().remove(refreshButton);
                view(mainAnchor, scroll, scrollAnchor);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        };
        refreshButton.addEventHandler(MouseEvent.MOUSE_CLICKED, refresh);
        mainAnchor.getChildren().add(refreshButton);

        MainText.main.setText(planOwnerNameAndSurname + "'s exams");

        String dayAndDate = GetWeekDay.get(date) + " " + FormatDay.formatDate(date.toString());

        Text dayAndDateText = new Text(313, 40, dayAndDate);
        dayAndDateText.setFont(Font.font("Calibri", FontWeight.BOLD, 35));
        dayAndDateText.setFill(Color.rgb(60, 86, 188));
        dayAndDateText.setWrappingWidth(359);
        dayAndDateText.setTextAlignment(TextAlignment.CENTER);
        scrollAnchor.getChildren().add(dayAndDateText);

        ImageView leftArrow = CreateArrows.create(257, FilesLocations.LEFT_ARROW_ICON);
        EventHandler<MouseEvent> dayMinusOne = e -> {
            date = date.minusDays(1);
            ExamsManageScreenView.date = date;
            try {
                mainAnchor.getChildren().remove(refreshButton);
                view(mainAnchor, scroll, scrollAnchor);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        };
        leftArrow.addEventHandler(MouseEvent.MOUSE_CLICKED, dayMinusOne);
        scrollAnchor.getChildren().add(leftArrow);

        ImageView rightArrow = CreateArrows.create(678, FilesLocations.RIGHT_ARROW_ICON);
        EventHandler<MouseEvent> dayPlusOne = e -> {
            date = date.plusDays(1);
            ExamsManageScreenView.date = date;
            try {
                mainAnchor.getChildren().remove(refreshButton);
                view(mainAnchor, scroll, scrollAnchor);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        };
        rightArrow.addEventHandler(MouseEvent.MOUSE_CLICKED, dayPlusOne);
        scrollAnchor.getChildren().add(rightArrow);

        int YPosition = 78;

        if (user.getUser_role().equals("TEACHER")) {
            YPosition = 128;
            JFXButton getBackButton = CreateRightCornerButton.create(20, "Get back to classes");
            EventHandler<MouseEvent> getBack = e -> {
                try {
                    ExamsManageScreenView.view(mainAnchor, scroll, scrollAnchor, date);
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            };
            getBackButton.addEventHandler(MouseEvent.MOUSE_CLICKED, getBack);
            scrollAnchor.getChildren().add(getBackButton);

            JFXButton addNoteButton = CreateRightCornerButton.create(65, "Add exam");
            EventHandler<MouseEvent> addNote = e -> {
                try {
                    Exams exam = new Exams(-1, classID, -1, "", Timestamp.valueOf(LocalDateTime.now()));
                    ManageExamController.editing = false;
                    ManageExamController.exam = exam;
                    new ManageExamMain().runThis();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            };
            addNoteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, addNote);
            scrollAnchor.getChildren().add(addNoteButton);
        }

        for (Exams thisDayExam : thisDayExams) {
            CreateLine.create(scrollAnchor, YPosition);

            AnchorPane examPane = new AnchorPane();
            examPane.setLayoutX(33);
            examPane.setLayoutY(YPosition);
            examPane.setPrefSize(919, 74);
            examPane.setId(Integer.toString(thisDayExam.getExam_id()));

            Plan actualPlanLesson = new DAOPlan().getExamPlanLesson(thisDayExam.getPlan_id()).get(0);

            String subjectName = new DAOSchoolSubject().getLessonNameFromExam(thisDayExam.getPlan_id()).get(0).getSubject_name();
            String className = new DAOClass().get(thisDayExam.getClass_id()).get(0).getClass_name();
            String lessonTime = actualPlanLesson.getStart_hour() + " - " + actualPlanLesson.getFinish_hour();
            int classroomNumber = actualPlanLesson.getClassroom_number();

            String lessonInformation = subjectName + ", " + className + ", " + lessonTime + ", " + classroomNumber;

            CreateSchedulePlanLessonText.createText(22, lessonInformation, examPane);
            CreateSchedulePlanLessonText.createText(490, thisDayExam.getExam_description(), examPane);

            Teacher thisExamTeacher = new DAOTeacher().getExamTeacher(thisDayExam.getExam_id()).get(0);

            if (thisExamTeacher.getTeacher_id() == teacher.getTeacher_id()) {
                JFXButton editExamButton = createManageExamButton(693, "Edit");
                EventHandler<MouseEvent> editExam = e -> {
                    try {
                        Exams exam = new DAOExams().get(Integer.parseInt(examPane.getId())).get(0);
                        ManageExamController.editing = true;
                        ManageExamController.exam = exam;
                        new ManageExamMain().runThis();
                    } catch (SQLException | IOException exception) {
                        exception.printStackTrace();
                    }
                };
                editExamButton.addEventHandler(MouseEvent.MOUSE_CLICKED, editExam);
                examPane.getChildren().add(editExamButton);

                JFXButton deleteExamButton = createManageExamButton(805, "Delete");
                EventHandler<MouseEvent> deleteExam = e -> {
                    try {
                        DeleteExamController.exam = new DAOExams().get(Integer.parseInt(examPane.getId())).get(0);
                        new DeleteExamMain().runThis();
                    } catch (SQLException | IOException exception) {
                        exception.printStackTrace();
                    }
                };
                deleteExamButton.addEventHandler(MouseEvent.MOUSE_CLICKED, deleteExam);
                examPane.getChildren().add(deleteExamButton);
            }

            scrollAnchor.getChildren().add(examPane);

            YPosition += 74;
        }

        CreateLine.create(scrollAnchor, YPosition);

        if (YPosition >= 544) {
            scrollAnchor.setPrefHeight(YPosition + 124);
            scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        }
    }

    private static JFXButton createManageExamButton(int XPosition, String textValue) {
        JFXButton button = new JFXButton();
        button.setLayoutX(XPosition);
        button.setLayoutY(19);
        button.setPrefSize(106, 37);
        button.setText(textValue);
        button.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        button.setTextFill(Color.rgb(255, 255, 255));
        button.setStyle("-fx-background-color: #3c56bc; -fx-background-radius: 10; -fx-border-radius: 10;");

        return button;
    }
}
