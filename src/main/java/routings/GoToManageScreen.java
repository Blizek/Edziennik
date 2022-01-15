package routings;

import controller.LoginController;
import controller.MainController;
import controller.ManageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class GoToManageScreen {
    public GoToManageScreen runThis(MainController mainController) {
        FXMLLoader loadManageScreen = new FXMLLoader();
        loadManageScreen.setLocation(this.getClass().getResource("/fxml/ManageScreen.fxml"));
        AnchorPane pane = null;
        try {
            pane = loadManageScreen.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ManageController manageController = loadManageScreen.getController();
        manageController.setMainController(mainController);
        mainController.setScreen(pane);

        return null;
    }
}
