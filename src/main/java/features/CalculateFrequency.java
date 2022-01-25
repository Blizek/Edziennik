package features;

import model.Absences;

import java.util.List;

public class CalculateFrequency {
    public static double calculate(List<Absences> absences) {
        double meter = 0.0d;

        for (Absences absence: absences) {
            if (!absence.isStudent_absence()) {
                ++meter;
            }
        }

        if (absences.size() == 0) return 0.0d;
        else return Math.floor(meter / absences.size() * 100);
    }
}
