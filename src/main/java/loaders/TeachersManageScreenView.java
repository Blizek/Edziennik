package loaders;

import DAO.DAOPrincipal;
import DAO.DAOSchoolSubject;
import DAO.DAOTeacher;
import com.jfoenix.controls.JFXButton;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.Principal;
import model.SchoolSubject;
import model.Teacher;
import variables.MainText;

import java.sql.SQLException;
import java.util.List;

public class TeachersManageScreenView {
    public static void view(AnchorPane mainAnchor, ScrollPane scroll, AnchorPane scrollAnchor) throws SQLException {
        scroll.setVvalue(0);
        scrollAnchor.setPrefHeight(544);

        scrollAnchor.getChildren().clear();
        mainAnchor.getChildren().clear();

        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        mainAnchor.getChildren().add(MainText.main);

        MainText.main.setText("All school teachers");

        JFXButton refreshButton = CreateRightCornerButton.create(38, "Refresh");
        EventHandler<MouseEvent> refresh = e -> {
            try {
                mainAnchor.getChildren().remove(refreshButton);
                view(mainAnchor, scroll, scrollAnchor);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        };
        refreshButton.addEventHandler(MouseEvent.MOUSE_CLICKED, refresh);
        mainAnchor.getChildren().add(refreshButton);

        JFXButton addPersonButton = CreateRightCornerButton.create(20, "Add person");
        EventHandler<MouseEvent> addPerson = e -> {
            System.out.println("dodaj osobe");
        };
        addPersonButton.addEventHandler(MouseEvent.MOUSE_CLICKED, addPerson);
        scrollAnchor.getChildren().add(addPersonButton);

        createPartText(65, "Principals", scrollAnchor);
        CreateLine.create(scrollAnchor, 80);

        int YPosition = 80;
        List<Principal> principals = new DAOPrincipal().getAllSorted();

        for (Principal principal : principals) {
            String principalNameAndSurname = principal.getPrincipal_name() + " " + principal.getPrincipal_surname();
            AnchorPane pane = createPersonPane(YPosition + 1, principalNameAndSurname);
            pane.setId(principal.getPrincipal_id() + "principal");
            createManageButtons(pane);
            scrollAnchor.getChildren().add(pane);

            YPosition += 74;
        }

        CreateLine.create(scrollAnchor, YPosition);

        YPosition += 45;
        List<SchoolSubject> subjects = new DAOSchoolSubject().getAll();

        for (SchoolSubject subject : subjects) {
            createPartText(YPosition, subject.getSubject_name(), scrollAnchor);
            CreateLine.create(scrollAnchor, YPosition + 20);

            YPosition += 20;
            List<Teacher> subjectTeachers = new DAOTeacher().getByTeachersSubject(subject.getSubject_id());
            for (Teacher subjectTeacher : subjectTeachers) {
                String nameAndSurname = subjectTeacher.getTeacher_name() + " " + subjectTeacher.getTeacher_surname();
                AnchorPane teacherPane = createPersonPane(YPosition + 1, nameAndSurname);
                teacherPane.setId(subjectTeacher.getTeacher_id() + "teacher");
                createManageButtons(teacherPane);
                scrollAnchor.getChildren().add(teacherPane);

                YPosition += 74;
            }
            CreateLine.create(scrollAnchor, YPosition);

            YPosition += 65;
        }

        if (YPosition >= 544) {
            scrollAnchor.setPrefHeight(YPosition + 124);
            scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        }
    }

    private static void createPartText(int YPosition, String textValue, AnchorPane paneToAdd) {
        Text partText = new Text(74, YPosition, textValue);
        partText.setFont(Font.font("Calibri", 25));
        partText.setFill(Color.rgb(60, 86, 188));
        paneToAdd.getChildren().add(partText);
    }

    private static AnchorPane createPersonPane(int YPosition, String nameAndSurname) {
        AnchorPane personPane = new AnchorPane();
        personPane.setLayoutX(33);
        personPane.setLayoutY(YPosition);
        personPane.setPrefSize(919, 74);

        CreateSchedulePlanLessonText.createText(22, nameAndSurname, personPane);

        return personPane;
    }

    private static void createManageButtons(AnchorPane pane) {
        JFXButton editButton = buttonSettings(693, "Edit");
        pane.getChildren().add(editButton);

        JFXButton deleteButton = buttonSettings(805, "Delete");
        pane.getChildren().add(deleteButton);
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
