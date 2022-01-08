package controller;

import features.ClearRememberMeData;
import features.GetAllMailsToUser;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import loaders.*;
import features.GetUser;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.Email;
import model.User;
import org.json.simple.parser.ParseException;
import routings.GoToEmailScreen;
import routings.GoToLoginScreen;
import variables.ListOfReceiversID;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
        ListOfReceiversID.receiversID.clear();
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
        List<Email> notOpenMails = GetAllMailsToUser.getNotOpened(user.getUser_id());
        if (notOpenMails.size() > 0) CreateNotOpenedMailsCount.createNotOpenedMailsCount(notOpenMails.size(), scrollAnchorPane);
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

    public void goToEmailScreen() {
        new GoToEmailScreen().runThis(mainController);
    }
}
