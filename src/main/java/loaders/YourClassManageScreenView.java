package loaders;

import DAO.DAOClass;
import com.jfoenix.controls.JFXButton;
import features.GetUser;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.Class;
import model.User;
import variables.MainText;

import java.sql.SQLException;

public class YourClassManageScreenView {
    public static void view(AnchorPane mainAnchor, ScrollPane scroll, AnchorPane scrollAnchor) throws SQLException {
        scroll.setVvalue(0);
        scrollAnchor.setPrefHeight(544);

        scrollAnchor.getChildren().clear();
        mainAnchor.getChildren().clear();

        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        mainAnchor.getChildren().add(MainText.main);

        User user = GetUser.get();
        Class thisClass = new DAOClass().getClassBySupervisingTeacherUserID(user.getUser_id()).get(0);

        MainText.main.setText("Manage " + thisClass.getClass_name());

        JFXButton studentsButton = createButton(63, 62, "Students");
        EventHandler<MouseEvent> students = e -> {
            try {
                LoadAllClassStudents.load(mainAnchor, scroll, scrollAnchor, thisClass.getClass_id(), "YOUR_CLASS");
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        };
        studentsButton.addEventHandler(MouseEvent.MOUSE_CLICKED, students);
        scrollAnchor.getChildren().add(studentsButton);

        JFXButton lessonPlanButton = createButton(397, 62, "Lesson plan");
        EventHandler<MouseEvent> lessonPlan = e -> {
            try {
                SetClassSchedulePlanScreenView.day = "MONDAY";
                SetClassSchedulePlanScreenView.view(mainAnchor, scroll, scrollAnchor, thisClass.getClass_id());
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        };
        lessonPlanButton.addEventHandler(MouseEvent.MOUSE_CLICKED, lessonPlan);
        scrollAnchor.getChildren().add(lessonPlanButton);

        JFXButton excusesButton = createButton(743, 62, "Excuses");
        scrollAnchor.getChildren().add(excusesButton);

        JFXButton certificatesButton = createButton(63, 176, "Certificates");
        scrollAnchor.getChildren().add(certificatesButton);
    }

    private static JFXButton createButton(int XPosition, int YPosition, String textValue) {
        JFXButton button = new JFXButton();
        button.setLayoutX(XPosition);
        button.setLayoutY(YPosition);
        button.setPrefSize(192, 51);
        button.setText(textValue);
        button.setFont(Font.font("Calibri", 25));
        button.setTextFill(Color.rgb(255, 255, 255));
        button.setStyle("-fx-background-color: #3c56bc; -fx-background-radius: 10; -fx-border-radius: 10;");

        return button;
    }
}
