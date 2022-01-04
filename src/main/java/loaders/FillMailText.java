package loaders;

import DAO.DAOEmail;
import controller.EmailController;
import features.GetUserNameAndRole;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.Email;

import java.sql.SQLException;
import java.util.List;

public class FillMailText {
    public static void fill(Email email, List<Text> emailTexts, AnchorPane textPane) throws SQLException {
        List<Email> listOfSameEmails = new DAOEmail().getAllSameEmails(email.getSender_user_id(), email.getEmail_send_date().toString());
        emailTexts.get(0).setText("Subject: " + email.getEmail_subject());
        emailTexts.get(1).setText("Date: " + email.getEmail_send_date().toString().substring(0, 19));
        emailTexts.get(2).setText("From: " + GetUserNameAndRole.get(email.getSender_user_id()));
        if (listOfSameEmails.size() == 1) emailTexts.get(3).setText("To: " + GetUserNameAndRole.get(email.getRecipient_user_id()));
        else emailTexts.get(3).setText("To: Many recipients");
        emailTexts.get(4).setText(email.getEmail_text());
        if (emailTexts.get(4).getLayoutBounds().getHeight() > 234) textPane.setPrefHeight(emailTexts.get(4).getLayoutBounds().getHeight() + 150);
    }
}
