package loaders;

import DAO.DAOClass;
import DAO.DAOStudent;
import DAO.DAOTeacher;
import com.jfoenix.controls.JFXButton;
import features.GetNameAndSurnameByTableID;
import features.GetNotDuplicatedTeachers;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Class;
import model.Student;
import model.Teacher;
import variables.MainText;
import variables.SupervisingTeacherID;

import java.sql.SQLException;
import java.util.List;

public class ClassManageScreenView {
    public static void view(AnchorPane mainAnchor, ScrollPane scroll, AnchorPane scrollAnchor, int classID) throws SQLException {
        scroll.setVvalue(0);
        scrollAnchor.setPrefHeight(544);

        scrollAnchor.getChildren().clear();

        Class actualClass = new DAOClass().get(classID).get(0);

        MainText.main.setText(actualClass.getClass_name());

        JFXButton getBackButton = CreateRightCornerButton.create(38, "Go back to classes");
        EventHandler<MouseEvent> getBack = e -> {
            try {
                AllClassesManageScreenView.view(mainAnchor, scroll, scrollAnchor);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        };
        getBackButton.addEventHandler(MouseEvent.MOUSE_CLICKED, getBack);
        mainAnchor.getChildren().add(getBackButton);

        JFXButton scheduleClassPlanButton = CreateRightCornerButton.create(20, "Schedule plan");
        EventHandler<MouseEvent> scheduleClassPlan = e -> {
            try {
                SetClassSchedulePlanScreenView.day = "MONDAY";
                SetClassSchedulePlanScreenView.view(mainAnchor, scroll, scrollAnchor, classID);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        };
        scheduleClassPlanButton.addEventHandler(MouseEvent.MOUSE_CLICKED, scheduleClassPlan);
        scrollAnchor.getChildren().add(scheduleClassPlanButton);

        JFXButton editClassButton = CreateRightCornerButton.create(65, "Edit class");
        EventHandler<MouseEvent> editClass = e -> {
            try {
                SupervisingTeacherID.setID(-1);
                ManageClassScreenView.view(mainAnchor, scroll, scrollAnchor, classID);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        };
        editClassButton.addEventHandler(MouseEvent.MOUSE_CLICKED, editClass);
        scrollAnchor.getChildren().add(editClassButton);

        createPartText(110, "Supervising teacher", scrollAnchor);
        CreateLine.create(scrollAnchor, 130);

        Teacher supervisingTeacher = new DAOTeacher().get(actualClass.getClass_supervising_teacher()).get(0);
        String supervisingTeacherNameAndSurname = GetNameAndSurnameByTableID.getTeacher(supervisingTeacher.getTeacher_id());
        AnchorPane supervisingTeacherPane = createPersonPane(131, supervisingTeacherNameAndSurname);
        scrollAnchor.getChildren().add(supervisingTeacherPane);

        CreateLine.create(scrollAnchor, 204);

        createPartText(249, "Students", scrollAnchor);
        CreateLine.create(scrollAnchor, 269);

        int YPosition = 270;
        List<Student> allClassStudents = new DAOStudent().getByClass(classID);

        for (Student allClassStudent : allClassStudents) {
            String nameAndSurname = GetNameAndSurnameByTableID.getStudent(allClassStudent.getStudent_id());
            AnchorPane studentPane = createPersonPane(YPosition + 1, nameAndSurname);
            scrollAnchor.getChildren().add(studentPane);

            YPosition += 74;

            CreateLine.create(scrollAnchor, YPosition);
        }

        YPosition += 45;
        createPartText(YPosition, "Teachers", scrollAnchor);
        CreateLine.create(scrollAnchor, YPosition + 20);

        List<Teacher> allLearningClassTeachers = GetNotDuplicatedTeachers.get(classID);

        YPosition += 20;

        for (Teacher allLearningClassTeacher : allLearningClassTeachers) {
            String nameAndSurname = GetNameAndSurnameByTableID.getTeacher(allLearningClassTeacher.getTeacher_id());
            AnchorPane teacherPane = createPersonPane(YPosition + 1, nameAndSurname);
            scrollAnchor.getChildren().add(teacherPane);

            YPosition += 74;

            CreateLine.create(scrollAnchor, YPosition);
        }

        if (YPosition >= 544) {
            scrollAnchor.setPrefHeight(YPosition + 250);
            scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        }
    }

    private static void createPartText(int YPosition, String textValue, AnchorPane paneToAdd) {
        Text partText = new Text(74, YPosition, textValue);
        partText.setFont(Font.font("Calibri", 25));
        partText.setFill(Color.rgb(60, 86, 188));
        paneToAdd.getChildren().add(partText);
    }

    private static AnchorPane createPersonPane(int YPosition, String nameAndSurname) {
        AnchorPane personPane = new AnchorPane();
        personPane.setLayoutX(33);
        personPane.setLayoutY(YPosition);
        personPane.setPrefSize(919, 74);

        CreateSchedulePlanLessonText.createText(22, nameAndSurname, personPane);

        return personPane;
    }
}
