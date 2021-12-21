package controller;

import features.ClearRememberMeData;
import loaders.CreateFooter;
import loaders.CreateLessonsSchedule;
import loaders.CreateManageBox;
import features.GetUser;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import loaders.CreateNearestExams;
import model.User;
import routings.GoToLoginScreen;

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

    double anchorPaneHeight = 728;

    public void initialize() throws SQLException {
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
        new CreateFooter().create(scrollAnchorPane);

    }
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void goToLoginScreen() {
        new ClearRememberMeData().clearData();
        new GoToLoginScreen().runThis(mainController);
    }

    private void loadStudentView() {
        new CreateManageBox().create(scrollAnchorPane, "Student");
        new CreateLessonsSchedule().create(scrollAnchorPane, user.getUser_role());
        new CreateNearestExams().create(scrollAnchorPane, user.getUser_role(), scrollAnchorPane.getPrefHeight());
    }

    private void loadTeacherView() {
        new CreateManageBox().create(scrollAnchorPane, "Manage students");
        new CreateLessonsSchedule().create(scrollAnchorPane, user.getUser_role());
        new CreateNearestExams().create(scrollAnchorPane, user.getUser_role(), scrollAnchorPane.getPrefHeight());
    }

    private void loadPrincipalView() {
        new CreateManageBox().create(scrollAnchorPane, "Manage");
        new CreateLessonsSchedule().create(scrollAnchorPane, user.getUser_role());
        new CreateNearestExams().create(scrollAnchorPane, user.getUser_role(), scrollAnchorPane.getPrefHeight());
    }

    private void loadGuardianView() {
        new CreateManageBox().create(scrollAnchorPane, "Student");
        new CreateLessonsSchedule().create(scrollAnchorPane, user.getUser_role());
        new CreateNearestExams().create(scrollAnchorPane, user.getUser_role(), scrollAnchorPane.getPrefHeight());
    }

    private void loadAdminView() {
        new CreateManageBox().create(scrollAnchorPane, "Manage");
    }
}
