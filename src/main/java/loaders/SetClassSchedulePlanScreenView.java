package loaders;

import DAO.DAOClass;
import DAO.DAOPlan;
import DAO.DAOSchoolSubject;
import DAO.DAOTeacher;
import com.jfoenix.controls.JFXButton;
import controller.DeleteLessonController;
import features.GetUser;
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
import model.Class;
import model.Plan;
import routings.DeleteLessonMain;
import variables.MainText;
import variables.SubjectTeacherID;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SetClassSchedulePlanScreenView {
    public static String day = "MONDAY";

    public static void view(AnchorPane mainAnchor, ScrollPane scroll, AnchorPane scrollAnchor, int classID) throws SQLException {
        scroll.setVvalue(0);
        scrollAnchor.setPrefHeight(544);

        scrollAnchor.getChildren().clear();
        mainAnchor.getChildren().clear();

        mainAnchor.getChildren().add(MainText.main);

        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        Class thisClass = new DAOClass().get(classID).get(0);

        MainText.main.setText(thisClass.getClass_name() + " schedule plan");

        JFXButton getBackButton = CreateRightCornerButton.create(38, "Get back to class");
        EventHandler<MouseEvent> getBack = e -> {
            try {
                mainAnchor.getChildren().remove(getBackButton);
                if (GetUser.get().getUser_role().equals("PRINCIPAL"))
                    ClassManageScreenView.view(mainAnchor, scroll, scrollAnchor, classID);
                else
                    YourClassManageScreenView.view(mainAnchor, scroll, scrollAnchor);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        };
        getBackButton.addEventHandler(MouseEvent.MOUSE_CLICKED, getBack);
        mainAnchor.getChildren().add(getBackButton);

        JFXButton refreshButton = CreateRightCornerButton.create(20, "Refresh");
        EventHandler<MouseEvent> refresh = e -> {
            try {
                mainAnchor.getChildren().remove(getBackButton);
                view(mainAnchor, scroll, scrollAnchor, classID);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        };
        refreshButton.addEventHandler(MouseEvent.MOUSE_CLICKED, refresh);
        scrollAnchor.getChildren().add(refreshButton);

        JFXButton addLessonButton = CreateRightCornerButton.create(65, "Add lesson");
        EventHandler<MouseEvent> addLesson = e -> {
            try {
                SubjectTeacherID.setID(-1);
                ManageScheduleLessonScreenView.editing = false;
                ManageScheduleLessonScreenView.view(mainAnchor, scroll, scrollAnchor, classID, day);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        };
        addLessonButton.addEventHandler(MouseEvent.MOUSE_CLICKED, addLesson);
        scrollAnchor.getChildren().add(addLessonButton);

        Text dayText = new Text(313, 40, day);
        dayText.setFont(Font.font("Calibri", FontWeight.BOLD, 35));
        dayText.setFill(Color.rgb(60, 86, 188));
        dayText.setWrappingWidth(359);
        dayText.setTextAlignment(TextAlignment.CENTER);
        scrollAnchor.getChildren().add(dayText);

        ImageView leftArrow = CreateArrows.create(257, FilesLocations.LEFT_ARROW_ICON);
        EventHandler<MouseEvent> dayMinusOne = e -> {
            previousDay();
            try {
                mainAnchor.getChildren().remove(refreshButton);
                view(mainAnchor, scroll, scrollAnchor, classID);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        };
        leftArrow.addEventHandler(MouseEvent.MOUSE_CLICKED, dayMinusOne);
        scrollAnchor.getChildren().add(leftArrow);

        ImageView rightArrow = CreateArrows.create(678, FilesLocations.RIGHT_ARROW_ICON);
        EventHandler<MouseEvent> dayPlusOne = e -> {
            nextDay();
            try {
                mainAnchor.getChildren().remove(refreshButton);
                view(mainAnchor, scroll, scrollAnchor, classID);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        };
        rightArrow.addEventHandler(MouseEvent.MOUSE_CLICKED, dayPlusOne);
        scrollAnchor.getChildren().add(rightArrow);

        List<Plan> classThisDayLessons = new DAOPlan().getThisDayClassLessons(classID, day);
        int YPosition = 110;

        CreateLine.create(scrollAnchor, YPosition);

        for (int i = 0; i < classThisDayLessons.size(); i++) {
            AnchorPane lessonPane = new AnchorPane();
            lessonPane.setLayoutX(33);
            lessonPane.setLayoutY(YPosition);
            lessonPane.setPrefSize(919, 74);
            lessonPane.setId(Integer.toString(classThisDayLessons.get(i).getPlan_id()));

            CreateSchedulePlanLessonText.createText(22, Integer.toString(i + 1), lessonPane);

            String subjectName = new DAOSchoolSubject().getTeacherSubject(classThisDayLessons.get(i).getTeacher_id()).get(0).getSubject_name();
            String className = new DAOClass().get(classThisDayLessons.get(i).getClass_id()).get(0).getClass_name();
            String lessonTime = classThisDayLessons.get(i).getStart_hour() + " - " + classThisDayLessons.get(i).getFinish_hour();
            int classroomNumber = classThisDayLessons.get(i).getClassroom_number();

            String lessonInformation = subjectName + ", " + className + ", " + lessonTime + ", " + classroomNumber;

            CreateSchedulePlanLessonText.createText(94, lessonInformation, lessonPane);

            JFXButton editButton = buttonSettings(693, "Edit");
            EventHandler<MouseEvent> edit = e-> {
                try {
                    SubjectTeacherID.setID(new DAOTeacher().getSchedulePlanLessonTeacher(Integer.parseInt(
                            lessonPane.getId())).get(0).getTeacher_id());
                    ManageScheduleLessonScreenView.editing = true;
                    ManageScheduleLessonScreenView.planID = Integer.parseInt(lessonPane.getId());
                    ManageScheduleLessonScreenView.view(mainAnchor, scroll, scrollAnchor, classID, day);
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            };
            editButton.addEventHandler(MouseEvent.MOUSE_CLICKED, edit);
            lessonPane.getChildren().add(editButton);

            JFXButton deleteButton = buttonSettings(805, "Delete");
            EventHandler<MouseEvent> delete = e -> {
                try {
                    DeleteLessonController.lesson = new DAOPlan().get(Integer.parseInt(lessonPane.getId())).get(0);
                    new DeleteLessonMain().runThis();
                } catch (IOException | SQLException ioException) {
                    ioException.printStackTrace();
                }
            };
            deleteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, delete);
            lessonPane.getChildren().add(deleteButton);

            scrollAnchor.getChildren().add(lessonPane);

            YPosition += 74;

            CreateLine.create(scrollAnchor, YPosition);
        }
    }

    private static void nextDay() {
        switch (day) {
            case "MONDAY" -> day = "TUESDAY";
            case "TUESDAY" -> day = "WEDNESDAY";
            case "WEDNESDAY" -> day = "THURSDAY";
            case "THURSDAY" -> day = "FRIDAY";
            case "FRIDAY" -> day = "MONDAY";
        }
    }

    private static void previousDay() {
        switch (day) {
            case "MONDAY" -> day = "FRIDAY";
            case "TUESDAY" -> day = "MONDAY";
            case "WEDNESDAY" -> day = "TUESDAY";
            case "THURSDAY" -> day = "WEDNESDAY";
            case "FRIDAY" -> day = "THURSDAY";
        }
    }

    private static JFXButton buttonSettings(int XPosition, String textValue) {
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
