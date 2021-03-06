package loaders;

import DAO.DAOTeacher;
import features.*;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.*;
import model.Class;
import variables.MainText;

import java.sql.SQLException;
import java.util.List;

public class MarksManageScreenView {
    public static void view(AnchorPane mainAnchor, ScrollPane scroll, AnchorPane scrollAnchor) throws SQLException {
        scroll.setVvalue(0);
        scrollAnchor.setPrefHeight(544);

        scrollAnchor.getChildren().clear();
        mainAnchor.getChildren().clear();

        mainAnchor.getChildren().add(MainText.main);

        String userRole = GetUser.get().getUser_role();

        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        if (!userRole.equals("TEACHER")) viewNotForTeacher(mainAnchor, scroll, scrollAnchor);
        else viewForTeacher(mainAnchor, scroll, scrollAnchor);
    }

    private static void viewNotForTeacher(AnchorPane mainAnchor, ScrollPane scroll, AnchorPane scrollAnchor) throws SQLException {
        int YPosition = 10;

        Student student;
        User user = GetUser.get();

        if (user.getUser_role().equals("STUDENT")) student = GetStudent.getForStudent(user.getUser_id());
        else student = GetStudent.getForGuardian(user.getUser_id());

        MainText.main.setText("Marks");

        Text average = new Text(35, 70, "Average: " + AllGradesAverageCalculator.calculate(student));
        average.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        average.setFill(Color.rgb(60, 86, 188));
        mainAnchor.getChildren().add(average);

        List<SchoolSubject> subjects = GetAllStudentSubjects.get();
        for (SchoolSubject subject : subjects) {
            CreateLine.create(scrollAnchor, YPosition);

            AnchorPane subjectPane = new AnchorPane();
            subjectPane.setLayoutX(34);
            subjectPane.setLayoutY(YPosition + 1);
            subjectPane.setPrefWidth(919);
            subjectPane.setPrefHeight(73);
            subjectPane.setId(String.valueOf(subject.getSubject_id()));

            Text subjectName = new Text(34, 41, subject.getSubject_name());
            subjectName.setTextAlignment(TextAlignment.LEFT);
            subjectName.setFont(Font.font("Calibri", 25));
            subjectName.setWrappingWidth(344);
            subjectName.setFill(Color.rgb(60, 86, 188));
            subjectPane.getChildren().add(subjectName);

            Text seeMore = new Text(690, 41, "See more");
            seeMore.setTextAlignment(TextAlignment.LEFT);
            seeMore.setFont(Font.font("Calibri", 25));
            seeMore.setWrappingWidth(207);
            seeMore.setFill(Color.rgb(60, 86, 188));
            subjectPane.getChildren().add(seeMore);

            EventHandler<MouseEvent> mouseOnBox = e -> subjectPane.setStyle("-fx-background-color: #DBDBDB;");
            subjectPane.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseOnBox);

            EventHandler<MouseEvent> mouseNotOnBox = e -> subjectPane.setStyle("-fx-background-color: transparent");
            subjectPane.addEventHandler(MouseEvent.MOUSE_EXITED, mouseNotOnBox);

            EventHandler<MouseEvent> boxClicked = e -> {
                mainAnchor.getChildren().remove(average);
                try {
                    LoadAllStudentMarkFromSubject.load(mainAnchor, scroll, scrollAnchor, Integer.parseInt(String.valueOf(subjectPane.getId())));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            };
            subjectPane.addEventHandler(MouseEvent.MOUSE_CLICKED, boxClicked);

            scrollAnchor.getChildren().add(subjectPane);

            YPosition += 74;
        }

        CreateLine.create(scrollAnchor, YPosition);

        if (YPosition >= 544) {
            scrollAnchor.setPrefHeight(YPosition + 124);
            scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        }
    }

    private static void viewForTeacher(AnchorPane mainAnchor, ScrollPane scroll, AnchorPane scrollAnchor) throws SQLException {
        scrollAnchor.getChildren().clear();

        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        MainText.main.setText("Choose class");

        Teacher teacher = new DAOTeacher().getByUserID(GetUser.get().getUser_id()).get(0);
        List<Class> classes = GetNotDuplicatedLearningClasses.get(teacher.getTeacher_id());

        int XPosition;
        int YPosition = 37;
        for (int i = 0; i < classes.size(); i++) {
            if (i % 3 == 0) XPosition = 51;
            else if (i % 3 == 1) XPosition = 393;
            else XPosition = 718;

            Class actualClass = classes.get(i);

            AnchorPane classPane = new AnchorPane();
            classPane.setLayoutX(XPosition);
            classPane.setLayoutY(YPosition);
            classPane.setPrefWidth(200);
            classPane.setPrefHeight(200);
            classPane.setId(Integer.toString(actualClass.getClass_id()));
            classPane.setStyle("-fx-background-color: #3c56bc; -fx-border-color: black; -fx-border-width: 3;");

            Text className = new Text(12, 130, actualClass.getClass_name());
            className.setWrappingWidth(175);
            className.setFont(Font.font("Calibri", FontWeight.BOLD, 100));
            className.setFill(Color.rgb(255, 255, 255));
            className.setTextAlignment(TextAlignment.CENTER);
            classPane.getChildren().add(className);

            EventHandler<MouseEvent> boxClicked = e -> {
                try {
                    LoadAllClassStudents.load(mainAnchor, scroll, scrollAnchor, Integer.parseInt(classPane.getId()), "MARKS");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            };
            classPane.addEventHandler(MouseEvent.MOUSE_CLICKED, boxClicked);

            scrollAnchor.getChildren().add(classPane);

            if (i % 3 == 2) {
                YPosition += 248;
            }
        }

        if (YPosition >= 544) {
            scrollAnchor.setPrefHeight(YPosition + 250);
            scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        }
    }
}
