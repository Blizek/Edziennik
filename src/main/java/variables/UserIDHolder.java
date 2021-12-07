package variables;

public class UserIDHolder {
    private static int userID;

    public static int getUserID() {
        return userID;
    }

    public static void setUserID(int userID) {
        UserIDHolder.userID = userID;
    }
}
