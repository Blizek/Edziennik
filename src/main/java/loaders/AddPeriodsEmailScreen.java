package loaders;

import controller.EmailController;
import features.SetEmailPeriods;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import variables.PossibleMailsDates;

import java.sql.SQLException;
import java.util.List;

public class AddPeriodsEmailScreen {
    public static void addPeriods(AnchorPane paneForPeriods, String isReceived, AnchorPane listOfMailsPane, AnchorPane paneForMail) {
        PossibleMailsDates.setPossibleMailsDate(PossibleMailsDates.possibleMailsDate);

        for (int i = 0; i < 5; i++) {
            AnchorPane period = new AnchorPane();

            period.setLayoutX(15);
            period.setLayoutY(20 + i * 30);
            period.setId(PossibleMailsDates.possibleMailsDate.get(i) + isReceived);

            Text periodText = new Text(22, 15, PossibleMailsDates.possibleMailsDate.get(i));
            periodText.setWrappingWidth(150);
            periodText.setFont(Font.font("Calibri", 18));
            periodText.setFill(Color.rgb(60, 86, 188));

            EmailController.focusOnBox(period);
            EmailController.notFocusOnBox(period);
            SetEmailPeriods.setEmailPeriodList(period, listOfMailsPane, paneForMail);

            period.getChildren().add(periodText);
            paneForPeriods.getChildren().add(period);
            paneForPeriods.setPrefHeight(171);
        }
    }
}
