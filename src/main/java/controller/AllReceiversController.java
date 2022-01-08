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
    @FXML
    ScrollPane boxesScroll;

    @FXML
    AnchorPane boxesAnchorPane;

    public void initialize() throws SQLException {
        boxesScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        boxesScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        loadComponents();
    }

    private void loadComponents() throws SQLException {
        ArrayList<Integer> IDs = ListOfReceiversID.receiversID;
        for (int i = 0; i < IDs.size(); i++) {
            String nameAndRole = GetUserNameAndRole.get(IDs.get(i));

            AnchorPane box = new AnchorPane();

            box.setLayoutX(14);
            box.setLayoutY(14 + i * 109);
            box.setPrefWidth(480);
            box.setPrefHeight(94);
            box.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 2;");

            Text userNameAndRoleText = new Text(35, 52, nameAndRole);
            userNameAndRoleText.setFont(Font.font("Calibri", 20));
            userNameAndRoleText.setWrappingWidth(409);
            userNameAndRoleText.setTextAlignment(TextAlignment.CENTER);
            box.getChildren().add(userNameAndRoleText);

            boxesAnchorPane.getChildren().add(box);
        }
        if (14 + IDs.size() * 109 > 791) {
            boxesAnchorPane.setPrefHeight(14 + (IDs.size() + 1) * 109);
        }
    }
}
