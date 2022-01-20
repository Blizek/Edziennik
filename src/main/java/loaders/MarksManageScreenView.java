package loaders;

import features.GetAllStudentSubjects;
import features.GetUser;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.SchoolSubject;

import java.sql.SQLException;
import java.util.List;

public class MarksManageScreenView {
    public static void view(ScrollPane scroll, AnchorPane scrollAnchor, Text pageInformation) throws SQLException {
        scrollAnchor.getChildren().clear();

        String userRole = GetUser.get().getUser_role();

        pageInformation.setText("Mark");

        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        if (!userRole.equals("TEACHER")) viewNotForTeacher(scroll, scrollAnchor, pageInformation);
        else viewForTeacher(scrollAnchor);
    }

    private static void viewNotForTeacher(ScrollPane scroll, AnchorPane scrollAnchor, Text pageInformation) throws SQLException {
        int YPosition = 10;
        List<SchoolSubject> subjects = GetAllStudentSubjects.get();
        for (SchoolSubject subject : subjects) {
            createLine(scrollAnchor, YPosition);

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
                try {
                    LoadAllStudentMarkFromSubject.load(scroll, scrollAnchor, pageInformation, Integer.parseInt(String.valueOf(subjectPane.getId())));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            };
            subjectPane.addEventHandler(MouseEvent.MOUSE_CLICKED, boxClicked);

            scrollAnchor.getChildren().add(subjectPane);

            YPosition += 74;
        }

        createLine(scrollAnchor, YPosition);

        if (YPosition >= 544) {
            scrollAnchor.setPrefHeight(YPosition + 50);
            scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        }
    }

    private static void viewForTeacher(AnchorPane pane) {

    }

    private static void createLine(AnchorPane paneToAdd, int YPosition) {
        Line line = new Line();
        line.setLayoutX(135);
        line.setLayoutY(YPosition);
        line.setStartX(-100);
        line.setStartY(0);
        line.setEndX(817);
        line.setEndY(0);
        line.setStroke(Color.rgb(154, 154, 154));
        line.setStrokeWidth(2);
        paneToAdd.getChildren().add(line);
    }
}
