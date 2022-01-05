package features;

import controller.EmailController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import loaders.CreateMailsPane;
import model.Email;
import variables.ListOfEmails;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SetEmailPeriods {
    public static void setEmailPeriodList(AnchorPane box, AnchorPane listOfMailsPane, AnchorPane paneForMail) {
        EventHandler<MouseEvent> getMails = e -> {
            String startDay, finishDay;
            String id = box.getId();
            if (id.startsWith("Today")) {
                startDay = LocalDate.now().toString();
                finishDay = LocalDate.now().plusDays(1).toString();
            } else if (id.startsWith("This week")) {
                startDay = LocalDate.now().minusDays(CalculateDaysBack.calculate(LocalDate.now())).toString();
                finishDay = LocalDate.now().plusDays(1).toString();
            } else if (id.startsWith("Last week")) {
                startDay = LocalDate.now().minusDays(CalculateDaysBack.calculate(LocalDate.now()) + 7).toString();
                finishDay = LocalDate.now().minusDays(CalculateDaysBack.calculate(LocalDate.now())).toString();
            } else if (id.startsWith("Two weeks ago")) {
                startDay = LocalDate.now().minusDays(CalculateDaysBack.calculate(LocalDate.now()) + 14).toString();
                finishDay = LocalDate.now().minusDays(CalculateDaysBack.calculate(LocalDate.now()) + 7).toString();
            } else {
                startDay = "1990-01-01";
                finishDay = LocalDate.now().minusDays(CalculateDaysBack.calculate(LocalDate.now()) + 14).toString();
            }

            try {
                ListOfEmails.emails = GetEmailsFromPeriod.get(startDay, finishDay);
                List<Email> emailsForUser = GetAllMailForUserInPeriod.get(ListOfEmails.emails, GetUser.get().getUser_id(), id.endsWith("true"));
                CreateMailsPane.create(emailsForUser, listOfMailsPane, paneForMail);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        };
        box.addEventHandler(MouseEvent.MOUSE_CLICKED, getMails);
    }
}
