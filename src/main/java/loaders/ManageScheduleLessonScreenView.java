package loaders;

import DAO.DAOClass;
import DAO.DAOPlan;
import DAO.DAOSchoolSubject;
import DAO.DAOTeacher;
import com.jfoenix.controls.JFXButton;
import controller.ChooseTeacherController;
import enumTypes.DatabaseTablesName;
import features.GetMaxID;
import features.GetNameAndSurnameByTableID;
import features.GetSubjectID;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Class;
import model.Plan;
import model.SchoolSubject;
import model.Teacher;
import routings.ChooseTeacherMain;
import variables.MainText;
import variables.SubjectTeacherID;

import java.io.IOException;
import java.sql.SQLException;

public class ManageScheduleLessonScreenView {
    public static boolean editing;
    public static int planID;

    public static void view(AnchorPane mainAnchor, ScrollPane scroll, AnchorPane scrollAnchor, int classID, String weekDay) throws SQLException {
        scroll.setVvalue(0);
        scrollAnchor.setPrefHeight(544);

        scrollAnchor.getChildren().clear();

        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        Class thisClass = new DAOClass().get(classID).get(0);

        MainText.main.setText("Manage schedule lesson");

        JFXButton getBackButton = CreateRightCornerButton.create(38, "Get back to schedule");
        EventHandler<MouseEvent> getBack = e -> {
            try {
                mainAnchor.getChildren().remove(getBackButton);
                SetClassSchedulePlanScreenView.view(mainAnchor, scroll, scrollAnchor, classID);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        };
        getBackButton.addEventHandler(MouseEvent.MOUSE_CLICKED, getBack);
        mainAnchor.getChildren().add(getBackButton);

        createPartText(42, 60, "Subject: ", scrollAnchor);
        TextField subjectTextField = createTextField(155, 28, 500);
        scrollAnchor.getChildren().add(subjectTextField);
        createCustomLine(295, 197, -140, -123, 360, -123, scrollAnchor);

        createPartText(42, 119, "Teacher: ", scrollAnchor);
        TextField teacherTextField = createTextField(155, 87, 613);
        teacherTextField.setEditable(false);
        scrollAnchor.getChildren().add(teacherTextField);
        createCustomLine(295, 197, -140, -64, 473, -64, scrollAnchor);

        createPartText(42, 173, "Start hour:", scrollAnchor);
        TextField startHourTextField = createTextField(180, 141, 222);
        scrollAnchor.getChildren().add(startHourTextField);
        createCustomLine(285, 187, -105, 0, 117, 0, scrollAnchor);

        createPartText(505, 173, "Finish hour: ", scrollAnchor);
        TextField finishHourTextField = createTextField(655, 141, 222);
        scrollAnchor.getChildren().add(finishHourTextField);
        createCustomLine(295, 197, 360, -10, 582, -10, scrollAnchor);

        createPartText(46, 238, "Week day: ", scrollAnchor);
        TextField weekDayTextField = createTextField(185, 208, 426);
        weekDayTextField.setText(weekDay);
        weekDayTextField.setEditable(false);
        scrollAnchor.getChildren().add(weekDayTextField);
        createCustomLine(295, 197, -106, 55, 311, 55, scrollAnchor);

        createPartText(46, 315, "Classroom number: ", scrollAnchor);
        TextField classroomNumberTextField = createTextField(291, 283, 222);
        scrollAnchor.getChildren().add(classroomNumberTextField);
        createCustomLine(305, 207, -14, 122, 208, 122, scrollAnchor);

        if (editing) {
            Plan schedulePlan = new DAOPlan().get(planID).get(0);
            SchoolSubject subject = new DAOSchoolSubject().getLessonNameFromPlan(schedulePlan.getPlan_id()).get(0);
            Teacher teacher = new DAOTeacher().getSchedulePlanLessonTeacher(schedulePlan.getPlan_id()).get(0);

            subjectTextField.setText(subject.getSubject_name());
            teacherTextField.setText(GetNameAndSurnameByTableID.getTeacher(teacher.getTeacher_id()));
            startHourTextField.setText(schedulePlan.getStart_hour());
            finishHourTextField.setText(schedulePlan.getFinish_hour());
            classroomNumberTextField.setText(Integer.toString(schedulePlan.getClassroom_number()));
        }

        JFXButton refreshButton = CreateRightCornerButton.create(2, "Refresh");
        EventHandler<MouseEvent> refresh = e -> {
            try {
                mainAnchor.getChildren().remove(getBackButton);
                if (SubjectTeacherID.getID() == -1) teacherTextField.setText("");
                else teacherTextField.setText(GetNameAndSurnameByTableID.getTeacher(SubjectTeacherID.getID()));
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        };
        refreshButton.addEventHandler(MouseEvent.MOUSE_CLICKED, refresh);
        scrollAnchor.getChildren().add(refreshButton);

        JFXButton setTeacherButton = CreateRightCornerButton.create(92, "Set teacher");
        EventHandler<MouseEvent> set = e -> {
            try {
                ChooseTeacherController.subjectName = subjectTextField.getText();
                new ChooseTeacherMain().runThis();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        };
        setTeacherButton.addEventHandler(MouseEvent.MOUSE_CLICKED, set);
        scrollAnchor.getChildren().add(setTeacherButton);

        JFXButton saveButton = CreateRightCornerButton.create(48, "Save");
        EventHandler<MouseEvent> save = e -> {
            if (GetSubjectID.get(subjectTextField.getText()) != -1) {
                if (SubjectTeacherID.getID() != -1) {
                    if (startHourTextField.getText().length() > 0 && finishHourTextField.getText().length() > 0) {
                        try {
                            int classroom = Integer.parseInt(classroomNumberTextField.getText());
                            if (editing) {
                                Plan updatedPlan = new DAOPlan().get(planID).get(0);
                                updatedPlan.setClass_id(classID);
                                updatedPlan.setTeacher_id(SubjectTeacherID.getID());
                                updatedPlan.setStart_hour(startHourTextField.getText());
                                updatedPlan.setFinish_hour(finishHourTextField.getText());
                                updatedPlan.setClassroom_number(classroom);
                                new DAOPlan().update(updatedPlan);
                                SetClassSchedulePlanScreenView.view(mainAnchor, scroll, scrollAnchor, classID);
                            } else {
                                Plan newPlan = new Plan(GetMaxID.get(DatabaseTablesName.PLAN) + 1,
                                        classID, SubjectTeacherID.getID(), weekDay, startHourTextField.getText(),
                                        finishHourTextField.getText(), classroom);
                                new DAOPlan().save(newPlan);
                                SetClassSchedulePlanScreenView.view(mainAnchor, scroll, scrollAnchor, classID);
                            }
                        } catch (Exception ignored) {
                        }
                    }
                }
            }
        };
        saveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, save);
        scrollAnchor.getChildren().add(saveButton);
    }


    private static void createPartText(int XPosition, int YPosition, String textValue, AnchorPane paneToAdd) {
        Text text = new Text(XPosition, YPosition, textValue);
        text.setFont(Font.font("Calibri", 30));
        text.setFill(Color.rgb(60, 86, 160));
        paneToAdd.getChildren().add(text);
    }

    private static TextField createTextField(int XPosition, int YPosition, int prefWidth) {
        TextField textField = new TextField();
        textField.setLayoutX(XPosition);
        textField.setLayoutY(YPosition);
        textField.setPrefSize(prefWidth, 46);
        textField.setFont(Font.font("Calibri", 25));
        textField.setStyle("-fx-background-color: white");

        return textField;
    }

    private static void createCustomLine(int XPosition, int YPosition, int startX, int startY, int endX, int endY, AnchorPane paneToAdd) {
        Line line = new Line();
        line.setLayoutX(XPosition);
        line.setLayoutY(YPosition);
        line.setStartX(startX);
        line.setStartY(startY);
        line.setEndX(endX);
        line.setEndY(endY);
        line.setStroke(Color.rgb(60, 86, 160));
        line.setStrokeWidth(2);

        paneToAdd.getChildren().add(line);
    }
}
