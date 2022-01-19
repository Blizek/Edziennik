package model;

public class Plan {
    private int plan_id;
    private int class_id;
    private int teacher_id;
    private String day;
    private String start_hour;
    private String finish_hour;

    public Plan(int plan_id, int class_id, int teacher_id, String day, String start_hour, String finish_hour) {
        this.plan_id = plan_id;
        this.class_id = class_id;
        this.teacher_id = teacher_id;
        this.day = day;
        this.start_hour = start_hour;
        this.finish_hour = finish_hour;
    }

    public int getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStart_hour() {
        return start_hour;
    }

    public void setStart_hour(String start_hour) {
        this.start_hour = start_hour;
    }

    public String getFinish_hour() {
        return finish_hour;
    }

    public void setFinish_hour(String finish_hour) {
        this.finish_hour = finish_hour;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "plan_id=" + plan_id +
                ", class_id=" + class_id +
                ", teacher_id=" + teacher_id +
                ", day='" + day + '\'' +
                ", start_hour='" + start_hour + '\'' +
                ", finish_hour='" + finish_hour + '\'' +
                '}';
    }
}
