package loaders;

import DAO.DAOTeacher;
import features.GetNotDuplicatedLearningClasses;
import features.GetStudent;
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
import model.Student;
import model.Teacher;
import model.User;
import variables.MainText;

import java.sql.SQLException;
import java.util.List;

public class NotesManageScreenView {
    public static void view(AnchorPane mainAnchor, ScrollPane scroll, AnchorPane scrollAnchor) throws SQLException {
        scroll.setVvalue(0);
        scrollAnchor.setPrefHeight(544);

        scrollAnchor.getChildren().clear();
        mainAnchor.getChildren().clear();

        mainAnchor.getChildren().add(MainText.main);

        User user = GetUser.get();

        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        if (!user.getUser_role().equals("TEACHER")) viewNotForTeacher(mainAnchor, scroll, scrollAnchor, user);
        else viewForTeacher(mainAnchor, scroll, scrollAnchor);
    }

    private static void viewNotForTeacher(AnchorPane mainAnchor, ScrollPane scroll, AnchorPane scrollAnchor, User user) throws SQLException {
        Student student;

        if (user.getUser_role().equals("STUDENT")) student = GetStudent.getForStudent(user.getUser_id());
        else student = GetStudent.getForGuardian(user.getUser_id());

        CreateNoteBox.create(mainAnchor, scroll, scrollAnchor, student.getStudent_id());
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
                    LoadAllClassStudents.load(mainAnchor, scroll, scrollAnchor, Integer.parseInt(classPane.getId()), false);
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
