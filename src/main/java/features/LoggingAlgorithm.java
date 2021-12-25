package features;

import DAO.DAOUser;
import com.jfoenix.controls.JFXToggleButton;
import model.User;
import variables.UserIDHolder;

import java.sql.SQLException;
import java.util.List;

public class LoggingAlgorithm {
    /** whole algorithm to check data from inputs in loginScreen with data in database **/
    public static boolean algorithm(String email, String password, JFXToggleButton rememberMeToggleButton) throws SQLException {
        // getting all users which email address from input is in database
        DAOUser userDao = new DAOUser();
        List<User> users = userDao.getByEmail(email);
        // if there is not the same email in database like from input show valid logging data
        if (users.isEmpty()) return false;
        else {
            // else check if password is also correct
            User user = users.get(0);
            if (!user.getUser_email().equals(email) || !user.getUser_password().equals(password)) return false;
            else {
                // if RememberMeToggleButton is selected remember user's data and after that log to app
                if (rememberMeToggleButton.isSelected()) SetRememberMeData.setData(email, password);
                UserIDHolder.setUserID(user.getUser_id());
                return true;
            }
        }
    }
}
