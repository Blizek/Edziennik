package loaders;

import DAO.DAOClass;
import com.jfoenix.controls.JFXButton;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.Class;
import variables.MainText;
import variables.SupervisingTeacherID;

import java.sql.SQLException;
import java.util.List;

public class AllClassesManageScreenView {
    public static void view(AnchorPane mainAnchor, ScrollPane scroll, AnchorPane scrollAnchor) throws SQLException {
        scroll.setVvalue(0);
        scrollAnchor.setPrefHeight(544);

        scrollAnchor.getChildren().clear();
        mainAnchor.getChildren().clear();

        mainAnchor.getChildren().add(MainText.main);
        MainText.main.setText("All classes");

        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        JFXButton addClassButton = CreateRightCornerButton.create(20, "Add class");
        EventHandler<MouseEvent> addClass = e -> {
            try {
                SupervisingTeacherID.setID(-1);
                ManageClassScreenView.view(mainAnchor, scroll, scrollAnchor, -1);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        };
        addClassButton.addEventHandler(MouseEvent.MOUSE_CLICKED, addClass);
        mainAnchor.getChildren().add(addClassButton);

        List<Class> allClasses = new DAOClass().getAll();

        int XPosition;
        int YPosition = 37;
        for (int i = 0; i < allClasses.size(); i++) {
            if (i % 3 == 0) XPosition = 51;
            else if (i % 3 == 1) XPosition = 393;
            else XPosition = 718;

            Class actualClass = allClasses.get(i);

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
                    mainAnchor.getChildren().remove(addClassButton);
                    ClassManageScreenView.view(mainAnchor, scroll, scrollAnchor, Integer.parseInt(classPane.getId()));
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
