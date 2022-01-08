package loaders;

import controller.ManageReceiversController;
import features.GetUserNameAndRole;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import locations.FilesLocations;
import routings.ManageReceiversMain;
import variables.ListOfReceiversID;

import java.sql.SQLException;
import java.util.ArrayList;

import static variables.ListOfReceiversID.addID;
import static variables.ListOfReceiversID.removeID;

public class LoadPossibleReceivers {
    public static ArrayList<AnchorPane> panes = new ArrayList<>();

    public static void load(int userID, int YPosition, boolean receiversBoxes, AnchorPane paneToAdd) throws SQLException {
        if (!ListOfReceiversID.receiversID.contains(userID) || receiversBoxes) {
            String nameAndRole = GetUserNameAndRole.get(userID);

            AnchorPane userBox = new AnchorPane();
            userBox.setLayoutX(14);
            userBox.setLayoutY(YPosition);
            userBox.setPrefWidth(422);
            userBox.setPrefHeight(89);
            userBox.setId(Integer.toString(userID));
            userBox.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-background-color: white;");

            Text userNameAndRole = new Text(22, 50, nameAndRole);
            userNameAndRole.setTextAlignment(TextAlignment.LEFT);
            userNameAndRole.setWrappingWidth(314);
            userNameAndRole.setFont(Font.font("Calibri", 20));
            userBox.getChildren().add(userNameAndRole);

            ImageView plusMinusIcon = new ImageView();
            plusMinusIcon.setFitWidth(33);
            plusMinusIcon.setFitHeight(33);
            plusMinusIcon.setLayoutX(362);
            plusMinusIcon.setLayoutY(28);

            EventHandler<MouseEvent> addUser = e -> {
                userBox.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-background-color: #DBDBDB;");
                addID(Integer.parseInt(userBox.getId()));
            };

            EventHandler<MouseEvent> removeUser = e -> {
                removeID(Integer.parseInt(userBox.getId()));
                try {
                    LoadAllPossibleMailReceivers.load(paneToAdd);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            };

            if (receiversBoxes) {
                plusMinusIcon.setImage(new Image(FilesLocations.MINUS_RECEIVER_ICON));
                plusMinusIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, removeUser);
            } else {
                plusMinusIcon.setImage(new Image(FilesLocations.PLUS_RECEIVER_ICON));
                plusMinusIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, addUser);
            }

            userBox.getChildren().add(plusMinusIcon);


            panes.add(userBox);
            paneToAdd.getChildren().add(userBox);
        }
    }
}
