package features;

import DAO.DAOTeacher;
import model.Teacher;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetNotDuplicatedTeachers {
    public static List<Teacher> get(int classID) throws SQLException {
        List<Teacher> everyPlanTeacher = new DAOTeacher().getAllScheduleTeachers(classID);

        ArrayList<Integer> teachersID = new ArrayList<>();
        ArrayList<Teacher> notDuplicatedTeachers = new ArrayList<>();

        for (Teacher teacher: everyPlanTeacher) {
            if (!teachersID.contains(teacher.getUser_id())) {
                teachersID.add(teacher.getUser_id());
                notDuplicatedTeachers.add(teacher);
            }
        }

        return notDuplicatedTeachers;
    }

    public static List<Teacher> get() throws SQLException {
        List<Teacher> allTeachers = new DAOTeacher().getAllSorted();

        ArrayList<Integer> teachersID = new ArrayList<>();
        ArrayList<Teacher> notDuplicatedTeachers = new ArrayList<>();

        for (Teacher teacher: allTeachers) {
            if (!teachersID.contains(teacher.getUser_id())) {
                teachersID.add(teacher.getUser_id());
                notDuplicatedTeachers.add(teacher);
            }
        }

        return notDuplicatedTeachers;
    }
}
