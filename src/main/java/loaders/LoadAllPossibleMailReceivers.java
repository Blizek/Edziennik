package loaders;

import javafx.scene.layout.AnchorPane;
import variables.ListOfReceiversID;

import java.sql.SQLException;

public class LoadAllPossibleMailReceivers {
    public static void load(AnchorPane paneToAdd) throws SQLException {
        paneToAdd.getChildren().clear();
        for (int i = 0; i < ListOfReceiversID.receiversID.size(); i++)
            LoadPossibleReceivers.load(ListOfReceiversID.receiversID.get(i), 14 + i * 98, true, paneToAdd);
        if (14 + (ListOfReceiversID.receiversID.size() - 1) * 98 >=459)
            paneToAdd.setPrefHeight(14 + ListOfReceiversID.receiversID.size() * 98);
    }
}
