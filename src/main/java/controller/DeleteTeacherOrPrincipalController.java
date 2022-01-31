package controller;

import DAO.DAOPrincipal;
import DAO.DAOTeacher;
import DAO.DAOUser;
import model.User;
import routings.DeleteTeacherOrPrincipalMain;

import java.sql.SQLException;

public class DeleteTeacherOrPrincipalController {
    public static int userID;

    public void delete() throws SQLException {
        User user = new DAOUser().get(userID).get(0);

        if (user.getUser_role().equals("PRINCIPAL")) {
            new DAOPrincipal().delete(new DAOPrincipal().getByUserID(userID).get(0));
        } else {
            new DAOTeacher().delete(new DAOTeacher().getByUserID(userID).get(0));
        }

        new DAOUser().delete(user);
        new DeleteTeacherOrPrincipalMain().closeStageByDeleting();
    }

    public void notDelete() {
        new DeleteTeacherOrPrincipalMain().closeStageByDeleting();
    }
}
