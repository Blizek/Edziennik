package controller;

import com.jfoenix.controls.JFXToggleButton;
import features.LoggingAlgorithm;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import locations.FilesLocations;
import routings.GoToForgotPasswordScreen;
import routings.GoToHomeScreen;

import java.sql.SQLException;

public class LoginController {
    // variables from LoginScreen.fxml file
    @FXML
    TextField userEmail, uncoverPassword;

    @FXML
    PasswordField userPassword;

    @FXML
    ImageView passwordViewIcon;

    @FXML
    JFXToggleButton rememberMeToggleButton;

    @FXML
    Text wrongDataText, iconInformationText;

    @FXML
    AnchorPane iconInformation;

    private MainController mainController;

    // private variable to check if app has to show text from password field or not
    private boolean coverPassword = true;

    public void setMainController(MainController mainController)
    {
        this.mainController = mainController;
    }

    // implemented functions from LoginScreen.fxml file
    public void goToForgotPasswordScreen() {
        new GoToForgotPasswordScreen().runThis(mainController);
    }

    /** function from LoginScreen.fxml file which is after clicking submit data. If user's data are right, app shows
     * user's home screen, if not it stays on this screen with information that login or password are incorrect
     * @throws SQLException
     * **/
    public void checkData() throws SQLException {
        String emailAddress = userEmail.getText();
        String password;
        if (userPassword.isVisible()) password = userPassword.getText();
        else password = uncoverPassword.getText();
        // result of trying to log in to app - true if all data is correct, false if one of them is valid
        boolean result = LoggingAlgorithm.algorithm(emailAddress, password, rememberMeToggleButton);

        if (!result) wrongDataText.setVisible(true);
        else new GoToHomeScreen().runThis(mainController);
    }

    /** function to show or hide password after clicking image **/
    public void changePasswordView() {
        String coverPasswordValue = userPassword.getText();
        String uncoverPasswordValue = uncoverPassword.getText();

        // if password field is hide - show password, else hide it again
        if (coverPassword) {
            uncoverPassword.setVisible(true);
            userPassword.setVisible(false);
            uncoverPassword.setText(coverPasswordValue);
            passwordViewIcon.setImage(new Image(FilesLocations.UNCOVER_PASSWORD_ICON));
        } else {
            userPassword.setVisible(true);
            uncoverPassword.setVisible(false);
            userPassword.setText(uncoverPasswordValue);
            passwordViewIcon.setImage(new Image(FilesLocations.COVER_PASSWORD_ICON));
        }

        coverPassword = !coverPassword;
        showInfoAboutIcon();
    }

    /** function to show information after entering show/hide password field icon **/
    public void showInfoAboutIcon() {
        if (coverPassword) iconInformationText.setText("Show password");
        else iconInformationText.setText("Hide password");
        iconInformation.setVisible(true);
        passwordViewIcon.setImage(new Image(FilesLocations.ENTERED_MOUSE_PASSWORD_ICON));
    }

    /** function to hide information after exiting show/hide password field icon **/
    public void hideInfoAboutIcon() {
        iconInformation.setVisible(false);
        if (coverPassword) passwordViewIcon.setImage(new Image(FilesLocations.COVER_PASSWORD_ICON));
        else passwordViewIcon.setImage(new Image(FilesLocations.UNCOVER_PASSWORD_ICON));
    }
}
