package model;

import java.sql.Date;
import java.sql.Timestamp;

public class Email {
    private int email_id;
    private int sender_user_id;
    private int recipient_user_id;
    private String email_subject;
    private String email_text;
    private Timestamp email_send_date;
    private boolean email_received;

    public Email(int email_id, int sender_user_id, int recipient_user_id, String email_subject, String email_text, Timestamp email_send_date, boolean email_received) {
        this.email_id = email_id;
        this.sender_user_id = sender_user_id;
        this.recipient_user_id = recipient_user_id;
        this.email_subject = email_subject;
        this.email_text = email_text;
        this.email_send_date = email_send_date;
        this.email_received = email_received;
    }

    public int getEmail_id() {
        return email_id;
    }

    public void setEmail_id(int email_id) {
        this.email_id = email_id;
    }

    public int getSender_user_id() {
        return sender_user_id;
    }

    public void setSender_user_id(int sender_user_id) {
        this.sender_user_id = sender_user_id;
    }

    public int getRecipient_user_id() {
        return recipient_user_id;
    }

    public void setRecipient_user_id(int recipient_user_id) {
        this.recipient_user_id = recipient_user_id;
    }

    public String getEmail_subject() {
        return email_subject;
    }

    public void setEmail_subject(String email_subject) {
        this.email_subject = email_subject;
    }

    public String getEmail_text() {
        return email_text;
    }

    public void setEmail_text(String email_text) {
        this.email_text = email_text;
    }

    public Timestamp getEmail_send_date() {
        return email_send_date;
    }

    public void setEmail_send_date(Timestamp email_send_date) {
        this.email_send_date = email_send_date;
    }

    public boolean isEmail_received() {
        return email_received;
    }

    public void setEmail_received(boolean email_received) {
        this.email_received = email_received;
    }
}
