package features;

import DAO.DAOEmail;
import model.Email;

import java.sql.SQLException;
import java.util.List;

public class GetEmailsFromPeriod {
    public static List<Email> get(String startDay, String finishDay) throws SQLException {
        return new DAOEmail().getAllFromPeriod(startDay, finishDay);
    }
}
