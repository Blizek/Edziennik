package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class MainController {
    @FXML
    AnchorPane pane;

    public void initialize() {
        setLoginScreen();
    }

    public void setScreen(AnchorPane pane) {
        this.pane.getChildren().clear();
        this.pane.getChildren().add(pane);
    }

    public void setLoginScreen() {
        System.out.println("Przejście do panelu logowania");
    }
}
