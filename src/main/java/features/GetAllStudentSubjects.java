package features;

import DAO.DAOSchoolSubject;
import DAO.DAOStudent;
import model.SchoolSubject;
import model.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GetAllStudentSubjects {
    public static List<SchoolSubject> get() throws SQLException {
        ArrayList<Integer> subjectsID = new ArrayList<>();
        ArrayList<SchoolSubject> subjects = new ArrayList<>();

        Student student = new DAOStudent().getByUserID(GetUser.get().getUser_id()).get(0);

        List<SchoolSubject> everyLesson = new DAOSchoolSubject().getAllStudentSubjects(student);

        for (SchoolSubject subject: everyLesson) {
            if (!subjectsID.contains(subject.getSubject_id())) {
                subjectsID.add(subject.getSubject_id());
                subjects.add(subject);
            }
        }

        return subjects;
    }
}
