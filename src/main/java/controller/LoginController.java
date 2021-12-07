package controller;

import DAO.DAOUser;
import com.jfoenix.controls.JFXToggleButton;
import features.SetRememberMeData;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.User;
import routings.GoToForgotPasswordScreen;
import routings.GoToHomeScreen;
import variables.UserIDHolder;

import java.sql.SQLException;
import java.util.List;

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
        DAOUser userDAO = new DAOUser();
        List<User> possibleUsers = userDAO.getByEmail(emailAddress);
        if (possibleUsers.isEmpty()) wrongDataText.setVisible(true);
        else {
            boolean userFound = false;
            User lookingUser = null;
            for (User user: possibleUsers) {
                if (user.getUser_email().equals(emailAddress) && user.getUser_password().equals(password)) {
                    lookingUser = user;
                    userFound = true;
                    break;
                }
            }
            if (!userFound) wrongDataText.setVisible(true);
            else {
                if (rememberMeToggleButton.isSelected()) {
                    new SetRememberMeData().setData(userEmail.getText(), userPassword.getText());
                }
                wrongDataText.setVisible(false);
                System.out.println("Logged as: ID: " + lookingUser.getUser_id() + ", e-mail: " + lookingUser.getUser_email()
                + ", password: " + lookingUser.getUser_password() + ", role: " + lookingUser.getUser_role());
                UserIDHolder.setUserID(lookingUser.getUser_id());
                new GoToHomeScreen().runThis(mainController);
            }
        }
    }
}
