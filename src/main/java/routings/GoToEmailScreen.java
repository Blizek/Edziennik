package routings;

import controller.EmailController;
import controller.HomeController;
import controller.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class GoToEmailScreen {
    public GoToEmailScreen runThis(MainController mainController) {
        FXMLLoader loadEmailScreen = new FXMLLoader();
        loadEmailScreen.setLocation(this.getClass().getResource("/fxml/EmailScreen.fxml"));
        AnchorPane pane = null;
        try {
            pane = loadEmailScreen.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        EmailController emailController = loadEmailScreen.getController();
        emailController.setMainController(mainController);
        mainController.setScreen(pane);

        return null;
    }
}
