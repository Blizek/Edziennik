package features;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class GetWeekDay {
    public static String get(LocalDate date) {
        DayOfWeek weekDay = date.getDayOfWeek();
        int weekDayNumber = weekDay.getValue();

        String dayName = "";
        switch (weekDayNumber) {
            case 1 -> dayName = "MONDAY";
            case 2 -> dayName = "TUESDAY";
            case 3 -> dayName = "WEDNESDAY";
            case 4 -> dayName = "THURSDAY";
            case 5 -> dayName = "FRIDAY";
            case 6 -> dayName = "SATURDAY";
            case 7 -> dayName = "SUNDAY";
        }

        return dayName;
    }
}
