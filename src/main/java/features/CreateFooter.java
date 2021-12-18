package features;

import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class CreateFooter {
    /** function to create footer in every screen in app **/
    public void create(AnchorPane pane) {
        // footer height and width and getting whole anchor pane height (to calculate where insert footer)
        final double FOOTER_HEIGHT = 70;
        final double FOOTER_WIDTH = 1200;
        double height = pane.getPrefHeight();

        // setting for footer
        AnchorPane footer = new AnchorPane();
        footer.setLayoutX(0);
        footer.setLayoutY(height - FOOTER_HEIGHT);
        footer.setMinHeight(FOOTER_HEIGHT);
        footer.setMinWidth(FOOTER_WIDTH);
        footer.setStyle("-fx-background-color: white;");

        // copyright information text
        Text infoText = new Text(282, 41, "App created by Błażej Naziemiec (Blizek). Copyright 2021©. All Rights Reserved.");
        infoText.setFont(Font.font("Calibri", FontWeight.NORMAL, 20));

        // adding copyright information to footer
        footer.getChildren().add(infoText);

        // adding footer to pane
        pane.getChildren().add(footer);
    }
}
