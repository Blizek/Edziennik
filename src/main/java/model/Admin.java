package model;

public class Admin {
    private int admin_id;
    private int user_id;
    private String admin_name;
    private String admin_surname;

    Admin(int admin_id, int user_id, String admin_name, String admin_surname) {
        this.admin_id = admin_id;
        this.user_id = user_id;
        this.admin_name = admin_name;
        this.admin_surname = admin_surname;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getAdmin_surname() {
        return admin_surname;
    }

    public void setAdmin_surname(String admin_surname) {
        this.admin_surname = admin_surname;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "admin_id=" + admin_id +
                ", user_id=" + user_id +
                ", admin_name='" + admin_name + '\'' +
                ", admin_surname='" + admin_surname + '\'' +
                '}';
    }
}
