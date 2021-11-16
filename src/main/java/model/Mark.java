package model;

public class Mark {
    private int mark_id;
    private int student_id;
    private int teacher_id;
    private float mark_value;
    private String mark_description;

    public Mark(int mark_id, int student_id, int teacher_id, float mark_value, String mark_description) {
        this.mark_id = mark_id;
        this.student_id = student_id;
        this.teacher_id = teacher_id;
        this.mark_value = mark_value;
        this.mark_description = mark_description;
    }

    public int getMark_id() {
        return mark_id;
    }

    public void setMark_id(int mark_id) {
        this.mark_id = mark_id;
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

    public float getMark_value() {
        return mark_value;
    }

    public void setMark_value(float mark_value) {
        this.mark_value = mark_value;
    }

    public String getMark_description() {
        return mark_description;
    }

    public void setMark_description(String mark_description) {
        this.mark_description = mark_description;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "mark_id=" + mark_id +
                ", student_id=" + student_id +
                ", teacher_id=" + teacher_id +
                ", mark_value=" + mark_value +
                ", mark_description='" + mark_description + '\'' +
                '}';
    }
}
