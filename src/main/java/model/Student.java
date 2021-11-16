package model;

import java.time.LocalDate;

public class Student {
    private int student_id;
    private int user_id;
    private int school_id;
    private int class_id;
    private String student_name;
    private String student_surname;
    private LocalDate student_date_of_birth;

    public Student(int student_id, int user_id, int school_id, int class_id, String student_name, String student_surname, LocalDate student_date_of_birth) {
        this.student_id = student_id;
        this.user_id = user_id;
        this.school_id = school_id;
        this.class_id = class_id;
        this.student_name = student_name;
        this.student_surname = student_surname;
        this.student_date_of_birth = student_date_of_birth;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
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

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_surname() {
        return student_surname;
    }

    public void setStudent_surname(String student_surname) {
        this.student_surname = student_surname;
    }

    public LocalDate getStudent_date_of_birth() {
        return student_date_of_birth;
    }

    public void setStudent_date_of_birth(LocalDate student_date_of_birth) {
        this.student_date_of_birth = student_date_of_birth;
    }

    @Override
    public String toString() {
        return "Student{" +
                "student_id=" + student_id +
                ", user_id=" + user_id +
                ", school_id=" + school_id +
                ", class_id=" + class_id +
                ", student_name='" + student_name + '\'' +
                ", student_surname='" + student_surname + '\'' +
                ", student_date_of_birth=" + student_date_of_birth +
                '}';
    }
}
