package features;

import locations.FilesLocations;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadCheckingPasswordFormatData {
    public static boolean readData() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(FilesLocations.JSON_CHECKING_PASSWORD_FORMAT_DATA);

        Object obj = parser.parse(reader);

        JSONObject data = (JSONObject) obj;
        boolean status = (boolean) data.get("checking");

        return status;
    }
}
