package features;

public class DecodeID {
    public static int decode(String paneID) {
        StringBuilder stringID = new StringBuilder();
        for (int i = 0; i < paneID.length(); i++) {
            if (paneID.charAt(i) == '0') stringID.append('0');
            else if (paneID.charAt(i) == '1') stringID.append('1');
            else if (paneID.charAt(i) == '2') stringID.append('2');
            else if (paneID.charAt(i) == '3') stringID.append('3');
            else if (paneID.charAt(i) == '4') stringID.append('4');
            else if (paneID.charAt(i) == '5') stringID.append('5');
            else if (paneID.charAt(i) == '6') stringID.append('6');
            else if (paneID.charAt(i) == '7') stringID.append('7');
            else if (paneID.charAt(i) == '8') stringID.append('8');
            else if (paneID.charAt(i) == '9') stringID.append('9');
            else break;
        }

        return Integer.parseInt(String.valueOf(stringID));
    }
}
