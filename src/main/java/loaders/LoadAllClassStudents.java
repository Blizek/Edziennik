package loaders;

import DAO.DAOClass;
import DAO.DAOStudent;
import DAO.DAOTeacher;
import com.jfoenix.controls.JFXButton;
import features.GetUser;
import features.GetUserNameAndSurnameByUserID;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.Class;
import model.Student;
import model.Teacher;
import variables.MainText;

import java.sql.SQLException;
import java.util.List;

public class LoadAllClassStudents {
    public static int studentID;
    public static int staticClassID;

    public static void load(AnchorPane mainAnchor, ScrollPane scroll, AnchorPane scrollAnchor, int classID, String typeView) throws SQLException {
        scroll.setVvalue(0);
        scrollAnchor.setPrefHeight(544);

        Class actualClass = new DAOClass().get(classID).get(0);
        List<Student> students = new DAOStudent().getByClass(classID);

        int YPosition = 83;
        staticClassID = classID;

        scrollAnchor.getChildren().clear();

        MainText.main.setText(actualClass.getClass_name() + "'s students");

        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        JFXButton getBackButton = new JFXButton();
        getBackButton.setLayoutX(771);
        getBackButton.setLayoutY(20);
        getBackButton.setPrefSize(200, 37);
        getBackButton.setText("Go back to classes");
        getBackButton.setFont(Font.font("Calibri", 20));
        getBackButton.setTextFill(Color.rgb(255, 255, 255));
        getBackButton.setStyle("-fx-background-color: #3c56bc; -fx-background-radius: 10; -fx-border-radius: 10;");

        if (typeView.equals("YOUR_CLASS")) {
            getBackButton.setText("Get back");

            JFXButton addStudentButton = CreateRightCornerButton.create(65, "Add student");
            EventHandler<MouseEvent> addStudent = e -> {
                try {
                    ManageStudentScreenView.editing = false;
                    ManageStudentScreenView.view(mainAnchor, scroll, scrollAnchor, classID);
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            };
            addStudentButton.addEventHandler(MouseEvent.MOUSE_CLICKED, addStudent);
            scrollAnchor.getChildren().add(addStudentButton);

            YPosition = 110;
        }

        EventHandler<MouseEvent> getBack = e -> {
            try {
                switch (typeView) {
                    case "MARKS" -> MarksManageScreenView.view(mainAnchor, scroll, scrollAnchor);
                    case "NOTES" -> NotesManageScreenView.view(mainAnchor, scroll, scrollAnchor);
                    case "YOUR_CLASS" -> YourClassManageScreenView.view(mainAnchor, scroll, scrollAnchor);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        };
        getBackButton.addEventHandler(MouseEvent.MOUSE_CLICKED, getBack);

        scrollAnchor.getChildren().add(getBackButton);

        for (Student student: students) {
            CreateLine.create(scrollAnchor, YPosition);

            AnchorPane studentPane = new AnchorPane();
            studentPane.setLayoutX(34);
            studentPane.setLayoutY(YPosition + 1);
            studentPane.setPrefWidth(919);
            studentPane.setPrefHeight(73);
            studentPane.setId(String.valueOf(student.getStudent_id()));

            String studentNameAndSurname = GetUserNameAndSurnameByUserID.getStudentName(student.getUser_id());
            Text studentName = new Text(34, 41, studentNameAndSurname);
            studentName.setTextAlignment(TextAlignment.LEFT);
            studentName.setFont(Font.font("Calibri", 25));
            studentName.setWrappingWidth(344);
            studentName.setFill(Color.rgb(60, 86, 188));
            studentPane.getChildren().add(studentName);

            Text seeMore = new Text(690, 41, "See more");
            seeMore.setTextAlignment(TextAlignment.LEFT);
            seeMore.setFont(Font.font("Calibri", 25));
            seeMore.setWrappingWidth(207);
            seeMore.setFill(Color.rgb(60, 86, 188));
            studentPane.getChildren().add(seeMore);

            EventHandler<MouseEvent> mouseOnBox = e -> studentPane.setStyle("-fx-background-color: #DBDBDB;");
            studentPane.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseOnBox);

            EventHandler<MouseEvent> mouseNotOnBox = e -> studentPane.setStyle("-fx-background-color: transparent");
            studentPane.addEventHandler(MouseEvent.MOUSE_EXITED, mouseNotOnBox);

            EventHandler<MouseEvent> boxClicked = e -> {
                try {
                    studentID = Integer.parseInt(studentPane.getId());
                    switch (typeView) {
                        case "MARKS" -> {
                            Teacher teacher = new DAOTeacher().getByUserID(GetUser.get().getUser_id()).get(0);
                            LoadAllStudentMarkFromSubject.load(mainAnchor, scroll, scrollAnchor, teacher.getSubject_id());
                        }
                        case "NOTES" -> CreateNoteBox.create(mainAnchor, scroll, scrollAnchor, studentID);
                        case "YOUR_CLASS" -> {
                            ManageStudentScreenView.studentID = Integer.parseInt(studentPane.getId());
                            ManageStudentScreenView.editing = true;
                            ManageStudentScreenView.view(mainAnchor, scroll, scrollAnchor, classID);
                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            };
            studentPane.addEventHandler(MouseEvent.MOUSE_CLICKED, boxClicked);

            scrollAnchor.getChildren().add(studentPane);

            YPosition += 74;
        }
        CreateLine.create(scrollAnchor, YPosition);

        if (YPosition >= 544) {
            scrollAnchor.setPrefHeight(YPosition + 124);
            scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        }
    }
}
