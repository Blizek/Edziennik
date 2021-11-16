package model;

public class Principal {
    private int principal_id;
    private int user_id;
    private String principal_name;
    private String principal_surname;

    public Principal(int principal_id, int user_id, String principal_name, String principal_surname) {
        this.principal_id = principal_id;
        this.user_id = user_id;
        this.principal_name = principal_name;
        this.principal_surname = principal_surname;
    }

    public int getPrincipal_id() {
        return principal_id;
    }

    public void setPrincipal_id(int principal_id) {
        this.principal_id = principal_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPrincipal_name() {
        return principal_name;
    }

    public void setPrincipal_name(String principal_name) {
        this.principal_name = principal_name;
    }

    public String getPrincipal_surname() {
        return principal_surname;
    }

    public void setPrincipal_surname(String principal_surname) {
        this.principal_surname = principal_surname;
    }

    @Override
    public String toString() {
        return "Principal{" +
                "principal_id=" + principal_id +
                ", user_id=" + user_id +
                ", principal_name='" + principal_name + '\'' +
                ", principal_surname='" + principal_surname + '\'' +
                '}';
    }
}
