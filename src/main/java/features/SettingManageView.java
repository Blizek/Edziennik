package features;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import loaders.AbsencesManageScreenView;
import loaders.LessonManageScreenView;
import loaders.MarksManageScreenView;

import java.sql.SQLException;
import java.time.LocalDate;

public class SettingManageView {
    public static void set(String buttonID, AnchorPane mainAnchor, ScrollPane scroll, AnchorPane scrollAnchor) throws SQLException {
        switch (buttonID) {
            case "marks" -> MarksManageScreenView.view(mainAnchor, scroll, scrollAnchor);
            case "absences" -> AbsencesManageScreenView.view(mainAnchor, scroll, scrollAnchor);
            case "notes" -> System.out.println("Uwagi");
            case "lesson_plan" -> LessonManageScreenView.view(mainAnchor, scroll, scrollAnchor, LocalDate.now());
            case "exams" -> System.out.println("Sprawdziany");
            case "students" -> System.out.println("Wszyscy uczniowie");
            case "teachers" -> System.out.println("Wszyscy nauczyciele");
            case "your_class" -> System.out.println("Twoja klasa");
        }
    }
}
