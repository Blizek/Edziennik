package loaders;

import DAO.DAOEmail;
import features.GetUserNameAndRole;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.Email;

import java.sql.SQLException;
import java.util.List;

public class FillMailText {
    static Text subject = new Text(), date = new Text(), sender = new Text(), receivers = new Text(), text = new Text();

    static AnchorPane topPane = new AnchorPane();

    public static void fill(Email email, AnchorPane textPane) throws SQLException {
        createInitializeView(textPane);
        List<Email> listOfSameEmails = new DAOEmail().getAllSameEmails(email.getSender_user_id(), email.getEmail_send_date().toString());
        subject.setText("Subject: " + email.getEmail_subject());
        date.setText("Date: " + email.getEmail_send_date().toString().substring(0, 19));
        sender.setText("From: " + GetUserNameAndRole.get(email.getSender_user_id()));
        if (listOfSameEmails.size() == 1) receivers.setText("To: " + GetUserNameAndRole.get(email.getRecipient_user_id()));
        else receivers.setText("To: Many recipients");
        text.setText(email.getEmail_text());
        if (text.getLayoutBounds().getHeight() > 234) textPane.setPrefHeight(text.getLayoutBounds().getHeight() + 150);
    }

    private static void createInitializeView(AnchorPane mailPane) {
        mailPane.getChildren().clear();
        topPane.getChildren().clear();

        subject.setLayoutX(14);
        subject.setLayoutY(19);
        subject.setTextAlignment(TextAlignment.LEFT);
        subject.setFont(Font.font("Calibri", 18));
        subject.setWrappingWidth(500);

        date.setLayoutX(14);
        date.setLayoutY(42);
        date.setTextAlignment(TextAlignment.LEFT);
        date.setFont(Font.font("Calibri", 18));
        date.setWrappingWidth(500);

        sender.setLayoutX(14);
        sender.setLayoutY(65);
        sender.setTextAlignment(TextAlignment.LEFT);
        sender.setFont(Font.font("Calibri", 18));
        sender.setWrappingWidth(500);

        receivers.setLayoutX(14);
        receivers.setLayoutY(88);
        receivers.setTextAlignment(TextAlignment.LEFT);
        receivers.setFont(Font.font("Calibri", 18));
        receivers.setWrappingWidth(500);

        topPane.setLayoutX(0);
        topPane.setLayoutY(0);
        topPane.setMinWidth(912);
        topPane.setMinHeight(100);
        topPane.setStyle("-fx-background-color: #DBDBDB");

        topPane.getChildren().addAll(subject, date, sender, receivers);

        text.setLayoutX(3);
        text.setLayoutY(123);
        text.setTextAlignment(TextAlignment.LEFT);
        text.setFont(Font.font("Calibri", 22));
        text.setWrappingWidth(890);

        mailPane.getChildren().addAll(topPane, text);
    }
}
