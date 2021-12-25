package features;

import locations.FilesLocations;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadRememberMeData {
    /** function to read RememberMe data **/
    public static List<String> readData() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(FilesLocations.JSON_REMEMBER_ME_FILE);

        Object obj = parser.parse(reader);

        JSONObject data = (JSONObject) obj;
        String email = (String) data.get("email");
        String password = (String) data.get("password");

        List<String> rememberMeData = new ArrayList<>();
        rememberMeData.add(email);
        rememberMeData.add(password);

        return rememberMeData;
    }
}
