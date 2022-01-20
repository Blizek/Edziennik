package features;

import DAO.*;
import model.*;

import java.sql.SQLException;

public class GetNameAndSurnameByTableID {
    public static String getTeacher(int teacherID) throws SQLException {
        Teacher teacher = new DAOTeacher().get(teacherID).get(0);
        String name = teacher.getTeacher_name();
        String surname = teacher.getTeacher_surname();
        return name + " " + surname;
    }

    public static String getStudent(int studentID) throws SQLException {
        Student student = new DAOStudent().get(studentID).get(0);
        String name = student.getStudent_name();
        String surname = student.getStudent_surname();
        return name + " " + surname;
    }

    public static String getGuardian(int guardianID) throws SQLException {
        Guardian guardian = new DAOGuardian().get(guardianID).get(0);
        String name = guardian.getGuardian_name();
        String surname = guardian.getGuardian_surname();
        return name + " " + surname;
    }

    public static String getPrincipal(int principalID) throws SQLException {
        Principal principal = new DAOPrincipal().get(principalID).get(0);
        String name = principal.getPrincipal_name();
        String surname = principal.getPrincipal_surname();
        return name + " " + surname;
    }

    public static String getAdmin(int adminID) throws SQLException {
        Admin admin = new DAOAdmin().get(adminID).get(0);
        String name = admin.getAdmin_name();
        String surname = admin.getAdmin_surname();
        return name + " " + surname;
    }
}
