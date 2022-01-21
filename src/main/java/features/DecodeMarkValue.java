package features;

public class DecodeMarkValue {
    public static float decode(String mark) {
        float markValue;
        mark += ";";

        markValue = Float.parseFloat(String.valueOf(mark.charAt(0)));

        if (mark.charAt(1) == '+') markValue += 0.25;
        else if (mark.charAt(1) == '-') markValue -= 0.25;
        else markValue += 0.0;

        return markValue;
    }
}
