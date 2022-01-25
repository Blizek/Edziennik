package model;

public class Absences {
    private int absence_id;
    private int student_id;
    private int lesson_id;
    private boolean student_absence;
    private boolean excused_absence;

    public Absences(int absence_id, int student_id, int lesson_id, boolean student_absence, boolean excused_absence) {
        this.absence_id = absence_id;
        this.student_id = student_id;
        this.lesson_id = lesson_id;
        this.student_absence = student_absence;
        this.excused_absence = excused_absence;
    }

    public int getAbsence_id() {
        return absence_id;
    }

    public void setAbsence_id(int absence_id) {
        this.absence_id = absence_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    public boolean isStudent_absence() {
        return student_absence;
    }

    public void setStudent_absence(boolean student_absence) {
        this.student_absence = student_absence;
    }

    public boolean isExcused_absence() {
        return excused_absence;
    }

    public void setExcused_absence(boolean excused_absence) {
        this.excused_absence = excused_absence;
    }

    @Override
    public String toString() {
        return "Absences{" +
                "absence_id=" + absence_id +
                ", student_id=" + student_id +
                ", lesson_id=" + lesson_id +
                ", student_absence=" + student_absence +
                ", excused_absence=" + excused_absence +
                '}';
    }
}
