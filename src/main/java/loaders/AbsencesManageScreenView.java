package loaders;

import DAO.DAOAbsences;
import DAO.DAOLesson;
import DAO.DAOPlan;
import DAO.DAOSchoolSubject;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
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
import locations.FilesLocations;
import model.*;
import variables.MainText;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AbsencesManageScreenView {
    public static void view(AnchorPane mainAnchor, ScrollPane scroll, AnchorPane scrollAnchor) throws SQLException {
        scrollAnchor.getChildren().clear();
        mainAnchor.getChildren().clear();

        mainAnchor.getChildren().add(MainText.main);

        String userRole = GetUser.get().getUser_role();

        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        if (!userRole.equals("TEACHER")) viewNotForTeacher(mainAnchor, scroll, scrollAnchor);
        else viewForTeacher(mainAnchor, scroll, scrollAnchor);
    }

    public static void viewNotForTeacher(AnchorPane mainAnchor, ScrollPane scroll, AnchorPane scrollAnchor) throws SQLException {
        Student student;
        User user = GetUser.get();

        if (user.getUser_role().equals("STUDENT")) student = GetStudent.getForStudent(user.getUser_id());
        else student = GetStudent.getForGuardian(user.getUser_id());

        MainText.main.setText(student.getStudent_name() + " " + student.getStudent_surname() + "'s absences");

        List<Absences> allStudentAbsences = new DAOAbsences().getAllStudentAbsences(student.getStudent_id());
        List<Absences> absencesAndPresences = new ArrayList<>();

        AnchorPane absencesAndPresencesPane = new AnchorPane();
        absencesAndPresencesPane.setPrefSize(991, 172);
        absencesAndPresencesPane.setLayoutX(0);
        absencesAndPresencesPane.setLayoutY(86);
        scrollAnchor.getChildren().add(absencesAndPresencesPane);

        double frequency = CalculateFrequency.calculate(allStudentAbsences);

        Text frequencyInformation = new Text(37, 48, "Student's frequency: " + frequency + "%");
        frequencyInformation.setFont(Font.font("Calibri", FontWeight.BOLD, 25));
        frequencyInformation.setFill(Color.rgb(60, 86, 188));
        scrollAnchor.getChildren().add(frequencyInformation);

        if (frequency < 80) {
            ImageView warning = new ImageView();
            warning.setFitWidth(30);
            warning.setFitHeight(30);
            warning.setLayoutX(37 + frequencyInformation.getLayoutBounds().getWidth());
            warning.setLayoutY(24);
            warning.setImage(new Image(FilesLocations.FALLING_CLASS_WARNING_ICON));
            scrollAnchor.getChildren().add(warning);
        }

        JFXCheckBox presencesCheckBox = createCheckBox(358, "Presences");
        scrollAnchor.getChildren().add(presencesCheckBox);

        JFXCheckBox absencesCheckBox = createCheckBox(533, "Excused absences");
        scrollAnchor.getChildren().add(absencesCheckBox);

        JFXCheckBox unexcusedAbsencesCheckBox = createCheckBox(758, "Unexcused absences");
        scrollAnchor.getChildren().add(unexcusedAbsencesCheckBox);

        JFXButton refreshButton = CreateRightCornerButton.create(38, "Refresh");
        EventHandler<MouseEvent> refresh = e -> {
            absencesAndPresences.clear();
            try {
                if (presencesCheckBox.isSelected()) {
                    List<Absences> presences = new DAOAbsences().getStudentAbsencesOrPresences(student.getStudent_id(),
                            false, true);
                    absencesAndPresences.addAll(presences);
                }
                if (absencesCheckBox.isSelected()) {
                    List<Absences> absences = new DAOAbsences().getStudentAbsencesOrPresences(student.getStudent_id(),
                            true, true);
                    absencesAndPresences.addAll(absences);
                }
                if (unexcusedAbsencesCheckBox.isSelected()) {
                    List<Absences> unexcusedAbsences = new DAOAbsences().getStudentAbsencesOrPresences(student.getStudent_id(),
                            true, false);
                    absencesAndPresences.addAll(unexcusedAbsences);
                }
                createAbsences(scroll, absencesAndPresencesPane, absencesAndPresences, student);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        };
        refreshButton.addEventHandler(MouseEvent.MOUSE_CLICKED, refresh);
        mainAnchor.getChildren().add(refreshButton);

        createAbsences(scroll, absencesAndPresencesPane, absencesAndPresences, student);
    }

    public static void viewForTeacher(AnchorPane mainAnchor, ScrollPane scroll, AnchorPane scrollAnchor) {

    }

    private static JFXCheckBox createCheckBox(int XPosition, String textValue) {
        JFXCheckBox checkBox = new JFXCheckBox();
        checkBox.setLayoutX(XPosition);
        checkBox.setLayoutY(23);
        checkBox.setPrefWidth(154);
        checkBox.setPrefHeight(38);
        checkBox.setCheckedColor(Color.rgb(60, 86, 188));
        checkBox.setUnCheckedColor(Color.rgb(90, 90, 90));
        checkBox.setText(textValue);
        checkBox.setFont(Font.font("Calibri", 20));
        checkBox.setTextFill(Color.rgb(60, 86, 188));

        return checkBox;
    }

    private static void createAbsences(ScrollPane scroll, AnchorPane anchor, List<Absences> allPresencesAndAbsences,
                                       Student student) throws SQLException {
        anchor.getChildren().clear();

        int YPosition = 10;
        for (Absences allPresencesAndAbsence : allPresencesAndAbsences) {
            CreateLine.create(anchor, YPosition);

            AnchorPane absencePane = new AnchorPane();
            absencePane.setLayoutX(34);
            absencePane.setLayoutY(YPosition + 1);
            absencePane.setPrefWidth(919);
            absencePane.setPrefHeight(73);
            anchor.getChildren().add(absencePane);

            SchoolSubject absenceSubject = new DAOSchoolSubject().getSubjectFromAbsence(allPresencesAndAbsence.getAbsence_id()).get(0);
            createText(absencePane, 31, absenceSubject.getSubject_name());

            Plan absencePlanLesson = new DAOPlan().getAbsencePlanLesson(allPresencesAndAbsence.getAbsence_id()).get(0);
            Lesson planPLesson = new DAOLesson().getAbsenceLesson(allPresencesAndAbsence.getAbsence_id()).get(0);
            String lessonDate = FormatDay.format(planPLesson.getLesson_date().toString());
            String planLessonDescription = lessonDate + " " + absencePlanLesson.getDay() + " " +
                    absencePlanLesson.getStart_hour() + " - " + absencePlanLesson.getFinish_hour();
            createText(absencePane, 302, planLessonDescription);

            String absenceStatus;
            if (allPresencesAndAbsence.isStudent_absence()) {
                if (!allPresencesAndAbsence.isExcused_absence()) {
                    absenceStatus = "Unexcused absence";
                } else {
                    absenceStatus = "Excused absence";
                }
            } else {
                absenceStatus = "Present";
            }
            createText(absencePane, 679, absenceStatus);

            if(absenceStatus.equals("Excused absence")) createImage(absencePane, 818, FilesLocations.FALLING_CLASS_ATTENTION_ICON);
            else if (absenceStatus.equals("Unexcused absence")) createImage(absencePane, 842, FilesLocations.FALLING_CLASS_WARNING_ICON);

            YPosition += 74;
        }
        CreateLine.create(anchor, YPosition);

        List<SchoolSubject> learningSubjects = GetAllStudentSubjects.get();

        Text subjectFrequencyText = new Text(50, YPosition + 35, "Subjects' frequency");
        subjectFrequencyText.setFont(Font.font("Calibri", 20));
        subjectFrequencyText.setFill(Color.rgb(60, 86, 188));
        anchor.getChildren().add(subjectFrequencyText);

        YPosition += 45;

        for (SchoolSubject learningSubject : learningSubjects) {
            CreateLine.create(anchor, YPosition);

            AnchorPane subjectPane = new AnchorPane();
            subjectPane.setLayoutX(34);
            subjectPane.setLayoutY(YPosition + 1);
            subjectPane.setPrefWidth(919);
            subjectPane.setPrefHeight(73);
            anchor.getChildren().add(subjectPane);

            List<Absences> studentSubjectsAbsences = new DAOAbsences().getAllStudentAbsencesFromSubject(student.getStudent_id(),
                    learningSubject.getSubject_id());

            double subjectFrequency = CalculateFrequency.calculate(studentSubjectsAbsences);

            createText(subjectPane, 75, learningSubject.getSubject_name());
            createText(subjectPane, 500, subjectFrequency + "%");

            if (subjectFrequency < 80) {
                String iconURL;
                if (subjectFrequency <= 50) iconURL = FilesLocations.FALLING_CLASS_WARNING_ICON;
                else iconURL = FilesLocations.FALLING_CLASS_ATTENTION_ICON;

                createImage(subjectPane, 575, iconURL);
            }

            YPosition += 74;
        }

        if (YPosition >= 458) {
            anchor.setPrefHeight(YPosition + 124);
            scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        }
    }

    private static void createText(AnchorPane pane, int XPosition, String textValue) {
        Text text = new Text(XPosition, 42, textValue);
        text.setFont(Font.font("Calibri", 20));
        text.setFill(Color.rgb(60, 86, 188));
        pane.getChildren().add(text);
    }

    private static void createImage(AnchorPane pane, int XPosition, String fileName) {
        ImageView frequencyWarning = new ImageView();
        frequencyWarning.setFitWidth(30);
        frequencyWarning.setFitHeight(30);
        frequencyWarning.setLayoutX(XPosition);
        frequencyWarning.setLayoutY(22);
        frequencyWarning.setImage(new Image(fileName));
        pane.getChildren().add(frequencyWarning);
    }
}
