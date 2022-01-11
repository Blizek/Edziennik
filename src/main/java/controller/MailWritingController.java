package controller;

import DAO.DAOEmail;
import enumTypes.DatabaseTablesName;
import features.GetMaxID;
import features.GetUser;
import features.GetUserNameAndRole;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.Email;
import routings.AllReceiversMain;
import routings.MailWritingMain;
import routings.ManageReceiversMain;
import variables.ListOfReceiversID;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class MailWritingController {
    // variables from MailWritingScreen.fxml file
    @FXML
    TextArea mailText;

    @FXML
    TextField mailSubject;

    @FXML
    AnchorPane mailPane;

    @FXML
    Text receiverErrorText, mailTextError;

    static Text mailReceivers = new Text();

    public void initialize() throws SQLException {
        // settings for mail receivers text and adding it to pane
        mailReceivers.setLayoutX(14);
        mailReceivers.setLayoutY(39);
        mailReceivers.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        mailReceivers.setWrappingWidth(350);
        mailReceivers.setTextAlignment(TextAlignment.LEFT);
        setReceivers(mailReceivers);

        mailPane.getChildren().add(mailReceivers);
    }

    // implemented function from MailWritingScreen.fxml
    public void goToManageReceiversPane() throws IOException {
        new ManageReceiversMain().runThis();
    }

    /** function from MailWritingScreen.fxml file thanks to which user can send mail to other user. If mail text has less
     * than 1 char, more than 1000 chars or doesn't have receivers, then it shows information about it and blocks sending
     * @throws SQLException
     */
    public void sendMail() throws SQLException {
        ArrayList<Integer> IDList = ListOfReceiversID.receiversID;
        int senderID = GetUser.get().getUser_id();
        String mailSubjectValue = mailSubject.getText();
        String mailTextValue = mailText.getText();
        Timestamp sendDate = new Timestamp(new Date().getTime());
        if (mailTextValue.length() > 1000) {
            mailTextError.setVisible(true);
            mailTextError.setText("Your mail hast to have less than 1000 chars!");
        }
        else if (mailTextValue.length() < 1) {
            mailTextError.setVisible(true);
            mailTextError.setText("Your mail has to have more than 0 chars!");
        }
        else if (IDList.size() == 0) {
            receiverErrorText.setVisible(true);
            mailTextError.setVisible(false);
        }
        else {
            mailTextError.setVisible(false);
            receiverErrorText.setVisible(false);
            for (Integer receiverID : IDList) {
                int emailID = GetMaxID.get(DatabaseTablesName.EMAIL) + 1;
                Email email = new Email(emailID, senderID, receiverID, mailSubjectValue, mailTextValue, sendDate, false);
                new DAOEmail().save(email);
            }
            new MailWritingMain().closeStageBySendingMail();
        }
    }

    public void GoToShowAllReceiversScreen() throws IOException {
        new AllReceiversMain().runThis();
    }

    public static void setReceivers(Text mailReceivers) throws SQLException {
        if (ListOfReceiversID.receiversID.size() == 1) mailReceivers.setText("To: " + GetUserNameAndRole.get(ListOfReceiversID.receiversID.get(0)));
        else if (ListOfReceiversID.receiversID.size() > 1) mailReceivers.setText("To: Many receivers");
        else mailReceivers.setText("To: ");
    }
}
