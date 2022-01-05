package features;

import model.Email;

import java.util.ArrayList;
import java.util.List;

public class GetAllMailForUserInPeriod {
    public static List<Email> get(List<Email> periodEmails, int userID, boolean isReceived) {
        List<Email> emailsForUserInPeriod = new ArrayList<>();
        for (int i = 0; i < periodEmails.size(); i++) {
            if (periodEmails.get(i).getRecipient_user_id() == userID && isReceived) {
                emailsForUserInPeriod.add(periodEmails.get(i));
            } else if (periodEmails.get(i).getSender_user_id() == userID && !isReceived) {
                emailsForUserInPeriod.add(periodEmails.get(i));
            }
        }
        return emailsForUserInPeriod;
    }
}
