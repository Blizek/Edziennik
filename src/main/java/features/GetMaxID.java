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
            if (allAbsences.size() > 0) ID = allAbsences.get(allAbsences.size() - 1).getAbsence_id();
            else ID = 0;
        } else if (ADMIN.equals(tableName)) {
            DAOAdmin daoAdmin = new DAOAdmin();
            List<Admin> allAdmins = daoAdmin.getAll();
            if (allAdmins.size() > 0) ID = allAdmins.get(allAdmins.size() - 1).getAdmin_id();
            else ID = 0;
        } else if (CLASS.equals(tableName)) {
            DAOClass daoClass = new DAOClass();
            List<Class> allClasses = daoClass.getAll();
            if (allClasses.size() > 0) ID = allClasses.get(allClasses.size() - 1).getClass_id();
            else ID = 0;
        } else if (EMAIL.equals(tableName)) {
            DAOEmail daoEmail = new DAOEmail();
            List<Email> allEmails = daoEmail.getAll();
            if (allEmails.size() > 0) ID = allEmails.get(allEmails.size() - 1).getEmail_id();
            else ID = 0;
        } else if (EXAMS.equals(tableName)) {
            DAOExams daoExams = new DAOExams();
            List<Exams> allExams = daoExams.getAll();
            if (allExams.size() > 0) ID = allExams.get(allExams.size() - 1).getExam_id();
            else ID = 0;
        } else if (FINAL_GRADE.equals(tableName)) {
            DAOFinalGrade daoFinalGrade = new DAOFinalGrade();
            List<FinalGrade> allFinalGrades = daoFinalGrade.getAll();
            if (allFinalGrades.size() > 0) ID = allFinalGrades.get(allFinalGrades.size() - 1).getGrade_id();
            else ID = 0;
        } else if (GUARDIAN.equals(tableName)) {
            DAOGuardian daoGuardian = new DAOGuardian();
            List<Guardian> allGuardians = daoGuardian.getAll();
            if (allGuardians.size() > 0)ID = allGuardians.get(allGuardians.size() - 1).getGuardian_id();
            else ID = 0;
        } else if (LESSON.equals(tableName)) {
            DAOLesson daoLesson = new DAOLesson();
            List<Lesson> allLessons = daoLesson.getAll();
            if (allLessons.size() > 0) ID = allLessons.get(allLessons.size() - 1).getLesson_id();
            else ID = 0;
        } else if (MARK.equals(tableName)) {
            DAOMark daoMark = new DAOMark();
            List<Mark> allMarks = daoMark.getAll();
            if (allMarks.size() > 0) ID = allMarks.get(allMarks.size() - 1).getMark_id();
            else ID = 0;
        } else if (NOTES.equals(tableName)) {
            DAONotes daoNotes = new DAONotes();
            List<Notes> allNotes = daoNotes.getAll();
            if (allNotes.size() > 0) ID = allNotes.get(allNotes.size() - 1).getNote_id();
            else ID = 0;
        } else if (PLAN.equals(tableName)) {
            DAOPlan daoPlan = new DAOPlan();
            List<Plan> allPlans = daoPlan.getAll();
            if (allPlans.size() > 0) ID = allPlans.get(allPlans.size() - 1).getPlan_id();
            else ID = 0;
        } else if (PRINCIPAL.equals(tableName)) {
            DAOPrincipal daoPrincipal = new DAOPrincipal();
            List<Principal> allPrincipals = daoPrincipal.getAll();
            if (allPrincipals.size() > 0) ID = allPrincipals.get(allPrincipals.size() - 1).getPrincipal_id();
            else ID = 0;
        } else if (SCHOOL.equals(tableName)) {
            DAOSchool daoSchool = new DAOSchool();
            List<School> allSchools = daoSchool.getAll();
            if (allSchools.size() > 0) ID = allSchools.get(allSchools.size() - 1).getSchool_id();
            else ID = 0;
        } else if (SCHOOL_SUBJECT.equals(tableName)) {
            DAOSchoolSubject daoSchoolSubject = new DAOSchoolSubject();
            List<SchoolSubject> allSchoolSubjects = daoSchoolSubject.getAll();
            if (allSchoolSubjects.size() > 0) ID = allSchoolSubjects.get(allSchoolSubjects.size() - 1).getSubject_id();
            else ID = 0;
        } else if (STUDENT.equals(tableName)) {
            DAOStudent daoStudent = new DAOStudent();
            List<Student> allStudents = daoStudent.getAll();
            if (allStudents.size() > 0) ID = allStudents.get(allStudents.size() - 1).getStudent_id();
            else ID = 0;
        } else if (TEACHER.equals(tableName)) {
            DAOTeacher daoTeacher = new DAOTeacher();
            List<Teacher> allTeachers = daoTeacher.getAll();
            if (allTeachers.size() > 0) ID = allTeachers.get(allTeachers.size() - 1).getTeacher_id();
            else ID = 0;
        } else if (USER.equals(tableName)) {
            DAOUser daoUser = new DAOUser();
            List<User> allUsers = daoUser.getAll();
            if (allUsers.size() > 0) ID = allUsers.get(allUsers.size() - 1).getUser_id();
            else ID = 0;
        }
        return ID;
    }
}
