package features;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import loaders.*;

import java.sql.SQLException;
import java.time.LocalDate;

public class SettingManageView {
    public static void set(String buttonID, AnchorPane mainAnchor, ScrollPane scroll, AnchorPane scrollAnchor) throws SQLException {
        switch (buttonID) {
            case "marks" -> MarksManageScreenView.view(mainAnchor, scroll, scrollAnchor);
            case "absences" -> AbsencesManageScreenView.view(mainAnchor, scroll, scrollAnchor, LocalDate.now());
            case "notes" -> NotesManageScreenView.view(mainAnchor, scroll, scrollAnchor);
            case "lesson_plan" -> LessonManageScreenView.view(mainAnchor, scroll, scrollAnchor, LocalDate.now());
            case "exams" -> ExamsManageScreenView.view(mainAnchor, scroll, scrollAnchor, LocalDate.now());
            case "teachers" -> TeachersManageScreenView.view(mainAnchor, scroll, scrollAnchor);
            case "classes" -> System.out.println("Wszyscy uczniowie");
            case "everybody" -> EverybodyManageScreenView.view(mainAnchor, scroll, scrollAnchor);
            case "your_class" -> System.out.println("Twoja klasa");
        }
    }
}
