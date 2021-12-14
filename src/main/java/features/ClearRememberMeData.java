package features;

import locations.FilesLocations;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class ClearRememberMeData {
    /** function which clean RememberMe data after logging out **/
    public void clearData(){
        JSONObject rememberMeData = new JSONObject();
        rememberMeData.put("email", "");
        rememberMeData.put("password", "");

        try (FileWriter file = new FileWriter(FilesLocations.jsonRememberMeFile)) {
            file.write(rememberMeData.toJSONString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
