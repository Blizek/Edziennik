package routings;

import controller.LoginController;
import controller.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class GoToLoginScreen {
    public GoToLoginScreen runThis(MainController mainController) {
        FXMLLoader loadLoginScreen = new FXMLLoader();
        loadLoginScreen.setLocation(this.getClass().getResource("/fxml/LoginScreen.fxml"));
        AnchorPane pane = null;
        try {
            pane = loadLoginScreen.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LoginController loginController = loadLoginScreen.getController();
        loginController.setMainController(mainController);
        mainController.setScreen(pane);

        return null;
    }
}
