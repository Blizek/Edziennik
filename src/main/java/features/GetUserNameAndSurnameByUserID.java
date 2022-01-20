package features;

import DAO.*;
import model.*;

import java.sql.SQLException;

public class GetUserNameAndSurnameByUserID {
    public static String getStudentName(int id) throws SQLException {
        Student student = new DAOStudent().getByUserID(id).get(0);
        return student.getStudent_name() + " " + student.getStudent_surname();
    }

    public static String getTeacherName(int id) throws SQLException {
        Teacher teacher = new DAOTeacher().getByUserID(id).get(0);
        return teacher.getTeacher_name() + " " + teacher.getTeacher_surname();
    }

    public static String getPrincipalName(int id) throws SQLException {
        Principal principal = new DAOPrincipal().getByUserID(id).get(0);
        return principal.getPrincipal_name() + " " + principal.getPrincipal_surname();
    }

    public static String getGuardianName(int id) throws SQLException {
        Guardian guardian = new DAOGuardian().getByUserID(id).get(0);
        return guardian.getGuardian_name() + " " + guardian.getGuardian_surname();
    }

    public static String getAdminName(int id) throws SQLException {
        Admin admin = new DAOAdmin().getByUserID(id).get(0);
        return admin.getAdmin_name() + " " + admin.getAdmin_surname();
    }
}
