package model;

public class FinalGrade {
    private int grade_id;
    private int student_id;
    private int subject_id;
    private int grade_value;

    public FinalGrade(int grade_id, int student_id, int subject_id, int grade_value) {
        this.grade_id = grade_id;
        this.student_id = student_id;
        this.subject_id = subject_id;
        this.grade_value = grade_value;
    }

    public int getGrade_id() {
        return grade_id;
    }

    public void setGrade_id(int grade_id) {
        this.grade_id = grade_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public int getGrade_value() {
        return grade_value;
    }

    public void setGrade_value(int grade_value) {
        this.grade_value = grade_value;
    }

    @Override
    public String toString() {
        return "FinalGrade{" +
                "grade_id=" + grade_id +
                ", student_id=" + student_id +
                ", subject_id=" + subject_id +
                ", grade_value=" + grade_value +
                '}';
    }
}
