package routings;

import controller.ForgotPasswordController;
import controller.LoginController;
import controller.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class GoToForgotPasswordScreen {
    public GoToForgotPasswordScreen runThis(MainController mainController) {
        FXMLLoader loadForgotPasswordScreen = new FXMLLoader();
        loadForgotPasswordScreen.setLocation(this.getClass().getResource("/fxml/ForgotPasswordScreen.fxml"));
        AnchorPane pane = null;
        try {
            pane = loadForgotPasswordScreen.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ForgotPasswordController forgotPasswordController = loadForgotPasswordScreen.getController();
        forgotPasswordController.setMainController(mainController);
        mainController.setScreen(pane);

        return null;
    }
}
