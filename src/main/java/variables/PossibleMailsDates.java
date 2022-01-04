package variables;

import java.util.ArrayList;

public class PossibleMailsDates {
    public static ArrayList<String> possibleMailsDate = new ArrayList<>();

    public static void setPossibleMailsDate(ArrayList<String> possibleMailsDate) {
        possibleMailsDate.add("Today");
        possibleMailsDate.add("This week");
        possibleMailsDate.add("Last week");
        possibleMailsDate.add("Two weeks ago");
        possibleMailsDate.add("Older");
    }
}
