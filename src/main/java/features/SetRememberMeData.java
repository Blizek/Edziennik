package features;

import locations.FilesLocations;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class SetRememberMeData {
    /** function to set RememberMe data if user has selected button to JSON file **/
    public static void setData(String email, String password) {
        JSONObject rememberData = new JSONObject();
        rememberData.put("email", email);
        rememberData.put("password", password);

        try (FileWriter file = new FileWriter(FilesLocations.JSON_REMEMBER_ME_FILE)) {
            file.write(rememberData.toJSONString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
