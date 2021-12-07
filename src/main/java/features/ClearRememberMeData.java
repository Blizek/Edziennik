package features;

import locations.FilesLocations;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class ClearRememberMeData {
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
