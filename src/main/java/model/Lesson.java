package model;

public class Lesson {
    private int lesson_id;
    private String lesson_subject;
    private boolean student_presence;

    public Lesson(int lesson_id, String lesson_subject, boolean student_presence) {
        this.lesson_id = lesson_id;
        this.lesson_subject = lesson_subject;
        this.student_presence = student_presence;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    public String getLesson_subject() {
        return lesson_subject;
    }

    public void setLesson_subject(String lesson_subject) {
        this.lesson_subject = lesson_subject;
    }

    public boolean isStudent_presence() {
        return student_presence;
    }

    public void setStudent_presence(boolean student_presence) {
        this.student_presence = student_presence;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "lesson_id=" + lesson_id +
                ", lesson_subject='" + lesson_subject + '\'' +
                ", student_presence=" + student_presence +
                '}';
    }
}
