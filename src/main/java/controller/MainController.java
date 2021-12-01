package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import routings.GoToLoginScreen;

import java.io.IOException;

public class MainController {
    @FXML
    AnchorPane pane;

    public void initialize() throws IOException {
        loadLoginScreen();
    }

    public void setScreen(AnchorPane pane) {
        this.pane.getChildren().clear();
        this.pane.getChildren().add(pane);
    }

    public void loadLoginScreen() throws IOException {
        new GoToLoginScreen().runThis(this);
    }
}
