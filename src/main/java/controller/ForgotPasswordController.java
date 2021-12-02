package controller;

import routings.GoToLoginScreen;

public class ForgotPasswordController {
    private MainController mainController;

    public void setMainController(MainController mainController)
    {
        this.mainController = mainController;
    }

    public void goToLoginScreen() {
        new GoToLoginScreen().runThis(mainController);
    }
}
