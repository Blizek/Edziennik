package model;

public class School {
    private int school_id;
    private int principal_id;
    private String school_name;
    private String school_place;
    private String school_email;
    private String school_phone_number;

    public School(int school_id, int principal_id, String school_name, String school_place, String school_email, String school_phone_number) {
        this.school_id = school_id;
        this.principal_id = principal_id;
        this.school_name = school_name;
        this.school_place = school_place;
        this.school_email = school_email;
        this.school_phone_number = school_phone_number;
    }

    public int getSchool_id() {
        return school_id;
    }

    public void setSchool_id(int school_id) {
        this.school_id = school_id;
    }

    public int getPrincipal_id() {
        return principal_id;
    }

    public void setPrincipal_id(int principal_id) {
        this.principal_id = principal_id;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public String getSchool_place() {
        return school_place;
    }

    public void setSchool_place(String school_place) {
        this.school_place = school_place;
    }

    public String getSchool_email() {
        return school_email;
    }

    public void setSchool_email(String school_email) {
        this.school_email = school_email;
    }

    public String getSchool_phone_number() {
        return school_phone_number;
    }

    public void setSchool_phone_number(String school_phone_number) {
        this.school_phone_number = school_phone_number;
    }

    @Override
    public String toString() {
        return "School{" +
                "school_id=" + school_id +
                ", principal_id=" + principal_id +
                ", school_name='" + school_name + '\'' +
                ", school_place='" + school_place + '\'' +
                ", school_email='" + school_email + '\'' +
                ", school_phone_number='" + school_phone_number + '\'' +
                '}';
    }
}
