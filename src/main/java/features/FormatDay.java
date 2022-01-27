package features;


public class FormatDay {
    public static String formatDate(String date) {
        String year = date.substring(0, 4);
        String month = date.substring(5, 7);
        String day = date.substring(8, 10);

        return day + "." + month + "." + year;
    }

    public static String formatDateAndHour(String date) {
        String formedDate = formatDate(date);

        return formedDate + date.substring(10, 19);
    }
}
