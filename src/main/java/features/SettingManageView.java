package features;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import loaders.MarksManageScreenView;

import java.sql.SQLException;

public class SettingManageView {
    public static void set(String buttonID, AnchorPane mainAnchor, ScrollPane scroll, AnchorPane scrollAnchor, Text pageInformation) throws SQLException {
        switch (buttonID) {
            case "marks" -> MarksManageScreenView.view(mainAnchor, scroll, scrollAnchor, pageInformation);
            case "absences" -> System.out.println("Frekwencja");
            case "notes" -> System.out.println("Uwagi");
            case "lesson_plan" -> System.out.println("Plan lekcji");
            case "exams" -> System.out.println("Sprawdziany");
            case "students" -> System.out.println("Wszyscy uczniowie");
            case "teachers" -> System.out.println("Wszyscy nauczyciele");
            case "your_class" -> System.out.println("Twoja klasa");
        }
    }
}
