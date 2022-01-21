package controller;

import DAO.DAOMark;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import features.ConvertMarkView;
import features.DecodeMarkValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.Mark;
import routings.ManageGradeMain;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManageGradeController {
    @FXML
    TextField markField, descriptionField, weightField;

    @FXML
    JFXCheckBox normalGradeCheckBox, customGradeCheckBox;

    @FXML
    Text doesntExistMarkErrorText, notNumberErrorText, tooLongDescriptionError, weightNumberError;

    @FXML
    JFXButton addOrEditButton;

    public static Mark mark;
    public static boolean editing;

    boolean someError = false;

    ArrayList<String> possibleGrades = new ArrayList<>();

    public void setPossibleGrades(ArrayList<String> possibleGrades) {
        possibleGrades.add("1");
        possibleGrades.add("1+");
        possibleGrades.add("2-");
        possibleGrades.add("2");
        possibleGrades.add("2+");
        possibleGrades.add("3-");
        possibleGrades.add("3");
        possibleGrades.add("3+");
        possibleGrades.add("4-");
        possibleGrades.add("4");
        possibleGrades.add("4+");
        possibleGrades.add("5-");
        possibleGrades.add("5");
        possibleGrades.add("5+");
        possibleGrades.add("6-");
        possibleGrades.add("6");
    }

    public void initialize() {
        if (editing) {
            markField.setText(ConvertMarkView.convert(mark.getMark_value()));
            descriptionField.setText(mark.getMark_description());
            weightField.setText(Integer.toString(mark.getMark_weight()));
            addOrEditButton.setText("Edit");
        }

        normalGradeCheckBox.setSelected(true);
        customGradeCheckBox.setSelected(false);
    }

    public void setNormalMarkStatus() {
        if (normalGradeCheckBox.isSelected()) {
            normalGradeCheckBox.setSelected(true);
            customGradeCheckBox.setSelected(false);
        } else {
            normalGradeCheckBox.setSelected(false);
            customGradeCheckBox.setSelected(true);
        }
    }

    public void setCustomMarkStatus() {
        if (customGradeCheckBox.isSelected()) {
            customGradeCheckBox.setSelected(true);
            normalGradeCheckBox.setSelected(false);
        } else {
            customGradeCheckBox.setSelected(false);
            normalGradeCheckBox.setSelected(true);
        }
    }

    public void addOrEditGrade() throws SQLException {
        someError = false;
        if (normalGradeCheckBox.isSelected()) {
            checkIfItIsGoodGrade();
        } else {
            doesntExistMarkErrorText.setVisible(false);
            checkIfItIsPositiveNumber(markField, notNumberErrorText);
        }

        if (descriptionField.getText().length() > 50) {
            tooLongDescriptionError.setVisible(true);
            someError = true;
        } else tooLongDescriptionError.setVisible(false);


        checkIfItIsPositiveNumber(weightField, weightNumberError);


        if (!someError) {
            if (normalGradeCheckBox.isSelected()) mark.setMark_value(DecodeMarkValue.decode(markField.getText()));
            else mark.setMark_value(Float.parseFloat(markField.getText()));
            mark.setMark_description(descriptionField.getText());
            mark.setMark_weight(Integer.parseInt(weightField.getText()));

            if (!editing) new DAOMark().save(mark);
            else new DAOMark().update(mark);

            new ManageGradeMain().closeStageByAddingGrade();
        }
    }

    private void checkIfItIsGoodGrade() {
        String grade = markField.getText();
        setPossibleGrades(possibleGrades);
        notNumberErrorText.setVisible(false);

        if (!possibleGrades.contains(grade)) {
            doesntExistMarkErrorText.setVisible(true);
            someError = true;
        } else doesntExistMarkErrorText.setVisible(false);
    }

    private void checkIfItIsPositiveNumber(TextField field, Text error) {
        String number = field.getText();

        try {
            int intNumber = Integer.parseInt(number);
            if (intNumber >= 0) error.setVisible(false);
            else {
                error.setVisible(true);
                someError = true;
            }
        } catch (NumberFormatException e) {
            error.setVisible(true);
            someError = true;
        }
    }
}
