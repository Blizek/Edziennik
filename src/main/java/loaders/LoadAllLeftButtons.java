package loaders;

import DAO.DAOClass;
import com.jfoenix.controls.JFXButton;
import controller.ManageController;
import features.GetUser;
import features.SettingManageView;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.User;

import java.sql.SQLException;

public class LoadAllLeftButtons {
    public static int YPosition = 0;
    public static String buttonToDisableID;
    public static void load(AnchorPane pane, String userRole, AnchorPane mainAnchor, ScrollPane scroll, AnchorPane scrollAnchor, Text pageInformation) throws SQLException {
        User user = GetUser.get();
        if (userRole.equals("STUDENT") || userRole.equals("TEACHER") || userRole.equals("GUARDIAN")) {
            createButton("Marks", "marks", pane, mainAnchor, scroll, scrollAnchor, pageInformation);
            createButton("Absences", "absences", pane, mainAnchor, scroll, scrollAnchor, pageInformation);
            createButton("Notes", "notes", pane, mainAnchor, scroll, scrollAnchor, pageInformation);
            createButton("Lesson plan", "lesson_plan", pane, mainAnchor, scroll, scrollAnchor, pageInformation);
            createButton("Exams", "exams", pane, mainAnchor, scroll, scrollAnchor, pageInformation);
        } else {
            createButton("Students", "students", pane, mainAnchor, scroll, scrollAnchor, pageInformation);
            createButton("Teachers", "teachers", pane, mainAnchor, scroll, scrollAnchor, pageInformation);
        }
        if (userRole.equals("TEACHER")) {
            if (new DAOClass().checkIfItIsClassSupervisingTeacher(user.getUser_id())) {
                createButton("Your class", "your_class", pane, mainAnchor, scroll, scrollAnchor, pageInformation);
            }
        }
    }

    private static void createButton(String textValue, String idValue, AnchorPane paneToAdd, AnchorPane mainAnchor, ScrollPane scroll, AnchorPane scrollAnchor, Text pageInformation) {
        JFXButton button = new JFXButton();

        button.setLayoutX(21);
        button.setLayoutY(YPosition);

        button.setPrefSize(156, 51);

        button.setText(textValue);
        button.setId(idValue);

        button.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        button.setTextFill(Color.rgb(60, 86, 188));
        button.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-border-radius: 10;");

        if (!button.getId().equals(buttonToDisableID)) {
            EventHandler<MouseEvent> mouseOnButton = e -> button.setStyle("-fx-background-color: #DBDBDB; -fx-background-radius: 10; -fx-border-radius: 10;");
            button.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseOnButton);

            EventHandler<MouseEvent> mouseNotOnButton = e -> button.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-border-radius: 10;");
            button.addEventHandler(MouseEvent.MOUSE_EXITED, mouseNotOnButton);

            EventHandler<MouseEvent> clickedButton = e -> {
                paneToAdd.getChildren().clear();
                LoadAllLeftButtons.buttonToDisableID = button.getId();
                LoadAllLeftButtons.YPosition = 0;
                try {
                    LoadAllLeftButtons.load(paneToAdd, GetUser.get().getUser_role(), mainAnchor, scroll, scrollAnchor, pageInformation);
                    SettingManageView.set(buttonToDisableID, mainAnchor, scroll, scrollAnchor, pageInformation);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            };
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, clickedButton);
        } else {
            button.setStyle("-fx-background-color: #DBDBDB; -fx-background-radius: 10; -fx-border-radius: 10;");
        }

        paneToAdd.getChildren().add(button);

        YPosition += 59;
    }
}
