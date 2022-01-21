package loaders;

import DAO.DAOFinalGrade;
import DAO.DAOGuardian;
import DAO.DAOMark;
import DAO.DAOStudent;
import com.jfoenix.controls.JFXButton;
import features.*;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import locations.FilesLocations;
import model.*;

import java.sql.SQLException;
import java.util.List;

public class CreateMarkBox {
    public static void create(ScrollPane scroll, AnchorPane scrollAnchor, Text pageInformation, int subjectID) throws SQLException {
        int XPosition;
        int YPosition = 83;

        User user = GetUser.get();
        Student student;
        String buttonText = "Go back to subjects";

        if (user.getUser_role().equals("STUDENT")) student = GetStudent.getForStudent(user.getUser_id());
        else if (user.getUser_role().equals("GUARDIAN")) student = GetStudent.getForGuardian(user.getUser_id());
        else {
            student = new DAOStudent().get(LoadAllCLassStudents.studentID).get(0);
            buttonText = "Go back to students";
            YPosition = 173;
        }

        scrollAnchor.getChildren().clear();

        List<Mark> marks = new DAOMark().getAllStudentMarksFromSubject(subjectID, student.getStudent_id());
        List<FinalGrade> finalGrades = new DAOFinalGrade().getStudentFinalGradeFromSuitableSubject(student.getStudent_id(), subjectID);

        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        Text finalGradeText = new Text(23, 44, "");
        finalGradeText.setFont(Font.font("Calibri", FontWeight.BOLD, 25));
        finalGradeText.setFill(Color.rgb(60, 86, 188));
        if (finalGrades.size() == 0) finalGradeText.setText("Final grade: None");
        else finalGradeText.setText("Final grade: " + finalGrades.get(0).getGrade_value());
        scrollAnchor.getChildren().add(finalGradeText);

        double averageGrade = AverageGradeCalculator.calculate(marks);
        Text averageGradeText = new Text(0, 44, "Average grade: " + averageGrade);
        averageGradeText.setFont(Font.font("Calibri", FontWeight.BOLD, 25));
        averageGradeText.setFill(Color.rgb(60, 86, 188));
        double halfOfAverageGradeTextWidth = averageGradeText.getLayoutBounds().getWidth() / 2.0;
        averageGradeText.setLayoutX(492.5 - halfOfAverageGradeTextWidth);
        scrollAnchor.getChildren().add(averageGradeText);

        if (averageGrade <= 2.0) {
            ImageView attentionIcon = new ImageView();
            attentionIcon.setFitWidth(30);
            attentionIcon.setFitHeight(30);
            attentionIcon.setLayoutX(497.5 + halfOfAverageGradeTextWidth);
            attentionIcon.setLayoutY(20);
            attentionIcon.setImage(new Image(FilesLocations.FALLING_CLASS_ATTENTION_ICON));
            scrollAnchor.getChildren().add(attentionIcon);
        }

        JFXButton getBackButton = createRightCornerButton(20, buttonText);

        EventHandler<MouseEvent> getBack = e -> {
            try {
                if (user.getUser_role().equals("TEACHER")) LoadAllCLassStudents.load(scroll, scrollAnchor,pageInformation, LoadAllCLassStudents.staticClassID);
                else MarksManageScreenView.view(scroll, scrollAnchor, pageInformation);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        };
        getBackButton.addEventHandler(MouseEvent.MOUSE_CLICKED, getBack);
        scrollAnchor.getChildren().add(getBackButton);

        if (user.getUser_role().equals("TEACHER")) {
            JFXButton addGradeButton = createRightCornerButton(65, "Add new grade");
            scrollAnchor.getChildren().add(addGradeButton);
            JFXButton addFinalGradeButton = createRightCornerButton(110, "Add final grade");
            scrollAnchor.getChildren().add(addFinalGradeButton);
        }

        for (int i = 0; i < marks.size(); i++) {
            if (i % 2 == 0) XPosition = 61;
            else XPosition = 560;

            AnchorPane markPane = new AnchorPane();

            markPane.setLayoutX(XPosition);
            markPane.setLayoutY(YPosition);
            markPane.setPrefWidth(371);
            markPane.setPrefHeight(198);
            markPane.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-background-color: #3c56bc;");

            fillMarkBox(marks.get(i), markPane);

            scrollAnchor.getChildren().add(markPane);

            if (i % 2 == 1) YPosition += 230;
        }
    }

    private static void fillMarkBox(Mark mark, AnchorPane box) throws SQLException {
        TextAlignment textAlignment = TextAlignment.LEFT;
        Color textColor = Color.rgb(255, 255, 255);
        int wrappingWidth = 322;

        String markValue = ConvertMarkView.convert(mark.getMark_value());
        int markWeight = mark.getMark_weight();
        String markDescription = mark.getMark_description();
        String teacherName = GetNameAndSurnameByTableID.getTeacher(mark.getTeacher_id());
        String studentName = GetNameAndSurnameByTableID.getStudent(mark.getStudent_id());

        Text markValueText = new Text(24, 43, "Mark: " + markValue);
        markValueText.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        markValueText.setTextAlignment(textAlignment);
        markValueText.setFill(textColor);
        markValueText.setWrappingWidth(wrappingWidth);
        box.getChildren().add(markValueText);

        Text markWeightText = new Text(212, 43, "Weight: " + markWeight);
        markWeightText.setFont(Font.font("Calibri", 20));
        markWeightText.setTextAlignment(textAlignment);
        markWeightText.setFill(textColor);
        box.getChildren().add(markWeightText);

        Text markDescriptionText = new Text(24, 84, "Description: " + markDescription);
        markDescriptionText.setFont(Font.font("Calibri", 20));
        markDescriptionText.setTextAlignment(textAlignment);
        markDescriptionText.setFill(textColor);
        markDescriptionText.setWrappingWidth(wrappingWidth);
        box.getChildren().add(markDescriptionText);

        Text markTeacherText = new Text(24, 127, "From: " + teacherName);
        markTeacherText.setFont(Font.font("Calibri", 20));
        markTeacherText.setTextAlignment(textAlignment);
        markTeacherText.setFill(textColor);
        markTeacherText.setWrappingWidth(wrappingWidth);
        box.getChildren().add(markTeacherText);

        Text markStudentText = new Text(24, 174, "To: " + studentName);
        markStudentText.setFont(Font.font("Calibri", 20));
        markStudentText.setTextAlignment(textAlignment);
        markStudentText.setFill(textColor);
        markStudentText.setWrappingWidth(wrappingWidth);
        box.getChildren().add(markStudentText);
    }

    private static JFXButton createRightCornerButton(int YPosition, String buttonText) {
        JFXButton button = new JFXButton();
        button.setLayoutX(771);
        button.setLayoutY(YPosition);
        button.setPrefSize(200, 37);
        button.setText(buttonText);
        button.setFont(Font.font("Calibri", 20));
        button.setTextFill(Color.rgb(255, 255, 255));
        button.setStyle("-fx-background-color: #3c56bc; -fx-background-radius: 10; -fx-border-radius: 10;");

        return button;
    }
}
