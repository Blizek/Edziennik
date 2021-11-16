package model;

public class GuardianStudentList {
    private int student_id;
    private int guardian_id;

    public GuardianStudentList(int student_id, int guardian_id) {
        this.student_id = student_id;
        this.guardian_id = guardian_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getGuardian_id() {
        return guardian_id;
    }

    public void setGuardian_id(int guardian_id) {
        this.guardian_id = guardian_id;
    }

    @Override
    public String toString() {
        return "GuardianStudentList{" +
                "student_id=" + student_id +
                ", guardian_id=" + guardian_id +
                '}';
    }
}
