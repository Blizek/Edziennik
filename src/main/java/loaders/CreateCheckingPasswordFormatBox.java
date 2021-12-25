package loaders;

import com.jfoenix.controls.JFXToggleButton;
import features.ReadCheckingPasswordFormatData;
import features.SetCheckingToggleButtonStatus;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import locations.FilesLocations;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CreateCheckingPasswordFormatBox {
    public static void create(AnchorPane pane) throws IOException, ParseException {
        AnchorPane setFormatBox = new AnchorPane();

        setFormatBox.setLayoutX(51);
        setFormatBox.setLayoutY(315);
        setFormatBox.setPrefHeight(208);
        setFormatBox.setPrefWidth(479);
        setFormatBox.setStyle("-fx-background-color: #EB1010; -fx-border-color: black; -fx-border-width: 3;");

        Text passwordFormatText = new Text(173, 46, "Checking password format");
        passwordFormatText.setWrappingWidth(282);
        passwordFormatText.setTextAlignment(TextAlignment.CENTER);
        passwordFormatText.setFont(Font.font("Calibri", FontWeight.BOLD, 35));
        passwordFormatText.setFill(Color.rgb(255, 255, 255));

        ImageView checkIcon = new ImageView();
        checkIcon.setFitHeight(132);
        checkIcon.setFitWidth(127);
        checkIcon.setLayoutX(29);
        checkIcon.setLayoutY(34);
        checkIcon.setImage(new Image(FilesLocations.PASSWORD_FORMAT_ICON_PATH));

        JFXToggleButton status = new JFXToggleButton();
        status.setLayoutX(186);
        status.setLayoutY(121);
        status.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        status.setToggleColor(Color.rgb(0, 0, 0));
        status.setToggleLineColor(Color.rgb(81, 75, 75));
        status.setUnToggleColor(Color.rgb(250, 250, 250));
        status.setUnToggleLineColor(Color.rgb(153, 153, 153));
        if (ReadCheckingPasswordFormatData.readData()) SetCheckingToggleButtonStatus.setOn(status);
        else SetCheckingToggleButtonStatus.setOff(status);

        setFormatBox.getChildren().add(passwordFormatText);
        setFormatBox.getChildren().add(checkIcon);
        setFormatBox.getChildren().add(status);

        pane.getChildren().add(setFormatBox);
    }
}
