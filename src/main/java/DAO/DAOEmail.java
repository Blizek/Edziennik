package DAO;

import database.DBConnection;
import model.Email;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOEmail implements DAO<Email> {
    Connection connection = DBConnection.getConnection();
    Statement statement = connection.createStatement();

    public DAOEmail() throws SQLException {
    }

    @Override
    public List<Email> get(int id) throws SQLException {
        String sql = "SELECT * FROM email WHERE email_id = " + id;
        return emailDatabaseCommends(sql);
    }

    @Override
    public List<Email> getAll() throws SQLException {
        String sql = "SELECT * FROM email";
        return emailDatabaseCommends(sql);
    }

    public List<Email> getByRecipientID(int id) throws SQLException {
        String sql = "SELECT * FROM email WHERE recipient_user_id = " + id;
        return emailDatabaseCommends(sql);
    }

    public List<Email> getAllNotOpenedMails(int id) throws SQLException {
        String sql = "SELECT * FROM email WHERE recipient_user_id = " + id + " AND email_received = false";
        return emailDatabaseCommends(sql);
    }

    public List<Email> getAllFromPeriod(String startDay, String finishDay) throws SQLException {
        String sql = "SELECT * FROM email WHERE email_send_date >= '" + startDay + "' and email_send_date < '" + finishDay + "'";
        return emailDatabaseCommends(sql);
    }

    public List<Email> getAllSameEmails(int sender_user_id, String email_send_date) throws SQLException {
        String sql = "SELECT * FROM email WHERE sender_user_id = " + sender_user_id + " AND email_send_date = '" + email_send_date.substring(0, 19) + "'";
        return emailDatabaseCommends(sql);
    }

    @Override
    public void save(Email email) throws SQLException {
        int email_id = email.getEmail_id();
        int sender_user_id = email.getSender_user_id();
        int recipient_user_id = email.getRecipient_user_id();
        String email_subject = email.getEmail_subject();
        String email_text = email.getEmail_text();
        String email_send_date = email.getEmail_send_date().toString().substring(0, 19);
        boolean email_received = email.isEmail_received();
        String sql = "INSERT INTO email (email_id, sender_user_id, recipient_user_id, email_subject, email_text, email_send_date, email_received) VALUES ("
                + email_id + ", " + sender_user_id + ", " + recipient_user_id + ", '" + email_subject + "', '" + email_text
                + "', '" + email_send_date + "', " + email_received + ")";
        statement.executeUpdate(sql);
    }

    @Override
    public void update(Email email) throws SQLException {
        int new_email_id = email.getEmail_id();
        int new_sender_user_id = email.getSender_user_id();
        int new_recipient_user_id = email.getRecipient_user_id();
        String new_email_subject = email.getEmail_subject();
        String new_email_text = email.getEmail_text();
        String new_email_send_date = email.getEmail_send_date().toString().substring(0, 19);
        boolean new_email_received = email.isEmail_received();
        String sql = "UPDATE email SET email_id = " + new_email_id + ", sender_user_id = " + new_sender_user_id + ", recipient_user_id = "
                + new_recipient_user_id + ", email_subject = '" + new_email_subject + "', email_text = '" + new_email_text
                + "', email_send_date = '" + new_email_send_date + "', email_received = " + new_email_received
                + " WHERE email_id = " + new_email_id;
        statement.executeUpdate(sql);
    }

    @Override
    public void delete(Email email) throws SQLException {
        int email_id = email.getEmail_id();
        String sql = "DELETE FROM email WHERE email_id = " + email_id;
        statement.executeUpdate(sql);
    }

    private List<Email> emailDatabaseCommends(String sql) throws SQLException {
        ResultSet resultSet = statement.executeQuery(sql);
        List<Email> emails = new ArrayList<>();
        while (resultSet.next()) {
            int email_id = resultSet.getInt(1);
            int sender_user_id = resultSet.getInt(2);
            int recipient_user_id = resultSet.getInt(3);
            String email_subject = resultSet.getString(4);
            String email_text = resultSet.getString(5);
            Timestamp email_send_date = resultSet.getTimestamp(6);
            boolean email_received = resultSet.getBoolean(7);
            emails.add(new Email(email_id, sender_user_id, recipient_user_id, email_subject, email_text, email_send_date, email_received));
        }
        return emails;
    }
}
