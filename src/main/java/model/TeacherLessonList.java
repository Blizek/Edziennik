package model;

public class TeacherLessonList {
    private int teacher_id;
    private int lesson_id;

    public TeacherLessonList(int teacher_id, int lesson_id) {
        this.teacher_id = teacher_id;
        this.lesson_id = lesson_id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    @Override
    public String toString() {
        return "TeacherLessonList{" +
                "teacher_id=" + teacher_id +
                ", lesson_id=" + lesson_id +
                '}';
    }
}
