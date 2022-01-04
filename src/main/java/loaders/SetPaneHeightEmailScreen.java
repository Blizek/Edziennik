package loaders;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import locations.FilesLocations;


import java.util.List;

public class SetPaneHeightEmailScreen {
    public static void setPaneHeight(AnchorPane settingPane, AnchorPane secondPane, AnchorPane paneForPeriods, ImageView settingPaneIcon, boolean isReceivedPane, AnchorPane listOfMailsPane, List<Text> texts, AnchorPane paneForMail) {
        if (settingPane.getPrefHeight() == 198) {
            settingPane.setPrefHeight(71);
            settingPaneIcon.setImage(new Image(FilesLocations.CLOSE_FOLDER_ICON));
            settingPane.setStyle("-fx-border-color: #3C56BC; -fx-border-width: 2; -fx-border-radius: 5;");
            DeletePeriodsEmailScreen.deletePeriods(paneForPeriods);
            if (isReceivedPane) secondPane.setLayoutY(312);
        } else {
            settingPane.setPrefHeight(198);
            settingPaneIcon.setImage(new Image(FilesLocations.OPEN_FOLDER_ICON));
            settingPane.setStyle("-fx-border-color: #FFE600; -fx-border-width: 2; -fx-border-radius: 5;");
            AddPeriodsEmailScreen.addPeriods(paneForPeriods, String.valueOf(isReceivedPane), listOfMailsPane, texts, paneForMail);
            if (isReceivedPane) secondPane.setLayoutY(436);
        }
        secondPane.setStyle("-fx-border-color: #3C56BC; -fx-border-width: 2; -fx-border-radius: 5;");
    }
}
