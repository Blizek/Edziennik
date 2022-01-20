package features;

import DAO.DAOSchoolSubject;
import DAO.DAOStudent;
import model.SchoolSubject;
import model.Student;
import model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GetAllStudentSubjects {
    public static List<SchoolSubject> get() throws SQLException {
        ArrayList<Integer> subjectsID = new ArrayList<>();
        ArrayList<SchoolSubject> subjects = new ArrayList<>();

        User user = GetUser.get();
        Student student;

        if (user.getUser_role().equals("STUDENT")) student = GetStudent.getForStudent(user.getUser_id());
        else student = GetStudent.getForGuardian(user.getUser_id());

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
