package controller;

import DAO.DAOFinalGrade;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import locations.FilesLocations;
import model.FinalGrade;
import routings.SetFinalGradeMain;

import java.sql.SQLException;
import java.util.ArrayList;

public class SetFinalGradeController {
    @FXML
    ImageView one, two, three, four, five, six;

    @FXML
    Text averageText, finalGradeText, error;

    public static FinalGrade grade;
    public static double average;
    public static boolean editing;

    int actualGrade;

    ArrayList<ImageView> images = new ArrayList<>();

    public void setImages(ArrayList<ImageView> images) {
        images.add(one);
        images.add(two);
        images.add(three);
        images.add(four);
        images.add(five);
        images.add(six);
    }

    public void initialize() {
        setImages(images);
        averageText.setText("Average grade: " + average);
        if (grade.getGrade_value() == 0) {
            actualGrade = 0;
            finalGradeText.setText("Final grade: None");
        } else {
            setInitialize(grade.getGrade_value());
        }
    }

    public void setGrade() throws SQLException {
        if (actualGrade == 0) {
            error.setVisible(true);
        } else {
            error.setVisible(false);
            grade.setGrade_value(actualGrade);
            if (!editing) {
                new DAOFinalGrade().update(grade);
            } else {
                new DAOFinalGrade().save(grade);
            }
            new SetFinalGradeMain().closeStageBySettingFinalGrade();
        }
    }

    public void setOne() {
        if (actualGrade == 0) {
            one.setImage(new Image(FilesLocations.ONE_MARK_GREY));
            finalGradeText.setText("Final grade: 1");
            actualGrade = 1;
        } else if (actualGrade != 1) {
            setDefaultIcon();
            one.setImage(new Image(FilesLocations.ONE_MARK_GREY));
            finalGradeText.setText("Final grade: 1");
            actualGrade = 1;
        } else {
            one.setImage(new Image(FilesLocations.ONE_MARK));
            finalGradeText.setText("Final grade: None");
            actualGrade = 0;
        }
    }

    public void setTwo() {
        if (actualGrade == 0) {
            two.setImage(new Image(FilesLocations.TWO_MARK_GREY));
            finalGradeText.setText("Final grade: 2");
            actualGrade = 2;
        } else if (actualGrade != 2) {
            setDefaultIcon();
            two.setImage(new Image(FilesLocations.TWO_MARK_GREY));
            finalGradeText.setText("Final grade: 2");
            actualGrade = 2;
        } else {
            two.setImage(new Image(FilesLocations.TWO_MARK));
            finalGradeText.setText("Final grade: None");
            actualGrade = 0;
        }
    }

    public void setThree() {
        if (actualGrade == 0) {
            three.setImage(new Image(FilesLocations.THREE_MARK_GREY));
            finalGradeText.setText("Final grade: 3");
            actualGrade = 3;
        } else if (actualGrade != 3) {
            setDefaultIcon();
            three.setImage(new Image(FilesLocations.THREE_MARK_GREY));
            finalGradeText.setText("Final grade: 3");
            actualGrade = 3;
        } else {
            three.setImage(new Image(FilesLocations.THREE_MARK));
            finalGradeText.setText("Final grade: None");
            actualGrade = 0;
        }
    }

    public void setFour() {
        if (actualGrade == 0) {
            four.setImage(new Image(FilesLocations.FOUR_MARK_GREY));
            finalGradeText.setText("Final grade: 4");
            actualGrade = 4;
        } else if (actualGrade != 4) {
            setDefaultIcon();
            four.setImage(new Image(FilesLocations.FOUR_MARK_GREY));
            finalGradeText.setText("Final grade: 4");
            actualGrade = 4;
        } else {
            four.setImage(new Image(FilesLocations.FOUR_MARK));
            finalGradeText.setText("Final grade: None");
            actualGrade = 0;
        }
    }

    public void setFive() {
        if (actualGrade == 0) {
            five.setImage(new Image(FilesLocations.FIVE_MARK_GREY));
            finalGradeText.setText("Final grade: 5");
            actualGrade = 5;
        } else if (actualGrade != 5) {
            setDefaultIcon();
            five.setImage(new Image(FilesLocations.FIVE_MARK_GREY));
            finalGradeText.setText("Final grade: 5");
            actualGrade = 5;
        } else {
            five.setImage(new Image(FilesLocations.FIVE_MARK));
            finalGradeText.setText("Final grade: None");
            actualGrade = 0;
        }
    }

    public void setSix() {
        if (actualGrade == 0) {
            six.setImage(new Image(FilesLocations.SIX_MARK_GREY));
            finalGradeText.setText("Final grade: 6");
            actualGrade = 6;
        } else if (actualGrade != 6) {
            setDefaultIcon();
            six.setImage(new Image(FilesLocations.SIX_MARK_GREY));
            finalGradeText.setText("Final grade: 6");
            actualGrade = 6;
        } else {
            six.setImage(new Image(FilesLocations.SIX_MARK));
            finalGradeText.setText("Final grade: None");
            actualGrade = 0;
        }
    }

    private void setInitialize(int grade) {
        switch (grade) {
            case 1 -> setOne();
            case 2 -> setTwo();
            case 3 -> setThree();
            case 4 -> setFour();
            case 5 -> setFive();
            case 6 -> setSix();
        }
    }

    private void setDefaultIcon() {
        one.setImage(new Image(FilesLocations.ONE_MARK));
        two.setImage(new Image(FilesLocations.TWO_MARK));
        three.setImage(new Image(FilesLocations.THREE_MARK));
        four.setImage(new Image(FilesLocations.FOUR_MARK));
        five.setImage(new Image(FilesLocations.FIVE_MARK));
        six.setImage(new Image(FilesLocations.SIX_MARK));
    }
}
