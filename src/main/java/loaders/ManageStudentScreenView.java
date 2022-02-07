package loaders;

import DAO.DAOClass;
import DAO.DAOGuardian;
import DAO.DAOStudent;
import DAO.DAOUser;
import com.jfoenix.controls.JFXButton;
import enumTypes.DatabaseTablesName;
import features.GetMaxID;
import features.GetNameAndSurnameByTableID;
import javafx.event.EventHandler;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Guardian;
import model.Student;
import model.User;
import variables.MainText;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ManageStudentScreenView {
    public static boolean editing;
    public static int studentID;

    public static void view(AnchorPane mainAnchor, ScrollPane scroll, AnchorPane scrollAnchor, int classID) throws SQLException {
        scroll.setVvalue(0);
        scrollAnchor.setPrefHeight(544);

        scrollAnchor.getChildren().clear();
        mainAnchor.getChildren().clear();

        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        mainAnchor.getChildren().add(MainText.main);

        MainText.main.setText("Manage student");

        JFXButton getBackButton = CreateRightCornerButton.create(38, "Get back to students");
        EventHandler<MouseEvent> getBack = e -> {
            try {
                mainAnchor.getChildren().remove(getBackButton);
                LoadAllClassStudents.load(mainAnchor, scroll, scrollAnchor, classID, "YOUR_CLASS");
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        };
        getBackButton.addEventHandler(MouseEvent.MOUSE_CLICKED, getBack);
        mainAnchor.getChildren().add(getBackButton);

        if (editing) {
            JFXButton deleteStudent = CreateRightCornerButton.create(65, "Delete");
            EventHandler<MouseEvent> delete = e -> {
                try {
                    Student student = new DAOStudent().get(studentID).get(0);
                    User user = new DAOUser().getByStudentID(studentID).get(0);
                    new DAOStudent().delete(student);
                    new DAOUser().delete(user);
                    mainAnchor.getChildren().remove(getBackButton);
                    LoadAllClassStudents.load(mainAnchor, scroll, scrollAnchor, classID, "YOUR_CLASS");
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            };
            deleteStudent.addEventHandler(MouseEvent.MOUSE_CLICKED, delete);
            scrollAnchor.getChildren().add(deleteStudent);
        }

        createText(79, "Name: ", scrollAnchor);
        TextField nameTextField = createTextField(138, 48, 633);
        scrollAnchor.getChildren().add(nameTextField);
        createLine(323, 227, -185, -134, 448, -133, scrollAnchor);

        createText(140, "Surname: ", scrollAnchor);
        TextField surnameTextField = createTextField(170, 109, 602);
        scrollAnchor.getChildren().add(surnameTextField);
        createLine(323, 227, -151, -73, 449, -73, scrollAnchor);

        createText(197, "Email: ", scrollAnchor);
        TextField emailTextField = createTextField(138, 166, 636);
        scrollAnchor.getChildren().add(emailTextField);
        createLine(323, 227, -185, -15, 451, -15, scrollAnchor);

        createText(254, "Date of birth: ", scrollAnchor);
        DatePicker datePicker = new DatePicker();
        datePicker.setLayoutX(209);
        datePicker.setLayoutY(229);
        datePicker.setPrefSize(231, 34);
        scrollAnchor.getChildren().add(datePicker);
        createLine(313, 217, -101, 44, 127, 44, scrollAnchor);

        createText(315, "Class: ", scrollAnchor);
        TextField classTextField = createTextField(138, 284, 202);
        classTextField.setText(new DAOClass().get(classID).get(0).getClass_name());
        classTextField.setEditable(false);
        scrollAnchor.getChildren().add(classTextField);
        createLine(323, 227, -185, 103, 17, 103, scrollAnchor);

        if (editing) {
            Student student = new DAOStudent().get(studentID).get(0);
            User studentUser = new DAOUser().getByStudentID(student.getStudent_id()).get(0);

            nameTextField.setText(student.getStudent_name());
            surnameTextField.setText(student.getStudent_surname());
            emailTextField.setText(studentUser.getUser_email());
            datePicker.setValue(student.getStudent_date_of_birth());
        }

        JFXButton saveButton = CreateRightCornerButton.create(20, "Save");
        EventHandler<MouseEvent> save = e -> {
            try {
                String name = nameTextField.getText();
                String surname = surnameTextField.getText();
                String email = emailTextField.getText();
                LocalDate dateOfBirth = datePicker.getValue();
                List<Student> studentsWithSameNameAndSurname = new DAOStudent().getAllWithSameNameAndSurname(name, surname);
                if (name.length() > 0 && surname.length() > 0 && email.length() > 0 && email.length() <= 50
                && surname.length() <= 50 && name.length() <= 50) {
                    if (dateOfBirth != null) {
                        if (editing) {
                            Student updatedStudent = new DAOStudent().get(studentID).get(0);
                            User updatedUser = new DAOUser().getByStudentID(studentID).get(0);
                            updatedStudent.setStudent_name(name);
                            updatedStudent.setStudent_surname(surname);
                            updatedStudent.setStudent_date_of_birth(dateOfBirth);
                            updatedUser.setUser_email(email);
                            new DAOUser().update(updatedUser);
                            new DAOStudent().update(updatedStudent);
                            mainAnchor.getChildren().remove(getBackButton);
                            LoadAllClassStudents.load(mainAnchor, scroll, scrollAnchor, classID, "YOUR_CLASS");
                        } else {
                            if (studentsWithSameNameAndSurname.size() == 0) {
                                User newUser = new User(GetMaxID.get(DatabaseTablesName.USER) + 1,
                                        email, "zxcv", "STUDENT");
                                Student newStudent = new Student(GetMaxID.get(DatabaseTablesName.STUDENT) + 1,
                                        newUser.getUser_id(), 0, classID, name, surname, dateOfBirth);
                                new DAOUser().save(newUser);
                                new DAOStudent().save(newStudent);
                                mainAnchor.getChildren().remove(getBackButton);
                                LoadAllClassStudents.load(mainAnchor, scroll, scrollAnchor, classID, "YOUR_CLASS");
                            }
                        }
                    }
                }
            } catch (Exception ignored) {
            }
        };
        saveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, save);
        scrollAnchor.getChildren().add(saveButton);

        if (editing) {
            createText(394, "Guardians", scrollAnchor);

            JFXButton addGuardianButton = CreateRightCornerButton.create(370, "Add guardian");
            EventHandler<MouseEvent> addGuardian = e -> {
                try {
                    ManageGuardianScreenView.editing = false;
                    ManageGuardianScreenView.view(mainAnchor, scroll, scrollAnchor, -1, classID);
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            };
            addGuardianButton.addEventHandler(MouseEvent.MOUSE_CLICKED, addGuardian);
            scrollAnchor.getChildren().add(addGuardianButton);

            int YPosition = 418;
            List<Guardian> studentGuardians = new DAOGuardian().getByStudentID(studentID);

            for (int i = 0; i < studentGuardians.size(); i++) {
                CreateLine.create(scrollAnchor, YPosition);

                AnchorPane guardianPane = new AnchorPane();
                guardianPane.setLayoutX(34);
                guardianPane.setLayoutY(YPosition + 1);
                guardianPane.setPrefWidth(919);
                guardianPane.setPrefHeight(73);
                guardianPane.setId(Integer.toString(studentGuardians.get(i).getGuardian_id()));
                scrollAnchor.getChildren().add(guardianPane);

                String nameAndSurname = GetNameAndSurnameByTableID.getGuardian(studentGuardians.get(i).getGuardian_id());
                createText(43, nameAndSurname, guardianPane);

                JFXButton editGuardianButton = new JFXButton();
                editGuardianButton.setLayoutX(718);
                editGuardianButton.setLayoutY(20);
                editGuardianButton.setPrefSize(87, 35);
                editGuardianButton.setText("Edit");
                editGuardianButton.setFont(Font.font("Calibri", 20));
                editGuardianButton.setTextFill(Color.rgb(255, 255, 255));
                editGuardianButton.setStyle("-fx-background-color: #3c56bc; -fx-background-radius: 10; -fx-border-radius: 10;");
                EventHandler<MouseEvent> editGuardian = e -> {
                    try {
                        ManageGuardianScreenView.editing = true;
                        ManageGuardianScreenView.view(mainAnchor, scroll, scrollAnchor, Integer.parseInt(guardianPane.getId()), classID);
                    } catch (SQLException exception) {
                        exception.printStackTrace();
                    }
                };
                editGuardianButton.addEventHandler(MouseEvent.MOUSE_CLICKED, editGuardian);
                guardianPane.getChildren().add(editGuardianButton);

                JFXButton deleteGuardianButton = new JFXButton();
                deleteGuardianButton.setLayoutX(814);
                deleteGuardianButton.setLayoutY(20);
                deleteGuardianButton.setPrefSize(87, 35);
                deleteGuardianButton.setText("Delete");
                deleteGuardianButton.setFont(Font.font("Calibri", 20));
                deleteGuardianButton.setTextFill(Color.rgb(255, 255, 255));
                deleteGuardianButton.setStyle("-fx-background-color: #3c56bc; -fx-background-radius: 10; -fx-border-radius: 10;");
                EventHandler<MouseEvent> deleteGuardian = e -> {
                    try {
                        Guardian guardian = new DAOGuardian().get(Integer.parseInt(guardianPane.getId())).get(0);
                        new DAOGuardian().delete(guardian);
                        mainAnchor.getChildren().remove(getBackButton);
                        ManageStudentScreenView.editing = true;
                        ManageStudentScreenView.view(mainAnchor, scroll, scrollAnchor, classID);
                    } catch (SQLException exception) {
                        exception.printStackTrace();
                    }
                };
                deleteGuardianButton.addEventHandler(MouseEvent.MOUSE_CLICKED, deleteGuardian);
                guardianPane.getChildren().add(deleteGuardianButton);

                YPosition += 74;
            }

            CreateLine.create(scrollAnchor, YPosition);

            if (YPosition >= 544) {
                scrollAnchor.setPrefHeight(YPosition + 124);
                scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            }
        }
    }

    private static void createText(int YPosition, String textValue, AnchorPane paneToAdd) {
        Text text = new Text(70, YPosition, textValue);
        text.setFont(Font.font("Calibri", 25));
        text.setFill(Color.rgb(60, 86, 160));
        paneToAdd.getChildren().add(text);
    }

    private static void createLine(int XPosition, int YPosition, int startX, int startY, int endX, int endY, AnchorPane paneToAdd) {
        Line line = new Line();
        line.setLayoutX(XPosition);
        line.setLayoutY(YPosition);
        line.setStartX(startX);
        line.setStartY(startY);
        line.setEndX(endX);
        line.setEndY(endY);
        line.setStrokeWidth(2);
        line.setStroke(Color.rgb(60, 86, 160));

        paneToAdd.getChildren().add(line);
    }

    private static TextField createTextField(int XPosition, int YPosition, int prefWidth) {
        TextField textField = new TextField();
        textField.setLayoutX(XPosition);
        textField.setLayoutY(YPosition);
        textField.setPrefSize(prefWidth, 46);
        textField.setFont(Font.font("Calibri", 25));
        textField.setStyle("-fx-background-color: white");

        return textField;
    }
}
