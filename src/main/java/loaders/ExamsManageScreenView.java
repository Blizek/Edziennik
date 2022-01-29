package loaders;

import DAO.DAOTeacher;
import features.GetNotDuplicatedLearningClasses;
import features.GetUser;
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
import model.Teacher;
import variables.MainText;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ExamsManageScreenView {
    public static LocalDate date;

    public static void view(AnchorPane mainAnchor, ScrollPane scroll, AnchorPane scrollAnchor, LocalDate actualDate) throws SQLException {
        scroll.setVvalue(0);
        scrollAnchor.setPrefHeight(544);

        scrollAnchor.getChildren().clear();
        mainAnchor.getChildren().clear();

        date = actualDate;

        mainAnchor.getChildren().add(MainText.main);

        String userRole = GetUser.get().getUser_role();

        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        if (!userRole.equals("TEACHER")) viewNotForTeacher(mainAnchor, scroll, scrollAnchor);
        else viewForTeacher(mainAnchor, scroll, scrollAnchor);
    }

    private static void viewNotForTeacher(AnchorPane mainAnchor, ScrollPane scroll, AnchorPane scrollAnchor) throws SQLException {
        CreateExamsScreenView.view(mainAnchor, scroll, scrollAnchor);
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
                    CreateExamsScreenView.classID = Integer.parseInt(classPane.getId());
                    CreateExamsScreenView.view(mainAnchor, scroll, scrollAnchor);
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
