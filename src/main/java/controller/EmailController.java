package controller;

import features.GetUser;
import loaders.SetPaneHeightEmailScreen;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import loaders.CreateFooter;
import model.User;
import routings.GoToHomeScreen;
import routings.MailWritingMain;

import java.io.IOException;
import java.sql.SQLException;

public class EmailController {
    @FXML
    Text loggedData;

    @FXML
    AnchorPane mainAnchorPane, receivedMailsPane, sentMailsPane, receivedBox, sentBox, receivedPeriods, sentPeriods,
                listOfMailsAnchorPane, mailTextAnchorPane;

    @FXML
    ImageView receivedIcon, sentIcon;

    @FXML
    ScrollPane listOfMailsScroll, mailTextScroll;

    User user;

    private MainController mainController;

    public void initialize() throws SQLException {
        user = GetUser.get();
        loggedData.setText("Logged in as: " + user.getUser_email() + " (" + user.getUser_role() + ")");
        CreateFooter.create(mainAnchorPane);

        focusOnBox(receivedBox);
        notFocusOnBox(receivedBox);
        focusOnBox(sentBox);
        notFocusOnBox(sentBox);

        listOfMailsScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        listOfMailsScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        mailTextScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mailTextScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
    }


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void goToHomeScreen() {
        new GoToHomeScreen().runThis(mainController);
    }

    public static void focusOnBox(AnchorPane box) {
        EventHandler<MouseEvent> focus = e -> box.setStyle("-fx-background-color: #FCEB7E");
        box.addEventHandler(MouseEvent.MOUSE_ENTERED, focus);
    }

    public static void notFocusOnBox(AnchorPane box) {
        EventHandler<MouseEvent> notFocus = e -> box.setStyle("-fx-background-color: #FFFFFF");
        box.addEventHandler(MouseEvent.MOUSE_EXITED, notFocus);
    }

    public void setReceivedPaneHeight() {
        SetPaneHeightEmailScreen.setPaneHeight(receivedMailsPane, sentMailsPane, receivedPeriods, receivedIcon, true, listOfMailsAnchorPane, mailTextAnchorPane);
    }

    public void setSentPaneHeight() {
        SetPaneHeightEmailScreen.setPaneHeight(sentMailsPane, receivedMailsPane, sentPeriods, sentIcon, false, listOfMailsAnchorPane, mailTextAnchorPane);
    }

    public void setMailWritingScreen() throws IOException {
        new MailWritingMain().runThis();
    }
}
