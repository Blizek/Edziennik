package loaders;

import DAO.DAONotes;
import DAO.DAOTeacher;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import controller.DeleteNoteController;
import controller.ManageNoteController;
import features.*;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.*;
import routings.DeleteNoteMain;
import routings.ManageNoteMain;
import variables.MainText;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreateNoteBox {
    public static void create(AnchorPane mainAnchor, ScrollPane scroll, AnchorPane scrollAnchor, int studentID) throws SQLException {
        scroll.setVvalue(0);
        scrollAnchor.setPrefHeight(544);

        User user = GetUser.get();

        scrollAnchor.getChildren().clear();

        String studentName = GetNameAndSurnameByTableID.getStudent(studentID);

        MainText.main.setText(studentName + "'s notes");

        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        List<Notes> allNotesToShow = new ArrayList<>();

        AnchorPane allNotesPane = new AnchorPane();
        allNotesPane.setPrefSize(991, 172);
        allNotesPane.setLayoutX(0);
        allNotesPane.setLayoutY(86);
        scrollAnchor.getChildren().add(allNotesPane);

        JFXRadioButton positiveNotesButton = createRadioButton(199, "Positive notes");
        scrollAnchor.getChildren().add(positiveNotesButton);

        JFXRadioButton negativeNotesButton = createRadioButton(493, "Negative notes");
        scrollAnchor.getChildren().add(negativeNotesButton);

        if (user.getUser_role().equals("TEACHER")) {
            JFXButton getBackButton = CreateRightCornerButton.create(20, "Get back to students");
            EventHandler<MouseEvent> getBack = e -> {
                try {
                    int classID = LoadAllClassStudents.staticClassID;
                    LoadAllClassStudents.load(mainAnchor, scroll, scrollAnchor, classID, false);
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            };
            getBackButton.addEventHandler(MouseEvent.MOUSE_CLICKED, getBack);
            scrollAnchor.getChildren().add(getBackButton);

            JFXButton addNoteButton = CreateRightCornerButton.create(65, "Add note");
            EventHandler<MouseEvent> addNote = e -> {
                try {
                    ManageNoteController.saving = true;
                    ManageNoteController.student_id = studentID;
                    ManageNoteController.teacher_id = new DAOTeacher().getByUserID(user.getUser_id()).get(0).getTeacher_id();
                    new ManageNoteMain().runThis();
                } catch (IOException | SQLException ioException) {
                    ioException.printStackTrace();
                }
            };
            addNoteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, addNote);
            scrollAnchor.getChildren().add(addNoteButton);
        }

        JFXButton refreshButton = CreateRightCornerButton.create(38, "Refresh");
        EventHandler<MouseEvent> refresh = e -> {
            try {
                allNotesToShow.clear();
                if (positiveNotesButton.isSelected()) {
                    List<Notes> positiveNotes;
                    if (!user.getUser_role().equals("TEACHER"))
                        positiveNotes = new DAONotes().getStudentNotes(studentID, ">=");
                    else {
                        int teacherID = new DAOTeacher().getByUserID(user.getUser_id()).get(0).getTeacher_id();
                        positiveNotes = new DAONotes().getStudentNotesFromTeacher(studentID, teacherID, ">=");
                    }
                    allNotesToShow.addAll(positiveNotes);
                } if (negativeNotesButton.isSelected()) {
                    List<Notes> negativeNotes;
                    if (!user.getUser_role().equals("TEACHER"))
                        negativeNotes = new DAONotes().getStudentNotes(studentID, "<");
                    else {
                        int teacherID = new DAOTeacher().getByUserID(user.getUser_id()).get(0).getTeacher_id();
                        negativeNotes = new DAONotes().getStudentNotesFromTeacher(studentID, teacherID, "<");
                    }
                    allNotesToShow.addAll(negativeNotes);
                }
                createNotes(scrollAnchor, scroll, allNotesPane, allNotesToShow, user);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        };
        refreshButton.addEventHandler(MouseEvent.MOUSE_CLICKED, refresh);
        mainAnchor.getChildren().add(refreshButton);

        createNotes(scrollAnchor, scroll, allNotesPane, allNotesToShow, user);
    }

    private static void createNotes(AnchorPane scrollAnchor, ScrollPane scroll, AnchorPane anchor, List<Notes> notesToShow, User user) throws SQLException {
        anchor.getChildren().clear();

        int XPosition;
        int YPosition = 3;

        if (user.getUser_role().equals("TEACHER")) {
            YPosition = 42;
        }

        for (int i = 0; i < notesToShow.size(); i++) {
            if (i % 2 == 0) XPosition = 61;
            else XPosition = 560;

            AnchorPane notePane = new AnchorPane();

            notePane.setLayoutX(XPosition);
            notePane.setLayoutY(YPosition);
            notePane.setPrefWidth(371);
            notePane.setPrefHeight(198);
            notePane.setId(String.valueOf(notesToShow.get(i).getNote_id()));
            notePane.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-background-color: #3c56bc;");

            fillNoteBox(notesToShow.get(i), notePane);

            if (user.getUser_role().equals("TEACHER")) {
                createTeacherManageNoteButtons(notePane, notesToShow.get(i));
            }

            anchor.getChildren().add(notePane);

            if (i % 2 == 1) YPosition += 230;
        }

        if (YPosition >= 264) {
            anchor.setPrefHeight(YPosition + 280);
            scrollAnchor.setPrefHeight(YPosition + 366);
            scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        }
    }

    private static JFXRadioButton createRadioButton(int XPosition, String textValue) {
        JFXRadioButton radioButton = new JFXRadioButton();
        radioButton.setLayoutX(XPosition);
        radioButton.setLayoutY(27);
        radioButton.setSelectedColor(Color.rgb(60, 86, 188));
        radioButton.setUnSelectedColor(Color.rgb(90, 90, 90));
        radioButton.setText(textValue);
        radioButton.setFont(Font.font("Calibri", 20));
        radioButton.setTextFill(Color.rgb(60, 86, 188));

        return radioButton;
    }

    private static void fillNoteBox(Notes note, AnchorPane box) throws SQLException {
        TextAlignment textAlignment = TextAlignment.LEFT;
        Color textColor = Color.rgb(255, 255, 255);
        int wrappingWidth = 322;

        int noteValue = note.getNote_value();
        String noteDate = FormatDay.formatDateAndHour(note.getNote_date().toString());
        String noteDescription = note.getNote_description();
        String teacherName = GetNameAndSurnameByTableID.getTeacher(note.getTeacher_id());
        String studentName = GetNameAndSurnameByTableID.getStudent(note.getStudent_id());

        Text noteValueText = new Text(24, 43, "Value: " + noteValue);
        noteValueText.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        noteValueText.setTextAlignment(textAlignment);
        noteValueText.setFill(textColor);
        noteValueText.setWrappingWidth(wrappingWidth);
        box.getChildren().add(noteValueText);

        Text noteDateText = new Text(140, 43, "Date: " + noteDate);
        noteDateText.setFont(Font.font("Calibri", 20));
        noteDateText.setTextAlignment(textAlignment);
        noteDateText.setFill(textColor);
        box.getChildren().add(noteDateText);

        Text noteDescriptionText = new Text(24, 84, "Description: " + noteDescription);
        noteDescriptionText.setFont(Font.font("Calibri", 20));
        noteDescriptionText.setTextAlignment(textAlignment);
        noteDescriptionText.setFill(textColor);
        noteDescriptionText.setWrappingWidth(wrappingWidth);
        box.getChildren().add(noteDescriptionText);

        Text noteTeacherText = new Text(24, 127, "From: " + teacherName);
        noteTeacherText.setFont(Font.font("Calibri", 20));
        noteTeacherText.setTextAlignment(textAlignment);
        noteTeacherText.setFill(textColor);
        noteTeacherText.setWrappingWidth(wrappingWidth);
        box.getChildren().add(noteTeacherText);

        Text noteStudentText = new Text(24, 174, "To: " + studentName);
        noteStudentText.setFont(Font.font("Calibri", 20));
        noteStudentText.setTextAlignment(textAlignment);
        noteStudentText.setFill(textColor);
        noteStudentText.setWrappingWidth(wrappingWidth);
        box.getChildren().add(noteStudentText);
    }

    private static void createTeacherManageNoteButtons(AnchorPane pane, Notes note) {
        JFXButton editButton = new JFXButton();
        editButton.setLayoutX(301);
        editButton.setLayoutY(119);
        editButton.setPrefSize(70, 37);
        editButton.setText("Edit");
        editButton.setFont(Font.font("Calibri", 15));
        editButton.setTextFill(Color.rgb(60, 86, 188));
        editButton.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 5; -fx-border-radius: 5;");

        EventHandler<MouseEvent> editMark = e -> {
            try {
                ManageNoteController.editingNotes = note;
                ManageNoteController.saving = false;
                new ManageNoteMain().runThis();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        };
        editButton.addEventHandler(MouseEvent.MOUSE_CLICKED, editMark);

        pane.getChildren().add(editButton);


        JFXButton deleteButton = new JFXButton();
        deleteButton.setLayoutX(301);
        deleteButton.setLayoutY(161);
        deleteButton.setPrefSize(70, 37);
        deleteButton.setText("Delete");
        deleteButton.setFont(Font.font("Calibri", 15));
        deleteButton.setTextFill(Color.rgb(60, 86, 188));
        deleteButton.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 5; -fx-border-radius: 5;");

        EventHandler<MouseEvent> deleteGrade = e -> {
            DeleteNoteController.note = note;
            try {
                new DeleteNoteMain().runThis();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        };
        deleteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, deleteGrade);

        pane.getChildren().add(deleteButton);
    }
}
