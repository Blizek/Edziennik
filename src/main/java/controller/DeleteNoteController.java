package controller;

import DAO.DAONotes;
import model.Notes;
import routings.DeleteNoteMain;

import java.sql.SQLException;

public class DeleteNoteController {
    public static Notes note;

    public void deleteNote() throws SQLException {
        new DAONotes().delete(note);
        new DeleteNoteMain().closeStageByDeletingNote();
    }

    public void notDeleteNote() {
        new DeleteNoteMain().closeStageByDeletingNote();
    }
}
