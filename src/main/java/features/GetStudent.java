package features;

import DAO.DAOGuardian;
import DAO.DAOStudent;
import model.Guardian;
import model.Student;

import java.sql.SQLException;

public class GetStudent {
    public static Student getForStudent(int userID) throws SQLException {
        return new DAOStudent().getByUserID(userID).get(0);
    }

    public static Student getForGuardian(int userID) throws SQLException {
        Guardian guardian = new DAOGuardian().getByUserID(userID).get(0);
        return new DAOStudent().get(guardian.getStudent_id()).get(0);
    }
}
