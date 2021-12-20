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

public class CreateLessonsSchedule {
    List<String> sampleLessonsHours = new ArrayList<>();
    List<String> sampleLessons = new ArrayList<>();
    List<String> sampleLessonsPlace = new ArrayList<>();
    List<String> sampleLessonsClasses = new ArrayList<>();

    public void setSampleLessons(List<String> sampleLessons) {
        sampleLessons.add("j.hiszpański");
        sampleLessons.add("j.hiszpański");
        sampleLessons.add("informatyka");
        sampleLessons.add("biologia");
        sampleLessons.add("fizyka");
        sampleLessons.add("matematyka");
        sampleLessons.add("matematyka");
    }

    public void setSampleLessonsHours(List<String> sampleLessonsHours) {
        sampleLessonsHours.add("8.00 - 8.45");
        sampleLessonsHours.add("8.55 - 9.40");
        sampleLessonsHours.add("9.50 - 10.35");
        sampleLessonsHours.add("10.55 - 11.40");
        sampleLessonsHours.add("11.50 - 12.35");
        sampleLessonsHours.add("12.45 - 13.30");
        sampleLessonsHours.add("13.35 - 14.20");
    }

    public void setSampleLessonsPlace(List<String> sampleLessonsPlace) {
        sampleLessonsPlace.add("205");
        sampleLessonsPlace.add("205");
        sampleLessonsPlace.add("211");
        sampleLessonsPlace.add("1");
        sampleLessonsPlace.add("1");
        sampleLessonsPlace.add("1");
        sampleLessonsPlace.add("1");
    }

    public void setSampleLessonsClasses(List<String> sampleLessonsClasses) {
        sampleLessonsClasses.add("3A");
        sampleLessonsClasses.add("3A");
        sampleLessonsClasses.add("3A");
        sampleLessonsClasses.add("1A");
        sampleLessonsClasses.add("2E");
        sampleLessonsClasses.add("3D");
        sampleLessonsClasses.add("3gg");
    }

    public void create(AnchorPane pane, String role) {
        setSampleLessons(sampleLessons);
        setSampleLessonsPlace(sampleLessonsPlace);
        setSampleLessonsHours(sampleLessonsHours);
        setSampleLessonsClasses(sampleLessonsClasses);

        AnchorPane lessonPane = new AnchorPane();
        int lessonYPosition = 144;

        lessonPane.setLayoutX(51);
        lessonPane.setLayoutY(315);
        lessonPane.setPrefWidth(479);
        lessonPane.setStyle("-fx-background-color: #EB1010; -fx-border-color: black; -fx-border-width: 3;");

        Text todayLessonsText = new Text(136, 57, "Today's lessons");
        todayLessonsText.setWrappingWidth(282);
        todayLessonsText.setTextAlignment(TextAlignment.CENTER);
        todayLessonsText.setFont(Font.font("Calibri", FontWeight.BOLD, 35));
        todayLessonsText.setFill(Color.rgb(255, 255, 255));

        ImageView scheduleIcon = new ImageView();
        scheduleIcon.setFitWidth(68);
        scheduleIcon.setFitHeight(71);
        scheduleIcon.setLayoutX(30);
        scheduleIcon.setLayoutY(14);
        scheduleIcon.setImage(new Image(FilesLocations.scheduleIconPath));

        Line topLine = new Line();
        topLine.setLayoutX(131);
        topLine.setLayoutY(106);
        topLine.setStartX(-100);
        topLine.setEndX(318);
        topLine.setStroke(Color.rgb(255, 255, 255));
        topLine.setStrokeWidth(3);

        if (sampleLessons.size() == 0) {
            Text info = new Text(40, lessonYPosition, "No lessons");
            info.setFont(Font.font("Calibri", 20));
            info.setFill(Color.rgb(255, 255, 255));
            info.setTextAlignment(TextAlignment.LEFT);
            info.setWrappingWidth(400);
            lessonYPosition += 20;
            lessonPane.getChildren().add(info);
        } else {
            for (int i = 0; i < sampleLessons.size(); i++) {
                String lessonInformation;
                if (role.equals("TEACHER")) lessonInformation = sampleLessonsHours.get(i) + " " + sampleLessons.get(i) + " " + sampleLessonsPlace.get(i) + " " + sampleLessonsClasses.get(i);
                else lessonInformation = sampleLessonsHours.get(i) + " " + sampleLessons.get(i) + " " + sampleLessonsPlace.get(i);
                Text lesson = new Text(40, lessonYPosition, lessonInformation);
                lesson.setFont(Font.font("Calibri", 20));
                lesson.setFill(Color.rgb(255, 255, 255));
                lesson.setTextAlignment(TextAlignment.LEFT);
                lesson.setWrappingWidth(400);
                lessonPane.getChildren().add(lesson);
                lessonYPosition += 30;
            }
        }

        Line bottomLine = new Line();
        bottomLine.setLayoutX(131);
        bottomLine.setLayoutY(lessonYPosition);
        bottomLine.setStartX(-100);
        bottomLine.setEndX(318);
        bottomLine.setStroke(Color.rgb(255, 255, 255));
        bottomLine.setStrokeWidth(3);

        lessonPane.getChildren().add(todayLessonsText);
        lessonPane.getChildren().add(scheduleIcon);
        lessonPane.getChildren().add(topLine);
        lessonPane.getChildren().add(bottomLine);
        lessonPane.getChildren().add(new CreateCheckMoreButton().createButton(lessonYPosition));

        lessonPane.setPrefHeight(lessonYPosition + 80);

        pane.getChildren().add(lessonPane);

        if (lessonYPosition + 395 >= 730) {
            pane.setPrefHeight(lessonYPosition + 600);
        }
    }
}
