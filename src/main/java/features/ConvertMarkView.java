package features;

public class ConvertMarkView {
    public static String convert(float mark) {
        String convertedMark;
        if (mark == 1.0) {
            convertedMark = "1";
        } else if (mark == 1.25) {
            convertedMark = "1+";
        } else if (mark == 1.75) {
            convertedMark = "2-";
        } else if (mark == 2.0) {
            convertedMark = "2";
        } else if (mark == 2.25) {
            convertedMark = "2+";
        } else if (mark == 2.75) {
            convertedMark = "3-";
        } else if (mark == 3.0) {
            convertedMark = "3";
        } else if (mark == 3.25) {
            convertedMark = "3+";
        } else if (mark == 3.75) {
            convertedMark = "4-";
        } else if (mark == 4.0) {
            convertedMark = "4";
        } else if (mark == 4.25) {
            convertedMark = "4+";
        } else if (mark == 4.75) {
            convertedMark = "5-";
        } else if (mark == 5.0) {
            convertedMark = "5";
        } else if (mark == 5.25) {
            convertedMark = "5+";
        } else if (mark == 5.75) {
            convertedMark = "6-";
        } else if (mark == 6.0) {
            convertedMark = "6";
        } else {
            convertedMark = Float.toString(mark);
        }
        return convertedMark;
    }
}
