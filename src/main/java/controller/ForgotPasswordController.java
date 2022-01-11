package controller;

import routings.GoToLoginScreen;

public class ForgotPasswordController {
    private MainController mainController;

    public void setMainController(MainController mainController)
    {
        this.mainController = mainController;
    }

    // implemented functions from ForgotPasswordScreen.fxml file
    public void goToLoginScreen() {
        new GoToLoginScreen().runThis(mainController);
    }
}
