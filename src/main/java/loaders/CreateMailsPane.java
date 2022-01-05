package loaders;

import DAO.DAOEmail;
import DAO.DAOUser;
import controller.EmailController;
import features.GetUserNameAndRole;
import features.GetUserNameAndSurname;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.Email;
import model.User;

import java.sql.SQLException;
import java.util.List;

public class CreateMailsPane {

    public static void create(List<Email> emails, AnchorPane paneToAdd, AnchorPane mailTextPane) throws SQLException {
        paneToAdd.getChildren().clear();
        CreateTopInfoBarsInMailList.create(paneToAdd);
        for (int i = 0; i < emails.size(); i++) {
            if (i == 0 || (!emails.get(i).getEmail_send_date().toString().equals(emails.get(i - 1).getEmail_send_date().toString())
            && emails.get(i).getSender_user_id() == emails.get(i - 1).getSender_user_id())) {
                AnchorPane mailPane = new AnchorPane();

                mailPane.setLayoutX(5);
                mailPane.setLayoutY(32 + i * 72);
                mailPane.setPrefWidth(885);
                mailPane.setPrefHeight(71);
                mailPane.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 3;");

                Email email = emails.get(i);

                mailPane.setId(Integer.toString(email.getEmail_id()));

                FontWeight fontWeight;
                if (email.isEmail_received()) fontWeight = FontWeight.NORMAL;
                else fontWeight = FontWeight.BOLD;

                TextAlignment textAlignment = TextAlignment.LEFT;
                Font font = Font.font("Calibri", fontWeight, 15);

                Text senderText = new Text(14, 39, GetUserNameAndRole.get(email.getSender_user_id()));
                senderText.setTextAlignment(textAlignment);
                senderText.setFont(font);
                senderText.setWrappingWidth(272);

                Text subjectText = new Text(296, 39, email.getEmail_subject());
                subjectText.setTextAlignment(textAlignment);
                subjectText.setFont(font);
                subjectText.setWrappingWidth(282);

                Text dateText = new Text(591, 39, email.getEmail_send_date().toString().substring(0, 19));
                dateText.setTextAlignment(textAlignment);
                dateText.setFont(font);
                dateText.setWrappingWidth(252);

                EventHandler<MouseEvent> focusOnEmail = e -> mailPane.setStyle("-fx-background-color: #DBDBDB; -fx-border-color: black; -fx-border-width: 3;");
                mailPane.addEventHandler(MouseEvent.MOUSE_ENTERED, focusOnEmail);

                EventHandler<MouseEvent> notFocusOnEmail = e -> mailPane.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 3;");
                mailPane.addEventHandler(MouseEvent.MOUSE_EXITED, notFocusOnEmail);

                EventHandler<MouseEvent> getThisEmail = e -> {
                    int emailId = Integer.parseInt(mailPane.getId());
                    try {
                        Email emailToOpen = new DAOEmail().get(emailId).get(0);
                        emailToOpen.setEmail_received(true);
                        FillMailText.fill(emailToOpen, mailTextPane);
                        new DAOEmail().update(emailToOpen);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                };
                mailPane.addEventHandler(MouseEvent.MOUSE_CLICKED, getThisEmail);

                mailPane.getChildren().add(senderText);
                mailPane.getChildren().add(subjectText);
                mailPane.getChildren().add(dateText);

                paneToAdd.getChildren().add(mailPane);
            }
        }
    }
}
