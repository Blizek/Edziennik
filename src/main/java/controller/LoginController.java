package controller;

import com.jfoenix.controls.JFXToggleButton;
import features.LoggingAlgorithm;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import locations.FilesLocations;
import routings.GoToForgotPasswordScreen;
import routings.GoToHomeScreen;

import java.sql.SQLException;

public class LoginController {
    @FXML
    TextField userEmail;

    @FXML
    PasswordField userPassword;

    @FXML
    TextField uncoverPassword;

    @FXML
    ImageView passwordViewIcon;

    @FXML
    JFXToggleButton rememberMeToggleButton;

    @FXML
    Text wrongDataText;

    @FXML
    AnchorPane iconInformation;

    @FXML
    Text iconInformationText;

    private MainController mainController;

    private boolean coverPassword = true;

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

    public void changePasswordView() {
        String coverPasswordValue = userPassword.getText();
        String uncoverPasswordValue = uncoverPassword.getText();

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

    public void showInfoAboutIcon() {
        if (coverPassword) iconInformationText.setText("Show password");
        else iconInformationText.setText("Hide password");
        iconInformation.setVisible(true);
        passwordViewIcon.setImage(new Image(FilesLocations.ENTERED_MOUSE_PASSWORD_ICON));
    }

    public void hideInfoAboutIcon() {
        iconInformation.setVisible(false);
        if (coverPassword) passwordViewIcon.setImage(new Image(FilesLocations.COVER_PASSWORD_ICON));
        else passwordViewIcon.setImage(new Image(FilesLocations.UNCOVER_PASSWORD_ICON));
    }
}
