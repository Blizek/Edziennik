package loaders;

import DAO.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import enumTypes.DatabaseTablesName;
import features.CalculateFrequency;
import features.GetMaxID;
import features.GetNameAndSurnameByTableID;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Absences;
import model.SchoolSubject;
import model.Student;
import variables.MainText;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LessonAbsencesScreenView {
    public static void view(AnchorPane mainAnchor, ScrollPane scroll, AnchorPane scrollAnchor, String stringLessonID) throws SQLException {
        scrollAnchor.getChildren().clear();

        int lessonID = Integer.parseInt(stringLessonID);

        List<Student> classStudents = new DAOStudent().getAllClassmatesFromLesson(lessonID);
        SchoolSubject subject = new DAOSchoolSubject().getLessonSubject(lessonID).get(0);
        String className = new DAOClass().getClassFromLesson(lessonID).get(0).getClass_name();

        MainText.main.setText(className + "'s students presence");

        JFXButton getBackButton = CreateRightCornerButton.create(38, "Go back to schedule");
        EventHandler<MouseEvent> getBack = e -> {
            try {
                mainAnchor.getChildren().remove(getBackButton);
                LocalDate date = AbsencesManageScreenView.date;
                AbsencesManageScreenView.view(mainAnchor, scroll, scrollAnchor, date);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        };
        getBackButton.addEventHandler(MouseEvent.MOUSE_CLICKED, getBack);
        mainAnchor.getChildren().add(getBackButton);

        Text errorText = new Text(50, 25, "You didn't select presence for every student!");
        errorText.setFont(Font.font("Calibri", 18));
        errorText.setFill(Color.rgb(255, 0, 0));
        errorText.setVisible(false);
        scrollAnchor.getChildren().add(errorText);

        int YPosition = 50;

        ArrayList<String> absencesToSet = new ArrayList<>();

        boolean save = true;

        for (int i = 0; i < classStudents.size(); i++) {
            List<Absences> studentPresenceList = new DAOAbsences().getStudentLessonPresence(lessonID, classStudents.get(i).getStudent_id());

            if (studentPresenceList.size() == 0) {
                absencesToSet.add("EMPTY");
                save = true;
            } else {
                absencesToSet.add(setPresenceByAbsence(studentPresenceList.get(0)));
                save = false;
            }

            CreateLine.create(scrollAnchor, YPosition);

            AnchorPane studentPresenceBox = new AnchorPane();
            studentPresenceBox.setLayoutX(33);
            studentPresenceBox.setLayoutY(YPosition);
            studentPresenceBox.setPrefSize(919, 74);
            studentPresenceBox.setId(Integer.toString(i));

            List<Absences> allStudentSubjectPresences = new DAOAbsences().getAllStudentAbsencesFromSubject(classStudents.get(i).getStudent_id(),
                    subject.getSubject_id());

            CreateSchedulePlanLessonText.createText(21, Integer.toString(i + 1), studentPresenceBox);

            String studentName = GetNameAndSurnameByTableID.getStudent(classStudents.get(i).getStudent_id());
            CreateSchedulePlanLessonText.createText(65, studentName, studentPresenceBox);

            CreateSchedulePlanLessonText.createText(845, CalculateFrequency.calculate(allStudentSubjectPresences) + "%",
                    studentPresenceBox);

            JFXRadioButton presentButton = createButton(309, "Present");
            studentPresenceBox.getChildren().add(presentButton);

            JFXRadioButton excusedAbsenceButton = createButton(426, "Excused absence");
            studentPresenceBox.getChildren().add(excusedAbsenceButton);

            JFXRadioButton unexcusedAbsenceButton = createButton(610, "Unexcused absence");
            studentPresenceBox.getChildren().add(unexcusedAbsenceButton);

            setCheckedWhenTeacherEditAbsence(absencesToSet.get(i), presentButton, excusedAbsenceButton, unexcusedAbsenceButton);

            EventHandler<MouseEvent> presentButtonClicked = e -> {
                setRadioCheckButton(presentButton, excusedAbsenceButton, unexcusedAbsenceButton, presentButton);
                absencesToSet.set(Integer.parseInt(studentPresenceBox.getId()), setPresenceByRadioButtons(presentButton,
                        excusedAbsenceButton, unexcusedAbsenceButton));
            };
            presentButton.addEventHandler(MouseEvent.MOUSE_CLICKED, presentButtonClicked);

            EventHandler<MouseEvent> excusedAbsenceButtonClicked = e -> {
                setRadioCheckButton(presentButton, excusedAbsenceButton, unexcusedAbsenceButton, excusedAbsenceButton);
                absencesToSet.set(Integer.parseInt(studentPresenceBox.getId()), setPresenceByRadioButtons(presentButton,
                        excusedAbsenceButton, unexcusedAbsenceButton));
            };
            excusedAbsenceButton.addEventHandler(MouseEvent.MOUSE_CLICKED, excusedAbsenceButtonClicked);

            EventHandler<MouseEvent> unexcusedAbsenceButtonClicked = e -> {
                setRadioCheckButton(presentButton, excusedAbsenceButton, unexcusedAbsenceButton, unexcusedAbsenceButton);
                absencesToSet.set(Integer.parseInt(studentPresenceBox.getId()), setPresenceByRadioButtons(presentButton,
                        excusedAbsenceButton, unexcusedAbsenceButton));
            };
            unexcusedAbsenceButton.addEventHandler(MouseEvent.MOUSE_CLICKED, unexcusedAbsenceButtonClicked);

            scrollAnchor.getChildren().add(studentPresenceBox);

            YPosition += 74;
        }

        JFXButton saveButton = CreateRightCornerButton.create(2, "Save");
        boolean finalSave = save;
        EventHandler<MouseEvent> saveToDatabase = e -> {
            try {
                if (absencesToSet.contains("EMPTY")) {
                    errorText.setVisible(true);
                } else {
                    errorText.setVisible(false);
                    for (int i = 0; i < absencesToSet.size(); i++) {
                        if (finalSave) {
                            Absences studentPresence = new Absences(GetMaxID.get(DatabaseTablesName.ABSENCES) + 1,
                                    classStudents.get(i).getStudent_id(), lessonID, false, false);
                            Absences finalStudentPresence = setPresence(absencesToSet.get(i), studentPresence);
                            new DAOAbsences().save(finalStudentPresence);
                        } else {
                            Absences studentPresence = new DAOAbsences().getStudentLessonPresence(lessonID,
                                    classStudents.get(i).getStudent_id()).get(0);
                            Absences finalStudentPresence = setPresence(absencesToSet.get(i), studentPresence);
                            new DAOAbsences().update(finalStudentPresence);
                        }
                    }
                    mainAnchor.getChildren().remove(getBackButton);
                    LocalDate date = AbsencesManageScreenView.date;
                    AbsencesManageScreenView.view(mainAnchor, scroll, scrollAnchor, date);
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        };
        saveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, saveToDatabase);
        scrollAnchor.getChildren().add(saveButton);

        CreateLine.create(scrollAnchor, YPosition);

        if (YPosition >= 544) {
            scrollAnchor.setPrefHeight(YPosition + 124);
            scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        }
    }

    private static JFXRadioButton createButton(int XPosition, String textValue) {
        JFXRadioButton radioButton = new JFXRadioButton();
        radioButton.setLayoutX(XPosition);
        radioButton.setLayoutY(26);
        radioButton.setSelectedColor(Color.rgb(60, 86, 188));
        radioButton.setUnSelectedColor(Color.rgb(90, 90, 90));
        radioButton.setText(textValue);
        radioButton.setFont(Font.font("Calibri", 20));
        radioButton.setTextFill(Color.rgb(60, 86, 188));

        return radioButton;
    }

    private static void setRadioCheckButton(JFXRadioButton present, JFXRadioButton excused, JFXRadioButton unexcused, JFXRadioButton toSet) {
        present.setSelected(false);
        excused.setSelected(false);
        unexcused.setSelected(false);
        toSet.setSelected(true);
    }

    private static String setPresenceByAbsence(Absences absence) {
        boolean absenceOnLesson = absence.isStudent_absence();
        boolean excusedAbsence = absence.isExcused_absence();

        if (!absenceOnLesson) return "PRESENT";
        else {
            if (excusedAbsence) return "EXCUSED ABSENCE";
            else return "UNEXCUSED ABSENCE";
        }
    }

    private static void setCheckedWhenTeacherEditAbsence(String presenceStatus, JFXRadioButton present, JFXRadioButton excused,
                                                         JFXRadioButton unexcused) {
        switch (presenceStatus) {
            case "PRESENT" -> present.setSelected(true);
            case "EXCUSED ABSENCE" -> excused.setSelected(true);
            case "UNEXCUSED ABSENCE" -> unexcused.setSelected(true);
            default -> {
                present.setSelected(false);
                excused.setSelected(false);
                unexcused.setSelected(false);
            }
        }
    }

    private static String setPresenceByRadioButtons(JFXRadioButton present, JFXRadioButton excused, JFXRadioButton unexcused) {
        if (present.isSelected()) return "PRESENT";
        else if (excused.isSelected()) return "EXCUSED ABSENCE";
        else if (unexcused.isSelected()) return "UNEXCUSED ABSENCE";
        else return "EMPTY";
    }

    private static Absences setPresence(String presence, Absences absence) {
        if (presence.equals("PRESENT")) {
            absence.setStudent_absence(false);
            absence.setExcused_absence(true);
        } else if (presence.equals("EXCUSED ABSENCE")) {
            absence.setStudent_absence(true);
            absence.setExcused_absence(true);
        } else {
            absence.setStudent_absence(true);
            absence.setExcused_absence(false);
        }

        return absence;
    }

}
