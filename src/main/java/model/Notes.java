package model;

import java.sql.Timestamp;

public class Notes {
    private int note_id;
    private int student_id;
    private int teacher_id;
    private String note_description;
    private int note_value;
    private Timestamp note_date;

    public Notes(int note_id, int student_id, int teacher_id, String note_description, int note_value, Timestamp note_date) {
        this.note_id = note_id;
        this.student_id = student_id;
        this.teacher_id = teacher_id;
        this.note_description = note_description;
        this.note_value = note_value;
        this.note_date = note_date;
    }

    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getNote_description() {
        return note_description;
    }

    public void setNote_description(String note_description) {
        this.note_description = note_description;
    }

    public int getNote_value() {
        return note_value;
    }

    public void setNote_value(int note_value) {
        this.note_value = note_value;
    }

    public Timestamp getNote_date() {
        return note_date;
    }

    public void setNote_date(Timestamp note_date) {
        this.note_date = note_date;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "note_id=" + note_id +
                ", student_id=" + student_id +
                ", teacher_id=" + teacher_id +
                ", note_description='" + note_description + '\'' +
                ", note_value=" + note_value +
                ", note_date=" + note_date +
                '}';
    }
}
