package features;

import locations.FilesLocations;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SetRememberMeData {
    public void setData(String email, String password) {
        JSONObject rememberData = new JSONObject();
        rememberData.put("email", email);
        rememberData.put("password", password);

        try (FileWriter file = new FileWriter(FilesLocations.jsonRememberMeFile)) {
            file.write(rememberData.toJSONString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
