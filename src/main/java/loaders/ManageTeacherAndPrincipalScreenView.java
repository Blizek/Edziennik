package loaders;

import DAO.DAOPrincipal;
import DAO.DAOSchoolSubject;
import DAO.DAOTeacher;
import DAO.DAOUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import enumTypes.DatabaseTablesName;
import features.GetMaxID;
import features.GetSubjectID;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Principal;
import model.SchoolSubject;
import model.Teacher;
import model.User;
import variables.MainText;

import java.sql.SQLException;
import java.util.List;

public class ManageTeacherAndPrincipalScreenView {
    public static boolean teacher;
    public static boolean principal;
    private static TextField subjectName;

    public static void view(AnchorPane mainAnchor, ScrollPane scroll, AnchorPane scrollAnchor, int ID) throws SQLException {
        int subjectID;

        scroll.setVvalue(0);
        scrollAnchor.setPrefHeight(544);

        scrollAnchor.getChildren().clear();
        mainAnchor.getChildren().clear();

        mainAnchor.getChildren().add(MainText.main);

        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        AnchorPane teacherPane = new AnchorPane();
        teacherPane.setLayoutX(0);
        teacherPane.setLayoutY(245);
        teacherPane.setPrefSize(991, 200);
        scrollAnchor.getChildren().add(teacherPane);

        JFXButton getBackButton = CreateRightCornerButton.create(38, "Get back to teachers");
        EventHandler<MouseEvent> getBack = e -> {
            try {
                TeachersManageScreenView.view(mainAnchor, scroll, scrollAnchor);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        };
        getBackButton.addEventHandler(MouseEvent.MOUSE_CLICKED, getBack);
        mainAnchor.getChildren().add(getBackButton);

        JFXRadioButton principalButton = createRadioButton(228, "Principal");
        JFXRadioButton teacherButton = createRadioButton(588, "Teacher");

        EventHandler<MouseEvent> setPrincipalView = e -> {
            principal = true;
            teacher = false;
            setRadioCheck(principalButton, teacherButton, principalButton);
            teacherPane.getChildren().clear();
        };
        principalButton.addEventHandler(MouseEvent.MOUSE_CLICKED, setPrincipalView);
        scrollAnchor.getChildren().add(principalButton);

        EventHandler<MouseEvent> setTeacherView = e -> {
            teacher = true;
            principal = false;
            setRadioCheck(principalButton, teacherButton, teacherButton);
            createText(48, "Subject: ", teacherPane);
            createText(48, "Subject: ", teacherPane);
            subjectName = createTextField(189, 16, 772);
            subjectName.setText("");
            teacherPane.getChildren().add(subjectName);
            Line line = new Line();
            line.setLayoutX(0);
            line.setLayoutY(0);
            line.setStartX(189);
            line.setStartY(62);
            line.setEndX(961);
            line.setEndY(62);
            line.setStroke(Color.rgb(60, 86, 160));
            line.setStrokeWidth(2);
            teacherPane.getChildren().add(line);
        };
        teacherButton.addEventHandler(MouseEvent.MOUSE_CLICKED, setTeacherView);
        scrollAnchor.getChildren().add(teacherButton);

        createText(150, "Name: ", scrollAnchor);
        TextField nameField = createTextField(178, 118, 780);
        scrollAnchor.getChildren().add(nameField);
        createCustomLine(279, 164, 679, scrollAnchor);

        createText(218, "Surname: ", scrollAnchor);
        TextField surnameField = createTextField(214, 186, 743);
        scrollAnchor.getChildren().add(surnameField);
        createCustomLine(315, 232, 642, scrollAnchor);

        if (teacher) {
            teacherButton.setSelected(true);
            Teacher teacher = new DAOTeacher().getByUserID(ID).get(0);
            nameField.setText(teacher.getTeacher_name());
            surnameField.setText(teacher.getTeacher_surname());
            subjectID = teacher.getSubject_id();
            createText(48, "Subject: ", teacherPane);
            subjectName = createTextField(189, 16, 772);
            List<SchoolSubject> subjects = new DAOSchoolSubject().get(subjectID);
            if (subjects.size() == 0) subjectName.setText("");
            else subjectName.setText(subjects.get(0).getSubject_name());
            teacherPane.getChildren().add(subjectName);
            Line line = new Line();
            line.setLayoutX(0);
            line.setLayoutY(0);
            line.setStartX(189);
            line.setStartY(62);
            line.setEndX(961);
            line.setEndY(62);
            line.setStroke(Color.rgb(60, 86, 160));
            line.setStrokeWidth(2);
            teacherPane.getChildren().add(line);
        } else if (principal) {
            principalButton.setSelected(true);
            Principal principal = new DAOPrincipal().getByUserID(ID).get(0);
            nameField.setText(principal.getPrincipal_name());
            surnameField.setText(principal.getPrincipal_surname());
        }

        JFXButton saveButton = CreateRightCornerButton.create(20, "Save");
        EventHandler<MouseEvent> save = e -> {
            try {
                String name = nameField.getText();
                String surname = surnameField.getText();
                String user_email = name.toLowerCase() + "_" + surname.toLowerCase() + "@e-slowacki.kielce.eu";
                List<Principal> principalsWithSameNameAndSurname = new DAOPrincipal().getAllWithSameNameAndSurname(name, surname);
                List<Teacher> teachersWithSameNameAndSurname = new DAOTeacher().getAllWithSameNameAndSurname(name, surname);
                if (principal && principalsWithSameNameAndSurname.size() == 0) {
                    List<Principal> principalWithSuitableID = new DAOPrincipal().getByUserID(ID);
                    if (principalWithSuitableID.size() == 0) {
                        User newUser = new User(GetMaxID.get(DatabaseTablesName.USER) + 1,
                                user_email, "zxcv",
                                "PRINCIPAL");
                        Principal newPrincipal = new Principal(GetMaxID.get(DatabaseTablesName.PRINCIPAL) + 1,
                                newUser.getUser_id(), name, surname);
                        if (new DAOTeacher().getByUserID(ID).size() != 0) {
                            new DAOTeacher().delete(new DAOTeacher().getByUserID(ID).get(0));
                            new DAOUser().delete(new DAOUser().get(ID).get(0));
                        }
                        new DAOUser().save(newUser);
                        new DAOPrincipal().save(newPrincipal);
                    } else {
                        Principal principal = new DAOPrincipal().getByUserID(ID).get(0);
                        principal.setPrincipal_name(name);
                        principal.setPrincipal_surname(surname);
                        new DAOPrincipal().update(principal);
                    }
                    TeachersManageScreenView.view(mainAnchor, scroll, scrollAnchor);
                } else if (teacher && teachersWithSameNameAndSurname.size() == 0) {
                    List<Teacher> teacherWithSuitableID = new DAOTeacher().getByUserID(ID);
                    int databaseSubjectID = GetSubjectID.get(subjectName.getText());
                    if (databaseSubjectID != -1) {
                        if (teacherWithSuitableID.size() == 0) {
                            User newUser = new User(GetMaxID.get(DatabaseTablesName.USER) + 1,
                                    user_email, "zxcv",
                                    "TEACHER");
                            if (new DAOPrincipal().getByUserID(ID).size() != 0) {
                                new DAOPrincipal().delete(new DAOPrincipal().getByUserID(ID).get(0));
                                new DAOUser().delete(new DAOUser().get(ID).get(0));
                            }
                            Teacher newTeacher = new Teacher(GetMaxID.get(DatabaseTablesName.TEACHER) + 1,
                                    newUser.getUser_id(), 0, databaseSubjectID, name, surname);
                            new DAOUser().save(newUser);
                            new DAOTeacher().save(newTeacher);
                        } else {
                            Teacher teacher = new DAOTeacher().getByUserID(ID).get(0);
                            teacher.setSubject_id(databaseSubjectID);
                            teacher.setTeacher_name(name);
                            teacher.setTeacher_surname(surname);
                            new DAOTeacher().update(teacher);
                        }
                        TeachersManageScreenView.view(mainAnchor, scroll, scrollAnchor);
                    }
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        };
        saveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, save);
        scrollAnchor.getChildren().add(saveButton);
    }

    private static JFXRadioButton createRadioButton(int XPosition, String textValue) {
        JFXRadioButton radioButton = new JFXRadioButton();
        radioButton.setLayoutX(XPosition);
        radioButton.setLayoutY(31);
        radioButton.setText(textValue);
        radioButton.setFont(Font.font("Calibri", 25));
        radioButton.setTextFill(Color.rgb(60,86, 160));
        radioButton.setUnSelectedColor(Color.rgb(90, 90, 90));
        radioButton.setSelectedColor(Color.rgb(60, 86, 160));

        return radioButton;
    }

    private static void createText(int YPosition, String textName, AnchorPane paneToAdd) {
        Text text = new Text(90, YPosition, textName);
        text.setFont(Font.font("Calibri", 30));
        text.setFill(Color.rgb(60, 86, 160));
        paneToAdd.getChildren().add(text);
    }

    private static TextField createTextField(int XPosition, int YPosition, int prefWidth) {
        TextField field = new TextField();
        field.setLayoutX(XPosition);
        field.setLayoutY(YPosition);
        field.setPrefSize(prefWidth, 46);
        field.setFont(Font.font("Calibri", 25));
        field.setStyle("-fx-background-color: white");

        return field;
    }

    private static void createCustomLine(int XPosition, int YPosition, int endX, AnchorPane paneToAdd) {
        Line line = new Line();
        line.setLayoutX(XPosition);
        line.setLayoutY(YPosition);
        line.setStartX(-100);
        line.setStartY(0);
        line.setEndX(endX);
        line.setEndY(0);
        line.setStroke(Color.rgb(60, 86, 160));
        line.setStrokeWidth(2);

        paneToAdd.getChildren().add(line);
    }

    private static void setRadioCheck(JFXRadioButton principal, JFXRadioButton teacher, JFXRadioButton toSet) {
        principal.setSelected(false);
        teacher.setSelected(false);
        toSet.setSelected(true);
    }
}
