package controller;

import DAO.DAOEmail;
import features.CalculateDaysBack;
import features.GetUser;
import loaders.CreateMailsPane;
import loaders.SetPaneHeightEmailScreen;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import loaders.CreateFooter;
import model.Email;
import model.User;
import routings.GoToHomeScreen;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmailController {
    @FXML
    Text loggedData;

    @FXML
    Text mailSubjectText, mailDateText, mailSenderText, mailRecipientsText, mailText;

    @FXML
    AnchorPane mainAnchorPane, receivedMailsPane, sentMailsPane, receivedBox, sentBox, receivedPeriods, sentPeriods,
                listOfMailsAnchorPane;

    @FXML
    AnchorPane mailTextAnchorPane;

    @FXML
    ImageView receivedIcon, sentIcon;

    @FXML
    ScrollPane listOfMailsScroll, mailTextScroll;

    User user;

    private MainController mainController;

    List<Text> mailTexts = new ArrayList<>();

    public void initialize() throws SQLException {
        setMailTexts(mailTexts);

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

    public void setMailTexts(List<Text> mailTexts) {
        mailTexts.add(mailSubjectText);
        mailTexts.add(mailDateText);
        mailTexts.add(mailSenderText);
        mailTexts.add(mailRecipientsText);
        mailTexts.add(mailText);
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

    public void setReceivedPaneHeight() throws SQLException {
        SetPaneHeightEmailScreen.setPaneHeight(receivedMailsPane, sentMailsPane, receivedPeriods, receivedIcon, true, listOfMailsAnchorPane, mailTexts, mailTextAnchorPane);
    }

    public void setSentPaneHeight() throws SQLException {
        SetPaneHeightEmailScreen.setPaneHeight(sentMailsPane, receivedMailsPane, sentPeriods, sentIcon, false, listOfMailsAnchorPane, mailTexts, mailTextAnchorPane);
    }
}
