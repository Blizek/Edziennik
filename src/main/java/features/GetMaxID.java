package features;

import DAO.*;
import model.*;
import model.Class;

import java.sql.SQLException;
import java.util.List;

import static enumTypes.DatabaseTablesName.*;

public class GetMaxID {
    public static int get(Enum tableName) throws SQLException {
        int ID = -1;
        if (ABSENCES.equals(tableName)) {
            DAOAbsences daoAbsences = new DAOAbsences();
            List<Absences> allAbsences = daoAbsences.getAll();
            ID = allAbsences.get(allAbsences.size() - 1).getAbsence_id();
        } else if (ADMIN.equals(tableName)) {
            DAOAdmin daoAdmin = new DAOAdmin();
            List<Admin> allAdmins = daoAdmin.getAll();
            ID = allAdmins.get(allAdmins.size() - 1).getAdmin_id();
        } else if (CLASS.equals(tableName)) {
            DAOClass daoClass = new DAOClass();
            List<Class> allClasses = daoClass.getAll();
            ID = allClasses.get(allClasses.size() - 1).getClass_id();
        } else if (EMAIL.equals(tableName)) {
            DAOEmail daoEmail = new DAOEmail();
            List<Email> allEmails = daoEmail.getAll();
            ID = allEmails.get(allEmails.size() - 1).getEmail_id();
        } else if (FINAL_GRADE.equals(tableName)) {
            DAOFinalGrade daoFinalGrade = new DAOFinalGrade();
            List<FinalGrade> allFinalGrades = daoFinalGrade.getAll();
            ID = allFinalGrades.get(allFinalGrades.size() - 1).getGrade_id();
        } else if (GUARDIAN.equals(tableName)) {
            DAOGuardian daoGuardian = new DAOGuardian();
            List<Guardian> allGuardians = daoGuardian.getAll();
            ID = allGuardians.get(allGuardians.size() - 1).getGuardian_id();
        } else if (LESSON.equals(tableName)) {
            DAOLesson daoLesson = new DAOLesson();
            List<Lesson> allLessons = daoLesson.getAll();
            ID = allLessons.get(allLessons.size() - 1).getLesson_id();
        } else if (MARK.equals(tableName)) {
            DAOMark daoMark = new DAOMark();
            List<Mark> allMarks = daoMark.getAll();
            ID = allMarks.get(allMarks.size() - 1).getMark_id();
        } else if (NOTES.equals(tableName)) {
            DAONotes daoNotes = new DAONotes();
            List<Notes> allNotes = daoNotes.getAll();
            ID = allNotes.get(allNotes.size() - 1).getNote_id();
        } else if (PLAN.equals(tableName)) {
            DAOPlan daoPlan = new DAOPlan();
            List<Plan> allPlans = daoPlan.getAll();
            ID = allPlans.get(allPlans.size() - 1).getPlan_id();
        } else if (PRINCIPAL.equals(tableName)) {
            DAOPrincipal daoPrincipal = new DAOPrincipal();
            List<Principal> allPrincipals = daoPrincipal.getAll();
            ID = allPrincipals.get(allPrincipals.size() - 1).getPrincipal_id();
        } else if (SCHOOL.equals(tableName)) {
            DAOSchool daoSchool = new DAOSchool();
            List<School> allSchools = daoSchool.getAll();
            ID = allSchools.get(allSchools.size() - 1).getSchool_id();
        } else if (SCHOOL_SUBJECT.equals(tableName)) {
            DAOSchoolSubject daoSchoolSubject = new DAOSchoolSubject();
            List<SchoolSubject> allSchoolSubjects = daoSchoolSubject.getAll();
            ID = allSchoolSubjects.get(allSchoolSubjects.size() - 1).getSubject_id();
        } else if (STUDENT.equals(tableName)) {
            DAOStudent daoStudent = new DAOStudent();
            List<Student> allStudents = daoStudent.getAll();
            ID = allStudents.get(allStudents.size() - 1).getStudent_id();
        } else if (TEACHER.equals(tableName)) {
            DAOTeacher daoTeacher = new DAOTeacher();
            List<Teacher> allTeachers = daoTeacher.getAll();
            ID = allTeachers.get(allTeachers.size() - 1).getTeacher_id();
        } else if (USER.equals(tableName)) {
            DAOUser daoUser = new DAOUser();
            List<User> allUsers = daoUser.getAll();
            ID = allUsers.get(allUsers.size() - 1).getUser_id();
        }
        return ID;
    }
}
