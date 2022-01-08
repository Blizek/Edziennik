package variables;

import javafx.scene.control.Alert;

import java.util.ArrayList;

public class ListOfReceiversID {
    public static ArrayList<Integer> receiversID = new ArrayList<>();

    public static void addID(int ID) {
        if (!receiversID.contains(ID)) {
            receiversID.add(ID);
        }
    }

    public static void removeID(Integer ID) {
        receiversID.remove(ID);
    }
}
