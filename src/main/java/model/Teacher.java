package model;

public class Teacher {
    private int teacher_id;
    private int user_id;
    private int school_id;
    private String teacher_name;
    private String teacher_surname;
    private String teacher_subject;

    public Teacher(int teacher_id, int user_id, int school_id, String teacher_name, String teacher_surname, String teacher_subject) {
        this.teacher_id = teacher_id;
        this.user_id = user_id;
        this.school_id = school_id;
        this.teacher_name = teacher_name;
        this.teacher_surname = teacher_surname;
        this.teacher_subject = teacher_subject;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getSchool_id() {
        return school_id;
    }

    public void setSchool_id(int school_id) {
        this.school_id = school_id;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getTeacher_surname() {
        return teacher_surname;
    }

    public void setTeacher_surname(String teacher_surname) {
        this.teacher_surname = teacher_surname;
    }

    public String getTeacher_subject() {
        return teacher_subject;
    }

    public void setTeacher_subject(String teacher_subject) {
        this.teacher_subject = teacher_subject;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacher_id=" + teacher_id +
                ", user_id=" + user_id +
                ", school_id=" + school_id +
                ", teacher_name='" + teacher_name + '\'' +
                ", teacher_surname='" + teacher_surname + '\'' +
                ", teacher_subject='" + teacher_subject + '\'' +
                '}';
    }
}
