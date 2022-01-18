package model;

import java.sql.Timestamp;

public class Lesson {
    private int lesson_id;
    private int teacher_id;
    private String lesson_subject;
    private Timestamp lesson_date;
    private Student student;

    public Lesson(int lesson_id, int teacher_id, String lesson_subject, Timestamp lesson_date) {
        this.lesson_id = lesson_id;
        this.teacher_id = teacher_id;
        this.lesson_subject = lesson_subject;
        this.lesson_date = lesson_date;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getLesson_subject() {
        return lesson_subject;
    }

    public void setLesson_subject(String lesson_subject) {
        this.lesson_subject = lesson_subject;
    }

    public Timestamp getLesson_date() {
        return lesson_date;
    }

    public void setLesson_date(Timestamp lesson_date) {
        this.lesson_date = lesson_date;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "lesson_id=" + lesson_id +
                ", teacher_id=" + teacher_id +
                ", lesson_subject='" + lesson_subject + '\'' +
                ", lesson_date=" + lesson_date +
                '}';
    }
}
