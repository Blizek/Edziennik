package controller;

import features.ClearRememberMeData;
import features.CreateFooter;
import features.GetUser;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
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

    private MainController mainController;

    User user;

    public void initialize() throws SQLException {
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        user = GetUser.get();
        loggedData.setText("Logged in as: " + user.getUser_email() + " (" + user.getUser_role() + ")");
        new CreateFooter().create(scrollAnchorPane);
    }
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void goToLoginScreen() {
        new ClearRememberMeData().clearData();
        new GoToLoginScreen().runThis(mainController);
    }
}
