package features;

import model.Mark;

import java.util.List;

public class AverageGradeCalculator {
    public static double calculate(List<Mark> marks) {
        double meter = 0.0;
        double denominator = 0.0;

        for (Mark mark: marks) {
            meter += mark.getMark_value() * mark.getMark_weight();
            denominator += mark.getMark_weight();
        }

        if (denominator == 0.0) return 0.0;
        else return Math.round((meter / denominator) * 100.0) / 100.0;
    }
}
