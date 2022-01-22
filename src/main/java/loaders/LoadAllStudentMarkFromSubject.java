package loaders;

import DAO.DAOSchoolSubject;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.sql.SQLException;

public class LoadAllStudentMarkFromSubject {
    public static void load(AnchorPane mainAnchor, ScrollPane scroll, AnchorPane scrollAnchor, Text pageInformation, int subjectID) throws SQLException {
        String subjectName = new DAOSchoolSubject().get(subjectID).get(0).getSubject_name();

        pageInformation.setText("Marks: " + subjectName);

        CreateMarkBox.create(mainAnchor, scroll, scrollAnchor, pageInformation, subjectID);
    }
}
