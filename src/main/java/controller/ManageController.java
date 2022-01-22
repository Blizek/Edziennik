package controller;

import DAO.DAOUser;
import features.GetUser;
import features.GetUserNameAndSurnameByUserID;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import loaders.CreateFooter;
import loaders.LoadAllLeftButtons;
import model.User;
import routings.GoToHomeScreen;

import java.sql.SQLException;

public class ManageController {
    // variables from ManageScreen.fxml
    @FXML
    AnchorPane mainAnchorPane, leftBarPane, mainValueAnchorPane, scrollAnchor;

    @FXML
    ScrollPane scroll;

    @FXML
    Text userNameText;

    User user;

    private MainController mainController;

    // screen's initialize function
    public void initialize() throws SQLException {
        user = GetUser.get();
        userNameText.setText(getNameAndSurnameByRole(user.getUser_id()));
        LoadAllLeftButtons.buttonToDisableID = "main";
        LoadAllLeftButtons.YPosition = 0;
        LoadAllLeftButtons.load(leftBarPane, user.getUser_role(), mainValueAnchorPane, scroll, scrollAnchor);
        CreateFooter.create(mainAnchorPane);
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    /** function from ManageScreen.fxml file to go to app's home screen **/
    public void goToHomeScreen() {
        new GoToHomeScreen().runThis(mainController);
    }

    /** function to get user name and surname by role
     * @param userID to find user in database
     * @throws SQLException
     */
    private String getNameAndSurnameByRole(int userID) throws SQLException {
        String userRole = new DAOUser().get(userID).get(0).getUser_role();
        String userNameAndSurname = "";
        switch (userRole) {
            case "ADMIN" -> userNameAndSurname = GetUserNameAndSurnameByUserID.getAdminName(userID);
            case "STUDENT" -> userNameAndSurname = GetUserNameAndSurnameByUserID.getStudentName(userID);
            case "TEACHER" -> userNameAndSurname = GetUserNameAndSurnameByUserID.getTeacherName(userID);
            case "GUARDIAN" -> userNameAndSurname = GetUserNameAndSurnameByUserID.getGuardianName(userID);
            case "PRINCIPAL" -> userNameAndSurname = GetUserNameAndSurnameByUserID.getPrincipalName(userID);
        }
        return userNameAndSurname;
    }
}
