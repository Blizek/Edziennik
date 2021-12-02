package controller;

import com.jfoenix.controls.JFXToggleButton;
import features.SetRememberMeData;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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

    }

    public void checkData() {
        if (rememberMeToggleButton.isSelected()) {
            new SetRememberMeData().setData(userEmail.getText(), userPassword.getText());
        }
    }
}
