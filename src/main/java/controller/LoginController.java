package controller;

import com.jfoenix.controls.JFXToggleButton;
import features.LoggingAlgorithm;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import routings.GoToForgotPasswordScreen;
import routings.GoToHomeScreen;

import java.sql.SQLException;

public class LoginController {
    @FXML
    TextField userEmail;

    @FXML
    PasswordField userPassword;

    @FXML
    JFXToggleButton rememberMeToggleButton;

    @FXML
    Text wrongDataText;

    private MainController mainController;

    public void setMainController(MainController mainController)
    {
        this.mainController = mainController;
    }

    public void goToForgotPasswordScreen() {
        new GoToForgotPasswordScreen().runThis(mainController);
    }

    public void checkData() throws SQLException {
        String emailAddress = userEmail.getText();
        String password = userPassword.getText();
        boolean result = LoggingAlgorithm.algorithm(emailAddress, password, rememberMeToggleButton);

        if (!result) wrongDataText.setVisible(true);
        else new GoToHomeScreen().runThis(mainController);
    }
}
