package features;

import DAO.DAOUser;
import model.User;
import variables.UserIDHolder;

import java.sql.SQLException;
import java.util.List;

public class GetUser {
    public static User get() throws SQLException {
        DAOUser userDao = new DAOUser();
        List<User> users = userDao.get(UserIDHolder.getUserID());
        return users.get(0);
    }
}
