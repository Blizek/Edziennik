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
    @FXML
    AnchorPane pane;

    public void initialize() throws IOException, ParseException, SQLException {
        List<String> data = ReadRememberMeData.readData();
        String email = data.get(0);

        if (email.length() == 0) loadLoginScreen();
        else loadHomeScreen(email);
    }

    public void setScreen(AnchorPane pane) {
        this.pane.getChildren().clear();
        this.pane.getChildren().add(pane);
    }

    public void loadLoginScreen() {
        new GoToLoginScreen().runThis(this);
    }

    private void loadHomeScreen(String email) throws SQLException {
        DAOUser userDao = new DAOUser();
        List<User> user = userDao.getByEmail(email);
        UserIDHolder.setUserID(user.get(0).getUser_id());

        new GoToHomeScreen().runThis(this);
    }
}
