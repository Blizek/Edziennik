package model;

import java.sql.Timestamp;

public class Exams {
    private int exam_id;
    private int class_id;
    private int plan_id;
    private String exam_description;
    private Timestamp exam_date;

    public Exams(int exam_id, int class_id, int plan_id, String exam_description, Timestamp exam_date) {
        this.exam_id = exam_id;
        this.class_id = class_id;
        this.plan_id = plan_id;
        this.exam_description = exam_description;
        this.exam_date = exam_date;
    }

    public int getExam_id() {
        return exam_id;
    }

    public void setExam_id(int exam_id) {
        this.exam_id = exam_id;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public int getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }

    public String getExam_description() {
        return exam_description;
    }

    public void setExam_description(String exam_description) {
        this.exam_description = exam_description;
    }

    public Timestamp getExam_date() {
        return exam_date;
    }

    public void setExam_date(Timestamp exam_date) {
        this.exam_date = exam_date;
    }

    @Override
    public String toString() {
        return "Exams{" +
                "exam_id=" + exam_id +
                ", class_id=" + class_id +
                ", plan_id=" + plan_id +
                ", exam_description='" + exam_description + '\'' +
                ", exam_date=" + exam_date +
                '}';
    }
}
