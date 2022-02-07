package loaders;

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
import model.User;
import variables.MainText;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ManageGuardianScreenView {
    public static boolean editing;

    public static void view(AnchorPane mainAnchor, ScrollPane scroll, AnchorPane scrollAnchor, int guardianID, int classID) throws SQLException {
        scroll.setVvalue(0);
        scrollAnchor.setPrefHeight(544);

        scrollAnchor.getChildren().clear();

        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        MainText.main.setText("Manage guardian");

        JFXButton getBackButton = CreateRightCornerButton.create(38, "Get back to student");
        EventHandler<MouseEvent> getBack = e -> {
            try {
                mainAnchor.getChildren().remove(getBackButton);
                ManageStudentScreenView.view(mainAnchor, scroll, scrollAnchor, classID);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        };
        getBackButton.addEventHandler(MouseEvent.MOUSE_CLICKED, getBack);
        mainAnchor.getChildren().add(getBackButton);

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

        createText(315, "Student: ", scrollAnchor);
        TextField studentTextField = createTextField(162, 284, 616);
        studentTextField.setText(GetNameAndSurnameByTableID.getStudent(new DAOStudent().get
                (ManageStudentScreenView.studentID).get(0).getStudent_id()));
        studentTextField.setEditable(false);
        scrollAnchor.getChildren().add(studentTextField);
        createLine(323, 227, -161, 103, 455, 103, scrollAnchor);

        if (editing) {
            Guardian guardian = new DAOGuardian().get(guardianID).get(0);
            User guardianUser = new DAOUser().getByGuardianID(guardian.getGuardian_id()).get(0);

            nameTextField.setText(guardian.getGuardian_name());
            surnameTextField.setText(guardian.getGuardian_surname());
            emailTextField.setText(guardianUser.getUser_email());
            datePicker.setValue(guardian.getGuardian_date_of_birth());
        }

        JFXButton saveButton = CreateRightCornerButton.create(20, "Save");
        EventHandler<MouseEvent> save = e -> {
            try {
                String name = nameTextField.getText();
                String surname = surnameTextField.getText();
                String email = emailTextField.getText();
                LocalDate dateOfBirth = datePicker.getValue();
                List<Guardian> guardiansWithSameNameAndSurname = new DAOGuardian().getGuardiansWithSameNameAndSurname(name, surname);
                if (name.length() > 0 && surname.length() > 0 && email.length() > 0 && email.length() <= 50
                        && surname.length() <= 50 && name.length() <= 50) {
                    if (dateOfBirth != null) {
                        if (editing) {
                            Guardian updatedGuardian = new DAOGuardian().get(guardianID).get(0);
                            User updatedUser = new DAOUser().getByGuardianID(guardianID).get(0);
                            updatedGuardian.setGuardian_name(name);
                            updatedGuardian.setGuardian_surname(surname);
                            updatedGuardian.setGuardian_date_of_birth(dateOfBirth);
                            updatedUser.setUser_email(email);
                            new DAOUser().update(updatedUser);
                            new DAOGuardian().update(updatedGuardian);
                            mainAnchor.getChildren().remove(getBackButton);
                            ManageStudentScreenView.editing = true;
                            ManageStudentScreenView.view(mainAnchor, scroll, scrollAnchor, classID);
                        } else {
                            if (guardiansWithSameNameAndSurname.size() == 0) {
                                User newUser = new User(GetMaxID.get(DatabaseTablesName.USER) + 1,
                                        email, "zxcv", "GUARDIAN");
                                Guardian newGuardian = new Guardian(GetMaxID.get(DatabaseTablesName.GUARDIAN) + 1,
                                        newUser.getUser_id(), ManageStudentScreenView.studentID, name, surname, dateOfBirth);
                                new DAOUser().save(newUser);
                                new DAOGuardian().save(newGuardian);
                                mainAnchor.getChildren().remove(getBackButton);
                                ManageStudentScreenView.editing = true;
                                ManageStudentScreenView.view(mainAnchor, scroll, scrollAnchor, classID);
                            }
                        }
                    }
                }
            } catch (Exception ignored) {
            }
        };
        saveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, save);
        scrollAnchor.getChildren().add(saveButton);
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
