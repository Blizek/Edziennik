package model;

public class LessonStudentList {
    private int lesson_id;
    private int student_id;

    public LessonStudentList(int lesson_id, int student_id) {
        this.lesson_id = lesson_id;
        this.student_id = student_id;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    @Override
    public String toString() {
        return "LessonStudentList{" +
                "lesson_id=" + lesson_id +
                ", student_id=" + student_id +
                '}';
    }
}
