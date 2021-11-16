package model;

public class Class {
    private int class_id;
    private int class_supervising_teacher;
    private String class_name;

    public Class(int class_id, int class_supervising_teacher, String class_name) {
        this.class_id = class_id;
        this.class_supervising_teacher = class_supervising_teacher;
        this.class_name = class_name;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public int getClass_supervising_teacher() {
        return class_supervising_teacher;
    }

    public void setClass_supervising_teacher(int class_supervising_teacher) {
        this.class_supervising_teacher = class_supervising_teacher;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    @Override
    public String toString() {
        return "Class{" +
                "class_id=" + class_id +
                ", class_supervising_teacher=" + class_supervising_teacher +
                ", class_name='" + class_name + '\'' +
                '}';
    }
}
