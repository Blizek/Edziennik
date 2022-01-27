package controller;

import DAO.DAONotes;
import enumTypes.DatabaseTablesName;
import features.GetMaxID;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.Notes;
import routings.ManageNoteMain;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ManageNoteController {
    @FXML
    TextField noteValueTextField, noteDescriptionTextField;

    @FXML
    Text noteValueError, noteDescriptionError;

    public static boolean saving;
    public static Notes editingNotes;

    public static int student_id;
    public static int teacher_id;

    int checkedValue;

    public void initialize() {
        if (!saving) {
            noteValueTextField.setText(Integer.toString(editingNotes.getNote_value()));
            noteDescriptionTextField.setText(editingNotes.getNote_description());
        }
    }

    public void submit() throws SQLException {
        String notCheckedValue = noteValueTextField.getText();
        String description = noteDescriptionTextField.getText();

        boolean someError = false;

        try {
            checkedValue = Integer.parseInt(notCheckedValue);
            noteValueError.setVisible(false);
        } catch (Exception e) {
            someError = true;
            noteValueError.setVisible(true);
        }

        if (description.length() < 1 || description.length() > 200) {
            someError = true;
            noteDescriptionError.setVisible(true);
        } else {
            noteDescriptionError.setVisible(false);
        }

        if (!someError) {
            if (!saving) {
                Notes note = editingNotes;
                note.setNote_value(checkedValue);
                note.setNote_description(description);
                new DAONotes().update(note);
            } else {
                Notes note = new Notes(GetMaxID.get(DatabaseTablesName.NOTES) + 1, student_id, teacher_id, description,
                        checkedValue, Timestamp.valueOf(LocalDateTime.now()));
                new DAONotes().save(note);
            }
            new ManageNoteMain().closeStageByAddingNote();
        }
    }
}
