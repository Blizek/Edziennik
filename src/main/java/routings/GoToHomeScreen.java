package routings;

import controller.HomeController;
import controller.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class GoToHomeScreen {
    public GoToHomeScreen runThis(MainController mainController) {
        FXMLLoader loadHomeScreen = new FXMLLoader();
        loadHomeScreen.setLocation(this.getClass().getResource("/fxml/HomeScreen.fxml"));
        AnchorPane pane = null;
        try {
            pane = loadHomeScreen.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HomeController homeController = loadHomeScreen.getController();
        homeController.setMainController(mainController);
        mainController.setScreen(pane);

        return null;
    }
}
