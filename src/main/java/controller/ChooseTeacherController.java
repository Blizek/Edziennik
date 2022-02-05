package controller;

import DAO.DAOTeacher;
import features.GetNameAndSurnameByTableID;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.Teacher;
import routings.ChooseTeacherMain;
import variables.SubjectTeacherID;

import java.sql.SQLException;
import java.util.List;

public class ChooseTeacherController {
    @FXML
    ScrollPane scroll;

    @FXML
    AnchorPane scrollAnchor;

    public static String subjectName;

    public void initialize() throws SQLException {
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        List<Teacher> allTeachers = new DAOTeacher().getBySubjectName(subjectName);
        int YPosition = 14;

        for (Teacher teacher: allTeachers) {
            createPersonBox(YPosition, teacher);
            YPosition += 102;
        }

        if (YPosition >= 563) {
            scrollAnchor.setPrefHeight(YPosition + 152);
            scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        }
    }

    public void getBack() {
        new ChooseTeacherMain().closeStageByChoosingTeacher();
    }

    private void createPersonBox(int YPosition, Teacher teacher) throws SQLException {
        AnchorPane personPane = new AnchorPane();
        personPane.setLayoutX(13);
        personPane.setLayoutY(YPosition);
        personPane.setPrefSize(318, 88);
        personPane.setStyle("-fx-background-color: white; -fx-border-color: #3c56bc; -fx-border-width: 2;");
        personPane.setId(Integer.toString(teacher.getTeacher_id()));

        String teacherName = GetNameAndSurnameByTableID.getTeacher(teacher.getTeacher_id());
        Text infoText = new Text(8, 51, teacherName);
        infoText.setFont(Font.font("Calibri", 25));
        infoText.setTextAlignment(TextAlignment.CENTER);
        infoText.setWrappingWidth(303);
        infoText.setFill(Color.rgb(60, 86, 188));
        personPane.getChildren().add(infoText);

        EventHandler<MouseEvent> setSuperVisingTeacher = e -> {
            SubjectTeacherID.setID(Integer.parseInt(personPane.getId()));
           new ChooseTeacherMain().closeStageByChoosingTeacher();
        };
        personPane.addEventHandler(MouseEvent.MOUSE_CLICKED, setSuperVisingTeacher);

        scrollAnchor.getChildren().add(personPane);
    }
}
