package controller;

import DAO.*;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import loaders.LoadAllPossibleMailReceivers;
import loaders.LoadPossibleReceivers;
import model.*;
import routings.MailWritingMain;
import routings.ManageReceiversMain;
import variables.ListOfReceiversID;

import java.sql.SQLException;
import java.util.List;

public class ManageReceiversController {
    @FXML
    MenuButton lookingRole;

    @FXML
    ScrollPane boxesScroll;

    @FXML
    AnchorPane boxesAnchorPane;

    @FXML
    JFXButton addAll, removeAll;

    public void initialize() {
        boxesScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        boxesScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
    }

    public void getAllReceivers() throws SQLException {
        lookingRole.setText("RECEIVERS");
        LoadAllPossibleMailReceivers.load(boxesAnchorPane);
        addAll.setDisable(true);
        removeAll.setDisable(false);
    }

    public void getAllAdmins() throws SQLException {
        lookingRole.setText("ADMIN");
        boxesAnchorPane.getChildren().clear();
        List<Admin> admins = new DAOAdmin().getAll();
        for (int i = 0; i < admins.size(); i++)
            LoadPossibleReceivers.load(admins.get(i).getUser_id(), 14 + i * 98, false, boxesAnchorPane);
        if (14 + (admins.size() - 1) * 98 >=459)
            boxesAnchorPane.setPrefHeight(14 + admins.size() * 98);
        addAll.setDisable(false);
        removeAll.setDisable(true);
    }

    public void getAllStudents() throws SQLException {
        lookingRole.setText("STUDENT");
        boxesAnchorPane.getChildren().clear();
        List<Student> students = new DAOStudent().getAll();
        for (int i = 0; i < students.size(); i++)
            LoadPossibleReceivers.load(students.get(i).getUser_id(), 14 + i * 98, false, boxesAnchorPane);
        if (14 + (students.size() - 1) * 98 >=459)
            boxesAnchorPane.setPrefHeight(14 + students.size() * 98);
        addAll.setDisable(false);
        removeAll.setDisable(true);
    }

    public void getAllTeachers() throws SQLException {
        lookingRole.setText("TEACHER");
        boxesAnchorPane.getChildren().clear();
        List<Teacher> teachers = new DAOTeacher().getAll();
        for (int i =0; i < teachers.size(); i++)
            LoadPossibleReceivers.load(teachers.get(i).getUser_id(), 14 + i * 98, false, boxesAnchorPane);
        if (14 + (teachers.size() - 1) * 98 >=459)
            boxesAnchorPane.setPrefHeight(14 + teachers.size() * 98);
        addAll.setDisable(false);
        removeAll.setDisable(true);
    }

    public void getAllGuardians() throws SQLException {
        lookingRole.setText("GUARDIAN");
        boxesAnchorPane.getChildren().clear();
        List<Guardian> guardians = new DAOGuardian().getAll();
        for (int i = 0; i < guardians.size(); i++)
            LoadPossibleReceivers.load(guardians.get(i).getUser_id(), 14 + i * 98, false, boxesAnchorPane);
        if (14 + (guardians.size() - 1) * 98 >=459)
            boxesAnchorPane.setPrefHeight(14 + guardians.size() * 98);
        addAll.setDisable(false);
        removeAll.setDisable(true);
    }

    public void getAllPrincipals() throws SQLException {
        lookingRole.setText("PRINCIPAL");
        boxesAnchorPane.getChildren().clear();
        List<Principal> principals = new DAOPrincipal().getAll();
        for (int i = 0; i < principals.size(); i++)
            LoadPossibleReceivers.load(principals.get(i).getUser_id(), 14 + i * 98, false, boxesAnchorPane);
        if (14 + (principals.size() - 1) * 98 >=459)
            boxesAnchorPane.setPrefHeight(14 + principals.size() * 98);
        addAll.setDisable(false);
        removeAll.setDisable(true);
    }

    public void addAllUsers() {
        for (int i = 0; i < LoadPossibleReceivers.panes.size(); i++) {
            AnchorPane actualPane = LoadPossibleReceivers.panes.get(i);
            ListOfReceiversID.addID(Integer.parseInt(actualPane.getId()));
            actualPane.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-background-color: #DBDBDB;");
        }
    }

    public void removeAllUsers() throws SQLException {
        ListOfReceiversID.receiversID.clear();
        getAllReceivers();
    }

    public void closeWindow() throws SQLException {
        new ManageReceiversMain().closeStageBySendingMail();
        MailWritingController.setReceivers(MailWritingController.mailReceivers);
    }
}
