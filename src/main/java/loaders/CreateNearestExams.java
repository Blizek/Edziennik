package loaders;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import locations.FilesLocations;

import java.util.ArrayList;
import java.util.List;

public class CreateNearestExams {
    static List<String> sampleExam = new ArrayList<>();
    static List<String> sampleExamDay = new ArrayList<>();
    static List<String> sampleClass = new ArrayList<>();

    public static void setSampleClass(List<String> sampleClass) {
        sampleClass.add("1A");
        sampleClass.add("3A");
        sampleClass.add("2G");
        sampleClass.add("3Bg");
    }

    public static void setSampleExam(List<String> sampleExam) {
        sampleExam.add("informatyka");
        sampleExam.add("matmematyka");
        sampleExam.add("j.polski");
        sampleExam.add("geografia");
    }

    public static void setSampleExamDay(List<String> sampleExamDay) {
        sampleExamDay.add("8.00 Monday");
        sampleExamDay.add("8.00 Monday");
        sampleExamDay.add("8.00 Monday");
        sampleExamDay.add("8.00 Monday");
    }

    public static void create(AnchorPane pane, String role, double actualPaneHeight) {
        setSampleExamDay(sampleExamDay);
        setSampleClass(sampleClass);
        setSampleExam(sampleExam);

        AnchorPane examsPane = new AnchorPane();
        int examYPosition = 144;

        examsPane.setLayoutX(661);
        examsPane.setLayoutY(315);
        examsPane.setPrefWidth(479);
        examsPane.setStyle("-fx-background-color: #9A10EB; -fx-border-color: black; -fx-border-width: 3;");

        Text theNearestExamsText = new Text(111, 58, "The nearest exams");
        theNearestExamsText.setWrappingWidth(354);
        theNearestExamsText.setTextAlignment(TextAlignment.CENTER);
        theNearestExamsText.setFont(Font.font("Calibri", FontWeight.BOLD, 35));
        theNearestExamsText.setFill(Color.rgb(255, 255, 255));

        ImageView examsIcon = new ImageView();
        examsIcon.setFitWidth(71);
        examsIcon.setFitHeight(71);
        examsIcon.setLayoutX(29);
        examsIcon.setLayoutY(14);
        examsIcon.setImage(new Image(FilesLocations.EXAMS_ICON_PATH));

        Line topLine = new Line();
        topLine.setLayoutX(131);
        topLine.setLayoutY(106);
        topLine.setStartX(-100);
        topLine.setEndX(318);
        topLine.setStroke(Color.rgb(255, 255, 255));
        topLine.setStrokeWidth(3);

        if (sampleExam.size() == 0) {
            Text info = new Text(40, examYPosition, "No exams");
            info.setFont(Font.font("Calibri", 20));
            info.setFill(Color.rgb(255, 255, 255));
            info.setTextAlignment(TextAlignment.LEFT);
            info.setWrappingWidth(400);
            examYPosition += 20;
            examsPane.getChildren().add(info);
        } else {
            for (int i = 0; i < sampleExam.size(); i++) {
                String examInformation;
                if (role.equals("TEACHER")) examInformation = sampleExamDay.get(i) + " " + sampleExam.get(i) + " " + sampleClass.get(i);
                else examInformation = sampleExamDay.get(i) + " " + sampleExam.get(i);
                Text lesson = new Text(40, examYPosition, examInformation);
                lesson.setFont(Font.font("Calibri", 20));
                lesson.setFill(Color.rgb(255, 255, 255));
                lesson.setTextAlignment(TextAlignment.LEFT);
                lesson.setWrappingWidth(400);
                examsPane.getChildren().add(lesson);
                examYPosition += 30;
            }
        }

        Line bottomLine = new Line();
        bottomLine.setLayoutX(131);
        bottomLine.setLayoutY(examYPosition);
        bottomLine.setStartX(-100);
        bottomLine.setEndX(318);
        bottomLine.setStroke(Color.rgb(255, 255, 255));
        bottomLine.setStrokeWidth(3);

        examsPane.getChildren().add(examsIcon);
        examsPane.getChildren().add(theNearestExamsText);
        examsPane.getChildren().add(topLine);
        examsPane.getChildren().add(bottomLine);
        examsPane.getChildren().add(CreateCheckMoreButton.createButton(examYPosition));

        examsPane.setPrefHeight(examYPosition + 80);

        pane.getChildren().add(examsPane);

        if (examsPane.getPrefHeight() >= actualPaneHeight - 465) {
            pane.setPrefHeight(465 + examsPane.getPrefHeight());
        }
    }
}
