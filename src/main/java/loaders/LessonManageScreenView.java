package loaders;

import DAO.DAOClass;
import DAO.DAOPlan;
import DAO.DAOSchoolSubject;
import DAO.DAOTeacher;
import com.jfoenix.controls.JFXButton;
import features.FormatDay;
import features.GetStudent;
import features.GetUser;
import features.GetWeekDay;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import locations.FilesLocations;
import model.Plan;
import model.Student;
import model.Teacher;
import model.User;
import variables.MainText;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class LessonManageScreenView {
    static LocalDate date;

    public static void view(AnchorPane mainAnchor, ScrollPane scroll, AnchorPane scrollAnchor, LocalDate actualDate) throws SQLException {
        scroll.setVvalue(0);
        scrollAnchor.setPrefHeight(544);

        scrollAnchor.getChildren().clear();
        mainAnchor.getChildren().clear();

        mainAnchor.getChildren().add(MainText.main);
        date = actualDate;

        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);


        viewLoader(mainAnchor, scroll, scrollAnchor);
    }

    public static void viewLoader(AnchorPane mainAnchor, ScrollPane scroll, AnchorPane scrollAnchor) throws SQLException {
        scrollAnchor.getChildren().clear();

        Student student;
        Teacher teacher;
        User user = GetUser.get();

        String planOwnerNameAndSurname;
        List<Plan> thisDayLessons;

        if (!user.getUser_role().equals("TEACHER")) {
            if (user.getUser_role().equals("GUARDIAN")) student = GetStudent.getForGuardian(user.getUser_id());
            else student = GetStudent.getForStudent(user.getUser_id());
            planOwnerNameAndSurname = student.getStudent_name() + " " + student.getStudent_surname();
            thisDayLessons = new DAOPlan().getStudentsPlanLessonsFromWeekDay(student.getStudent_id(), GetWeekDay.get(date));
        } else {
            teacher = new DAOTeacher().getByUserID(user.getUser_id()).get(0);
            planOwnerNameAndSurname = teacher.getTeacher_name() + " " + teacher.getTeacher_surname();
            thisDayLessons = new DAOPlan().getTeachersPlanLessonsFromWeekDay(teacher.getTeacher_id(), GetWeekDay.get(date));
        }

        JFXButton refreshButton = CreateRightCornerButton.create(38, "Refresh");
        EventHandler<MouseEvent> refresh = e -> {
            try {
                mainAnchor.getChildren().remove(refreshButton);
                viewLoader(mainAnchor, scroll, scrollAnchor);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        };
        refreshButton.addEventHandler(MouseEvent.MOUSE_CLICKED, refresh);
        mainAnchor.getChildren().add(refreshButton);

        MainText.main.setText(planOwnerNameAndSurname + "'s lesson plan");

        String dayAndDate = GetWeekDay.get(date) + " " + FormatDay.formatDate(date.toString());

        Text dayAndDateText = new Text(313, 40, dayAndDate);
        dayAndDateText.setFont(Font.font("Calibri", FontWeight.BOLD, 35));
        dayAndDateText.setFill(Color.rgb(60, 86, 188));
        dayAndDateText.setWrappingWidth(359);
        dayAndDateText.setTextAlignment(TextAlignment.CENTER);
        scrollAnchor.getChildren().add(dayAndDateText);

        ImageView leftArrow = CreateArrows.create(257, FilesLocations.LEFT_ARROW_ICON);
        EventHandler<MouseEvent> dayMinusOne = e -> {
            date = date.minusDays(1);
            try {
                mainAnchor.getChildren().remove(refreshButton);
                viewLoader(mainAnchor, scroll, scrollAnchor);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        };
        leftArrow.addEventHandler(MouseEvent.MOUSE_CLICKED, dayMinusOne);
        scrollAnchor.getChildren().add(leftArrow);

        ImageView rightArrow = CreateArrows.create(678, FilesLocations.RIGHT_ARROW_ICON);
        EventHandler<MouseEvent> dayPlusOne = e -> {
            date = date.plusDays(1);
            try {
                mainAnchor.getChildren().remove(refreshButton);
                viewLoader(mainAnchor, scroll, scrollAnchor);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        };
        rightArrow.addEventHandler(MouseEvent.MOUSE_CLICKED, dayPlusOne);
        scrollAnchor.getChildren().add(rightArrow);

        int YPosition = 78;

        for (int i = 0; i < thisDayLessons.size(); i++) {
            CreateLine.create(scrollAnchor, YPosition);

            AnchorPane lessonPane = new AnchorPane();
            lessonPane.setLayoutX(33);
            lessonPane.setLayoutY(YPosition);
            lessonPane.setPrefSize(919, 74);
            lessonPane.setId(Integer.toString(thisDayLessons.get(i).getPlan_id()));

            CreateSchedulePlanLessonText.createText(22, Integer.toString(i + 1), lessonPane);

            String subjectName = new DAOSchoolSubject().getTeacherSubject(thisDayLessons.get(i).getTeacher_id()).get(0).getSubject_name();
            String className = new DAOClass().get(thisDayLessons.get(i).getClass_id()).get(0).getClass_name();
            String lessonTime = thisDayLessons.get(i).getStart_hour() + " - " + thisDayLessons.get(i).getFinish_hour();
            int classroomNumber = thisDayLessons.get(i).getClassroom_number();

            String lessonInformation = subjectName + ", " + className + ", " + lessonTime + ", " + classroomNumber;

            CreateSchedulePlanLessonText.createText(94, lessonInformation, lessonPane);
            CreateSchedulePlanLessonText.createText(720, "See more >", lessonPane);

            EventHandler<MouseEvent> paneClicked = e -> {
                mainAnchor.getChildren().remove(refreshButton);
                try {
                    LessonDetailsManageScreenView.load(mainAnchor, scroll, scrollAnchor, lessonPane.getId());
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            };
            lessonPane.addEventHandler(MouseEvent.MOUSE_CLICKED, paneClicked);

            scrollAnchor.getChildren().add(lessonPane);

            YPosition += 74;
        }

        CreateLine.create(scrollAnchor, YPosition);

        if (YPosition >= 544) {
            scrollAnchor.setPrefHeight(YPosition + 124);
            scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        }
    }
}
