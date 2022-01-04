package features;

import DAO.DAOUser;
import model.User;

import java.sql.SQLException;

public class GetUserNameAndRole {
    public static String get(int userID) throws SQLException {
        User user = new DAOUser().get(userID).get(0);
        String userName = "";

        switch (user.getUser_role()) {
            case "STUDENT" -> userName = GetUserNameAndSurname.getStudentName(userID);
            case "TEACHER" -> userName = GetUserNameAndSurname.getTeacherName(userID);
            case "PRINCIPAL" -> userName = GetUserNameAndSurname.getPrincipalName(userID);
            case "GUARDIAN" -> userName = GetUserNameAndSurname.getGuardianName(userID);
            case "ADMIN" -> userName = GetUserNameAndSurname.getAdminName(userID);
        }

        return userName + " - " + user.getUser_role();
    }
}
