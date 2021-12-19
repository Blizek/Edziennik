package features;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import locations.FilesLocations;

public class CreateManageBox {
    public void create(AnchorPane pane, String text) {
        AnchorPane manageBox = new AnchorPane();

        manageBox.setPrefWidth(479);
        manageBox.setPrefHeight(208);
        manageBox.setLayoutX(661);
        manageBox.setLayoutY(46);
        manageBox.setStyle("-fx-background-color:  #1AD759; -fx-border-color: black; -fx-border-width: 3;");

        Text manageText = new Text(156, 112, text);
        manageText.setWrappingWidth(303);
        manageText.setTextAlignment(TextAlignment.CENTER);
        manageText.setFont(Font.font("Calibri", FontWeight.BOLD, 35));
        manageText.setFill(Color.rgb(255, 255, 255));

        ImageView studentIcon = new ImageView();
        studentIcon.setFitHeight(150);
        studentIcon.setFitWidth(124);
        studentIcon.setLayoutX(23);
        studentIcon.setLayoutY(29);
        studentIcon.setImage(new Image(FilesLocations.studentsIconPath));

        manageBox.getChildren().add(manageText);
        manageBox.getChildren().add(studentIcon);

        pane.getChildren().add(manageBox);
    }
}
