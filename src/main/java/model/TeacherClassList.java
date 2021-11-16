package model;

public class TeacherClassList {
    private int teacher_id;
    private int class_id;

    public TeacherClassList(int teacher_id, int class_id) {
        this.teacher_id = teacher_id;
        this.class_id = class_id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    @Override
    public String toString() {
        return "TeacherClassList{" +
                "teacher_id=" + teacher_id +
                ", class_id=" + class_id +
                '}';
    }
}
