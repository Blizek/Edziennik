package features;

import DAO.DAOUser;
import model.User;

import java.sql.SQLException;

public class GetUserNameAndRole {
    public static String get(int userID) throws SQLException {
        User user = new DAOUser().get(userID).get(0);
        String userName = "";

        switch (user.getUser_role()) {
            case "STUDENT" -> userName = GetUserNameAndSurnameByUserID.getStudentName(userID);
            case "TEACHER" -> userName = GetUserNameAndSurnameByUserID.getTeacherName(userID);
            case "PRINCIPAL" -> userName = GetUserNameAndSurnameByUserID.getPrincipalName(userID);
            case "GUARDIAN" -> userName = GetUserNameAndSurnameByUserID.getGuardianName(userID);
            case "ADMIN" -> userName = GetUserNameAndSurnameByUserID.getAdminName(userID);
        }

        return userName + " - " + user.getUser_role();
    }
}
