package features;

import DAO.DAOClass;
import model.Class;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetNotDuplicatedLearningClasses {
    public static List<Class> get(int teacherID) throws SQLException {
        List<Class> everyPlanClass = new DAOClass().getAllTeacherLearningClasses(teacherID);

        ArrayList<Integer> classesID = new ArrayList<>();
        ArrayList<Class> notDuplicatedClasses = new ArrayList<>();

        for (Class actualClass: everyPlanClass) {
            if (!classesID.contains(actualClass.getClass_id())) {
                classesID.add(actualClass.getClass_id());
                notDuplicatedClasses.add(actualClass);
            }
        }

        return notDuplicatedClasses;
    }
}
