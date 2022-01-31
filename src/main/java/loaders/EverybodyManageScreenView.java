package loaders;

import DAO.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.*;
import variables.MainText;

import java.sql.SQLException;
import java.util.List;

public class EverybodyManageScreenView {
    public static void view(AnchorPane mainAnchor, ScrollPane scroll, AnchorPane scrollAnchor) throws SQLException {
        scroll.setVvalue(0);
        scrollAnchor.setPrefHeight(544);

        scrollAnchor.getChildren().clear();
        mainAnchor.getChildren().clear();

        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        mainAnchor.getChildren().add(MainText.main);

        MainText.main.setText("All school teachers");

        int YPosition = 20;

        createPartText(YPosition, "Principals", scrollAnchor);
        CreateLine.create(scrollAnchor, YPosition + 15);

        YPosition += 15;

        List<Principal> principals = new DAOPrincipal().getAllSorted();

        for (Principal principal : principals) {
            String principalNameAndSurname = principal.getPrincipal_name() + " " + principal.getPrincipal_surname();
            AnchorPane pane = createPersonPane(YPosition + 1, principalNameAndSurname);
            scrollAnchor.getChildren().add(pane);

            YPosition += 74;

            CreateLine.create(scrollAnchor, YPosition);
        }
        CreateLine.create(scrollAnchor, YPosition);

        YPosition += 45;
        createPartText(YPosition, "Teachers", scrollAnchor);
        CreateLine.create(scrollAnchor, YPosition + 15);

        YPosition += 15;

        List<Teacher> teachers = new DAOTeacher().getAllSorted();

        for (Teacher teacher: teachers) {
            String teacherNameAndSurname = teacher.getTeacher_name() + " " + teacher.getTeacher_surname();
            AnchorPane pane = createPersonPane(YPosition + 1, teacherNameAndSurname);
            scrollAnchor.getChildren().add(pane);

            YPosition += 74;

            CreateLine.create(scrollAnchor, YPosition);
        }
        CreateLine.create(scrollAnchor, YPosition);

        YPosition += 45;
        createPartText(YPosition, "Students", scrollAnchor);
        CreateLine.create(scrollAnchor, YPosition + 15);

        YPosition += 15;

        List<Student> students = new DAOStudent().getAllSorted();

        for (Student student: students) {
            String studentNameAndSurname = student.getStudent_name() + " " + student.getStudent_surname();
            AnchorPane pane = createPersonPane(YPosition + 1, studentNameAndSurname);
            scrollAnchor.getChildren().add(pane);

            YPosition += 74;

            CreateLine.create(scrollAnchor, YPosition);
        }
        CreateLine.create(scrollAnchor, YPosition);

        YPosition += 45;
        createPartText(YPosition, "Guardians", scrollAnchor);
        CreateLine.create(scrollAnchor, YPosition + 15);

        YPosition += 15;

        List<Guardian> guardians = new DAOGuardian().getALlSorted();

        for (Guardian guardian: guardians) {
            String guardianNameAndSurname = guardian.getGuardian_name() + " " + guardian.getGuardian_surname();
            AnchorPane pane = createPersonPane(YPosition + 1, guardianNameAndSurname);
            scrollAnchor.getChildren().add(pane);

            YPosition += 74;

            CreateLine.create(scrollAnchor, YPosition);
        }
        CreateLine.create(scrollAnchor, YPosition);

        YPosition += 45;
        createPartText(YPosition, "Admins", scrollAnchor);
        CreateLine.create(scrollAnchor, YPosition + 15);

        YPosition += 15;

        List<Admin> admins = new DAOAdmin().getAllSorted();

        for (Admin admin: admins) {
            String adminNameAndSurname = admin.getAdmin_name() + " " + admin.getAdmin_surname();
            AnchorPane pane = createPersonPane(YPosition + 1, adminNameAndSurname);
            scrollAnchor.getChildren().add(pane);

            YPosition += 74;

            CreateLine.create(scrollAnchor, YPosition);
        }

        if (YPosition >= 544) {
            scrollAnchor.setPrefHeight(YPosition + 124);
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
