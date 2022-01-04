package loaders;

import javafx.scene.layout.AnchorPane;

public class DeletePeriodsEmailScreen {
    public static void deletePeriods(AnchorPane paneToDeletePeriods) {
        paneToDeletePeriods.getChildren().clear();
        paneToDeletePeriods.setPrefHeight(0);
    }
}
