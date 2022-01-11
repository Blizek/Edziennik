package controller;

import features.GetUserNameAndRole;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import variables.ListOfReceiversID;

import java.sql.SQLException;
import java.util.ArrayList;

public class AllReceiversController {
    // app's scroll pane where are boxes with all receivers
    @FXML
    ScrollPane boxesScroll;

    // anchor pane where app inserts boxes
    @FXML
    AnchorPane boxesAnchorPane;

    public void initialize() throws SQLException {
        // settings for scroll (always on the left,never on the bottom)
        boxesScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        boxesScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        loadComponents();
    }

    /** function to load and create boxes where is information about all mail receivers
     * @throws SQLException
     * **/
    private void loadComponents() throws SQLException {
        // list of all receivers
        ArrayList<Integer> IDs = ListOfReceiversID.receiversID;
        for (int i = 0; i < IDs.size(); i++) {
            String nameAndRole = GetUserNameAndRole.get(IDs.get(i));

            // main information box where app adds text
            AnchorPane box = new AnchorPane();

            // box settings
            box.setLayoutX(14);
            box.setLayoutY(14 + i * 109);
            box.setPrefWidth(480);
            box.setPrefHeight(94);
            box.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 2;");

            // text which includes information about name and role of user and settings for this text
            Text userNameAndRoleText = new Text(35, 52, nameAndRole);
            userNameAndRoleText.setFont(Font.font("Calibri", 20));
            userNameAndRoleText.setWrappingWidth(409);
            userNameAndRoleText.setTextAlignment(TextAlignment.CENTER);
            box.getChildren().add(userNameAndRoleText);

            // adding to main scroll anchor pane this information box
            boxesAnchorPane.getChildren().add(box);
        }
        // settings scroll anchor pane height depending on the count of boxes
        if (14 + IDs.size() * 109 > 791) {
            boxesAnchorPane.setPrefHeight(14 + (IDs.size() + 1) * 109);
        }
    }
}
