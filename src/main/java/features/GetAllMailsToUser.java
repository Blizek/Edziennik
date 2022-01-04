package features;

import DAO.DAOEmail;
import model.Email;

import java.sql.SQLException;
import java.util.List;

public class GetAllMailsToUser {
    public static List<Email> getAll(int ID) throws SQLException {
        DAOEmail daoEmail = new DAOEmail();
        return daoEmail.getByRecipientID(ID);
    }

    public static List<Email> getNotOpened(int ID) throws SQLException {
        DAOEmail daoEmail = new DAOEmail();
        return daoEmail.getAllNotOpenedMails(ID);
    }
}
