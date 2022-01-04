package loaders;

import controller.EmailController;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class CreateTopInfoBarsInMailList {
    public static void create(AnchorPane pane) {
        AnchorPane senderPane = new AnchorPane();

        senderPane.setLayoutX(5);
        senderPane.setLayoutY(0);
        senderPane.setPrefWidth(294);
        senderPane.setPrefHeight(30);
        senderPane.setStyle("-fx-background-color: #DBDBDB; -fx-border-color: black; -fx-border-width: 2;");

        Text senderText = new Text(77, 19, "Sender");
        senderText.setWrappingWidth(145);
        senderText.setFont(Font.font("Calibri", 20));
        senderText.setTextAlignment(TextAlignment.CENTER);
        senderPane.getChildren().add(senderText);

        AnchorPane subjectPane = new AnchorPane();

        subjectPane.setLayoutX(299);
        subjectPane.setLayoutY(0);
        subjectPane.setPrefWidth(294);
        subjectPane.setPrefHeight(30);
        subjectPane.setStyle("-fx-background-color: #DBDBDB; -fx-border-color: black; -fx-border-width: 2;");

        Text subjectText = new Text(72, 19, "Subject");
        subjectText.setWrappingWidth(163);
        subjectText.setFont(Font.font("Calibri", 20));
        subjectText.setTextAlignment(TextAlignment.CENTER);
        subjectPane.getChildren().add(subjectText);

        AnchorPane datePane = new AnchorPane();

        datePane.setLayoutX(593);
        datePane.setLayoutY(0);
        datePane.setPrefWidth(294);
        datePane.setPrefHeight(30);
        datePane.setStyle("-fx-background-color: #DBDBDB; -fx-border-color: black; -fx-border-width: 2;");

        Text dateText = new Text(72, 19, "Date");
        dateText.setWrappingWidth(189);
        dateText.setFont(Font.font("Calibri", 20));
        dateText.setTextAlignment(TextAlignment.CENTER);
        datePane.getChildren().add(dateText);


        pane.getChildren().add(senderPane);
        pane.getChildren().add(subjectPane);
        pane.getChildren().add(datePane);
    }
}
