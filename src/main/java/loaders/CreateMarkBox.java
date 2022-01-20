package loaders;

import DAO.DAOMark;
import com.jfoenix.controls.JFXButton;
import features.ConvertMarkView;
import features.GetNameAndSurnameByTableID;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.Mark;

import java.sql.SQLException;
import java.util.List;

public class CreateMarkBox {
    public static void create(ScrollPane scroll, AnchorPane scrollAnchor, Text pageInformation, int subjectID) throws SQLException {
        int XPosition;
        int YPosition = 83;

        scrollAnchor.getChildren().clear();

        List<Mark> marks = new DAOMark().getAllStudentMarksFromSubject(subjectID);

        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        JFXButton getBackButton = new JFXButton();
        getBackButton.setLayoutX(771);
        getBackButton.setLayoutY(20);
        getBackButton.setPrefSize(200, 37);
        getBackButton.setText("Go back to subjects");
        getBackButton.setFont(Font.font("Calibri", 20));
        getBackButton.setTextFill(Color.rgb(255, 255, 255));
        getBackButton.setStyle("-fx-background-color: #3c56bc; -fx-background-radius: 10; -fx-border-radius: 10;");

        EventHandler<MouseEvent> getBack = e -> {
            try {
                MarksManageScreenView.view(scroll, scrollAnchor, pageInformation);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        };
        getBackButton.addEventHandler(MouseEvent.MOUSE_CLICKED, getBack);

        scrollAnchor.getChildren().add(getBackButton);

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
        String markDescription = mark.getMark_description();
        String teacherName = GetNameAndSurnameByTableID.getTeacher(mark.getTeacher_id());
        String studentName = GetNameAndSurnameByTableID.getStudent(mark.getStudent_id());

        Text markValueText = new Text(24, 43, "Mark: " + markValue);
        markValueText.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        markValueText.setTextAlignment(textAlignment);
        markValueText.setFill(textColor);
        markValueText.setWrappingWidth(wrappingWidth);
        box.getChildren().add(markValueText);

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
}
