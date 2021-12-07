package features;

import DAO.DAOUser;
import com.jfoenix.controls.JFXToggleButton;
import model.User;
import variables.UserIDHolder;

import java.sql.SQLException;
import java.util.List;

public class LoggingAlgorithm {
    public static boolean algorithm(String email, String password, JFXToggleButton rememberMeToggleButton) throws SQLException {
        DAOUser userDao = new DAOUser();
        List<User> users = userDao.getByEmail(email);
        if (users.isEmpty()) return false;
        else {
            User user = users.get(0);
            if (!user.getUser_email().equals(email) || !user.getUser_password().equals(password)) return false;
            else {
                if (rememberMeToggleButton.isSelected()) new SetRememberMeData().setData(email, password);
                UserIDHolder.setUserID(user.getUser_id());
                return true;
            }
        }
    }
}
