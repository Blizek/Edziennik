package model;

public class Teacher {
    private int teacher_id;
    private int user_id;
    private int school_id;
    private int subject_id;
    private String teacher_name;
    private String teacher_surname;
    private Class teacherClass;

    public Teacher(int teacher_id, int user_id, int school_id, int subject_id, String teacher_name, String teacher_surname) {
        this.teacher_id = teacher_id;
        this.user_id = user_id;
        this.school_id = school_id;
        this.subject_id = subject_id;
        this.teacher_name = teacher_name;
        this.teacher_surname = teacher_surname;
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

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
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


    public Class getTeacherClass() {
        return teacherClass;
    }

    public void setTeacherClass(Class teacherClass) {
        this.teacherClass = teacherClass;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacher_id=" + teacher_id +
                ", user_id=" + user_id +
                ", school_id=" + school_id +
                ", subject_id=" + subject_id +
                ", teacher_name='" + teacher_name + '\'' +
                ", teacher_surname='" + teacher_surname + '\'' +
                '}';
    }
}
