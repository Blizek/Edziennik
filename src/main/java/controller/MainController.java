package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import routings.GoToLoginScreen;

public class MainController {
    @FXML
    AnchorPane pane;

    public void initialize() {
        loadLoginScreen();
    }

    public void setScreen(AnchorPane pane) {
        this.pane.getChildren().clear();
        this.pane.getChildren().add(pane);
    }

    public void loadLoginScreen() {
        new GoToLoginScreen().runThis(this);
    }
}
