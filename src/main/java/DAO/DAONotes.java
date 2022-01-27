package DAO;

import database.DBConnection;
import model.Notes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAONotes implements DAO<Notes> {
    Connection connection = DBConnection.getConnection();
    Statement statement = connection.createStatement();

    public DAONotes() throws SQLException {
    }

    @Override
    public List<Notes> get(int id) throws SQLException {
        String sql = "SELECT * FROM notes WHERE note_id = " + id;
        return notesDatabaseCommends(sql);
    }

    @Override
    public List<Notes> getAll() throws SQLException {
        String sql = "SELECT * FROM notes";
        return notesDatabaseCommends(sql);
    }

    public List<Notes> getStudentNotes(int student_id, String moreLessOrEqualZeroSign) throws SQLException {
        String sql = "SELECT notes.* FROM notes INNER JOIN student ON notes.student_id = student.student_id WHERE " +
                "student.student_id = " + student_id + " AND notes.note_value " + moreLessOrEqualZeroSign + " 0";
        return notesDatabaseCommends(sql);
    }

    public List<Notes> getStudentNotesFromTeacher(int student_id, int teacher_id, String moreLessEqualZeroSign) throws SQLException {
        String sql = "SELECT notes.* FROM teacher INNER JOIN notes ON teacher.teacher_id = notes.teacher_id INNER JOIN " +
                "student ON notes.student_id = student.student_id WHERE student.student_id = " + student_id + " " +
                "AND teacher.teacher_id = " + teacher_id + " AND notes.note_value " + moreLessEqualZeroSign + " 0";
        return notesDatabaseCommends(sql);
    }

    @Override
    public void save(Notes notes) throws SQLException {
        int note_id = notes.getNote_id();
        int student_id = notes.getStudent_id();
        int teacher_id = notes.getTeacher_id();
        String note_description = notes.getNote_description();
        int note_value = notes.getNote_value();
        String note_date = notes.getNote_date().toString().substring(0, 19);
        String sql = "INSERT INTO notes(note_id, student_id, teacher_id, note_description, note_value, note_date) VALUES("
                + note_id + ", " + student_id + ", " + teacher_id + ", '" + note_description + "', " + note_value + ", '"
                + note_date + "')";
        statement.executeUpdate(sql);
    }

    @Override
    public void update(Notes notes) throws SQLException {
        int new_note_id = notes.getNote_id();
        int new_student_id = notes.getStudent_id();
        int new_teacher_id = notes.getTeacher_id();
        String new_note_description = notes.getNote_description();
        int new_note_value = notes.getNote_value();
        String new_note_date = notes.getNote_date().toString().substring(0, 19);
        String sql = "UPDATE notes SET note_id = " + new_note_id + ", student_id = " + new_student_id + ", teacher_id = "
                + new_teacher_id + ", note_description = '" + new_note_description + "', note_value = " + new_note_value
                + ", note_date = '" + new_note_date + "' WHERE note_id = " + new_note_id;
        statement.executeUpdate(sql);
    }

    @Override
    public void delete(Notes notes) throws SQLException {
        int note_id = notes.getNote_id();
        String sql = "DELETE FROM notes WHERE note_id = " + note_id;
        statement.executeUpdate(sql);
    }

    private List<Notes> notesDatabaseCommends(String sql) throws SQLException {
        ResultSet resultSet = statement.executeQuery(sql);
        List<Notes> notes = new ArrayList<>();
        while (resultSet.next()) {
            int note_id = resultSet.getInt(1);
            int student_id = resultSet.getInt(2);
            int teacher_id = resultSet.getInt(3);
            String note_description = resultSet.getString(4);
            int note_value = resultSet.getInt(5);
            Timestamp note_date = resultSet.getTimestamp(6);
            notes.add(new Notes(note_id, student_id, teacher_id, note_description, note_value, note_date));
        }
        return notes;
    }
}
