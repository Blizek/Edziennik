package loaders;

import DAO.DAOClass;
import com.jfoenix.controls.JFXButton;
import enumTypes.DatabaseTablesName;
import features.GetMaxID;
import features.GetNameAndSurnameByTableID;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Class;
import routings.ChooseSupervisingTeacherMain;
import variables.MainText;
import variables.SupervisingTeacherID;

import java.io.IOException;
import java.sql.SQLException;

public class ManageClassScreenView {
    public static void view(AnchorPane mainAnchor, ScrollPane scroll, AnchorPane scrollAnchor, int classID) throws SQLException {
        scroll.setVvalue(0);
        scrollAnchor.setPrefHeight(544);

        scrollAnchor.getChildren().clear();
        mainAnchor.getChildren().clear();

        mainAnchor.getChildren().add(MainText.main);
        MainText.main.setText("Manage class");

        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        JFXButton getBackButton = CreateRightCornerButton.create(38, "Get back to classes");
        EventHandler<MouseEvent> getBack = e -> {
            try {
                mainAnchor.getChildren().remove(getBackButton);
                if (classID == -1) AllClassesManageScreenView.view(mainAnchor, scroll, scrollAnchor);
                else ClassManageScreenView.view(mainAnchor, scroll, scrollAnchor, classID);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        };
        getBackButton.addEventHandler(MouseEvent.MOUSE_CLICKED, getBack);
        mainAnchor.getChildren().add(getBackButton);

        JFXButton refreshButton = CreateRightCornerButton.create(0, "Refresh");
        EventHandler<MouseEvent> refresh = e -> {
            try {
                mainAnchor.getChildren().remove(getBackButton);
                view(mainAnchor, scroll, scrollAnchor, classID);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        };
        refreshButton.addEventHandler(MouseEvent.MOUSE_CLICKED, refresh);
        scrollAnchor.getChildren().add(refreshButton);

        createText(59, "Class name: ", scrollAnchor);
        createText(121, "Supervising teacher name: ", scrollAnchor);

        TextField classNameField = createTextField(192, 28, 326);
        scrollAnchor.getChildren().add(classNameField);
        TextField supervisingTeacherNameField = createTextField(340, 90, 445);
        supervisingTeacherNameField.setEditable(false);
        scrollAnchor.getChildren().add(supervisingTeacherNameField);

        createCustomLine(292, 74, 226, scrollAnchor);
        createCustomLine(441, 136, 344, scrollAnchor);

        if (classID != -1) {
            Class editingClass = new DAOClass().get(classID).get(0);
            supervisingTeacherNameField.setText(GetNameAndSurnameByTableID.getTeacher(editingClass.getClass_supervising_teacher()));
            classNameField.setText(editingClass.getClass_name());
        }

        if (SupervisingTeacherID.getID() != -1) {
            supervisingTeacherNameField.setText(GetNameAndSurnameByTableID.getTeacher(SupervisingTeacherID.getID()));
        }

        JFXButton changeButton = new JFXButton();
        changeButton.setLayoutX(834);
        changeButton.setLayoutY(88);
        changeButton.setPrefSize(121, 51);
        changeButton.setText("Change");
        changeButton.setFont(Font.font("Calibri", 25));
        changeButton.setTextFill(Color.rgb(255, 255, 255));
        changeButton.setStyle("-fx-background-color: #3c56bc; -fx-background-radius: 10; -fx-border-radius: 10;");
        EventHandler<MouseEvent> changeSupervisingTeacher = e -> {
            try {
                new ChooseSupervisingTeacherMain().runThis();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        };
        changeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, changeSupervisingTeacher);
        scrollAnchor.getChildren().add(changeButton);

        JFXButton saveButton = CreateRightCornerButton.create(45, "Save");
        EventHandler<MouseEvent> save = e -> {
            try {
                if (classID != -1) {
                    String className = classNameField.getText();
                    int supervisionTeacherID = SupervisingTeacherID.getID();
                    Class updatedClass = new Class(classID, supervisionTeacherID, className);
                    new DAOClass().update(updatedClass);
                    ClassManageScreenView.view(mainAnchor, scroll, scrollAnchor, classID);
                } else {
                    if (SupervisingTeacherID.getID() != -1 && classNameField.getText().length() > 0) {
                        int id = GetMaxID.get(DatabaseTablesName.CLASS) + 1;
                        String className = classNameField.getText();
                        int supervisionTeacherID = SupervisingTeacherID.getID();
                        Class newClass = new Class(id, supervisionTeacherID, className);
                        new DAOClass().save(newClass);
                        AllClassesManageScreenView.view(mainAnchor, scroll, scrollAnchor);
                    }
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        };
        saveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, save);
        scrollAnchor.getChildren().add(saveButton);
    }

    private static void createText(int YPosition, String textValue, AnchorPane paneToAdd) {
        Text text = new Text(65, YPosition, textValue);
        text.setFont(Font.font("Calibri", 25));
        text.setFill(Color.rgb(60, 86, 160));
        paneToAdd.getChildren().add(text);
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
}
