package features;

import java.time.LocalDate;

public class CalculateDaysBack {
    public static int calculate(LocalDate date) {
        int daysBackToMonday = -1;
        switch (date.getDayOfWeek().toString()) {
            case "MONDAY" -> daysBackToMonday = 0;
            case "TUESDAY" -> daysBackToMonday = 1;
            case "WEDNESDAY" -> daysBackToMonday = 2;
            case "THURSDAY" -> daysBackToMonday = 3;
            case "FRIDAY" -> daysBackToMonday = 4;
            case "SATURDAY" -> daysBackToMonday = 5;
            case "SUNDAY" -> daysBackToMonday = 6;
        }
        return daysBackToMonday;
    }
}
