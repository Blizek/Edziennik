package controller;

import DAO.DAOUser;
import features.ReadRememberMeData;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import model.User;
import org.json.simple.parser.ParseException;
import routings.GoToHomeScreen;
import routings.GoToLoginScreen;
import variables.UserIDHolder;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MainController {
    // variables from MainScreen.fxml file
    @FXML
    AnchorPane pane;

    /** initialize screen function
     * @throws IOException
     * @throws ParseException
     * @throws SQLException
     */
    public void initialize() throws IOException, ParseException, SQLException {
        List<String> data = ReadRememberMeData.readData();
        String email = data.get(0);

        if (email.length() == 0) loadLoginScreen();
        else loadHomeScreen(email);
    }

    /** function to set new screen in app
     * @param pane
     */
    public void setScreen(AnchorPane pane) {
        this.pane.getChildren().clear();
        this.pane.getChildren().add(pane);
    }

    /** function to go to login screen **/
    public void loadLoginScreen() {
        new GoToLoginScreen().runThis(this);
    }

    /** function to go automatically to home screen, if last user chose "Remember me"
     * @param email
     * @throws SQLException
     */
    private void loadHomeScreen(String email) throws SQLException {
        DAOUser userDao = new DAOUser();
        List<User> user = userDao.getByEmail(email);
        UserIDHolder.setUserID(user.get(0).getUser_id());

        new GoToHomeScreen().runThis(this);
    }
}
