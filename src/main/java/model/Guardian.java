package model;

import java.time.LocalDate;

public class Guardian {
    private int guardian_id;
    private int user_id;
    private int student_id;
    private String guardian_name;
    private String guardian_surname;
    private LocalDate guardian_date_of_birth;

    public Guardian(int guardian_id, int user_id, int student_id, String guardian_name, String guardian_surname, LocalDate guardian_date_of_birth) {
        this.guardian_id = guardian_id;
        this.user_id = user_id;
        this.student_id = student_id;
        this.guardian_name = guardian_name;
        this.guardian_surname = guardian_surname;
        this.guardian_date_of_birth = guardian_date_of_birth;
    }

    public int getGuardian_id() {
        return guardian_id;
    }

    public void setGuardian_id(int guardian_id) {
        this.guardian_id = guardian_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getGuardian_name() {
        return guardian_name;
    }

    public void setGuardian_name(String guardian_name) {
        this.guardian_name = guardian_name;
    }

    public String getGuardian_surname() {
        return guardian_surname;
    }

    public void setGuardian_surname(String guardian_surname) {
        this.guardian_surname = guardian_surname;
    }

    public LocalDate getGuardian_date_of_birth() {
        return guardian_date_of_birth;
    }

    public void setGuardian_date_of_birth(LocalDate guardian_date_of_birth) {
        this.guardian_date_of_birth = guardian_date_of_birth;
    }

    @Override
    public String toString() {
        return "Guardian{" +
                "guardian_id=" + guardian_id +
                ", user_id=" + user_id +
                ", student_id=" + student_id +
                ", guardian_name='" + guardian_name + '\'' +
                ", guardian_surname='" + guardian_surname + '\'' +
                ", guardian_date_of_birth=" + guardian_date_of_birth +
                '}';
    }
}
