package loaders;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;

public class LoadAllStudentMarkFromSubject {
    public static void load(AnchorPane mainAnchor, ScrollPane scroll, AnchorPane scrollAnchor, int subjectID) throws SQLException {
        CreateMarkBox.create(mainAnchor, scroll, scrollAnchor, subjectID);
    }
}
