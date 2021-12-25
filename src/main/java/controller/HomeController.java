package controller;

import features.ClearRememberMeData;
import loaders.*;
import features.GetUser;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.User;
import org.json.simple.parser.ParseException;
import routings.GoToLoginScreen;

import java.io.IOException;
import java.sql.SQLException;

public class HomeController {
    @FXML
    Text loggedData;

    @FXML
    ScrollPane scroll;

    @FXML
    AnchorPane scrollAnchorPane;

    User user;

    private MainController mainController;

    public void initialize() throws SQLException, IOException, ParseException {
        user = GetUser.get();
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        loggedData.setText("Logged in as: " + user.getUser_email() + " (" + user.getUser_role() + ")");
        switch (user.getUser_role()) {
            case "STUDENT" -> loadStudentView();
            case "TEACHER" -> loadTeacherView();
            case "PRINCIPAL" -> loadPrincipalView();
            case "GUARDIAN" -> loadGuardianView();
            case "ADMIN" -> loadAdminView();
        }
        CreateFooter.create(scrollAnchorPane);

    }
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void goToLoginScreen() {
        ClearRememberMeData.clearData();
        new GoToLoginScreen().runThis(mainController);
    }

    private void loadStudentView() {
        CreateManageBox.create(scrollAnchorPane, "Student");
        CreateLessonsSchedule.create(scrollAnchorPane, user.getUser_role());
        CreateNearestExams.create(scrollAnchorPane, user.getUser_role(), scrollAnchorPane.getPrefHeight());
    }

    private void loadTeacherView() {
        CreateManageBox.create(scrollAnchorPane, "Manage students");
        CreateLessonsSchedule.create(scrollAnchorPane, user.getUser_role());
        CreateNearestExams.create(scrollAnchorPane, user.getUser_role(), scrollAnchorPane.getPrefHeight());
    }

    private void loadPrincipalView() {
        CreateManageBox.create(scrollAnchorPane, "Manage");
        CreateLessonsSchedule.create(scrollAnchorPane, user.getUser_role());
        CreateNearestExams.create(scrollAnchorPane, user.getUser_role(), scrollAnchorPane.getPrefHeight());
    }

    private void loadGuardianView() {
        CreateManageBox.create(scrollAnchorPane, "Student");
        CreateLessonsSchedule.create(scrollAnchorPane, user.getUser_role());
        CreateNearestExams.create(scrollAnchorPane, user.getUser_role(), scrollAnchorPane.getPrefHeight());
    }

    private void loadAdminView() throws IOException, ParseException {
        CreateManageBox.create(scrollAnchorPane, "Manage");
        CreateCheckingPasswordFormatBox.create(scrollAnchorPane);
    }
}
