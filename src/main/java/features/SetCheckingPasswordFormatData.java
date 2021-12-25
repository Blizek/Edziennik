package features;

import locations.FilesLocations;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class SetCheckingPasswordFormatData {
    public static void setData(boolean status) {
        JSONObject checkingData = new JSONObject();
        checkingData.put("checking", status);

        try (FileWriter file = new FileWriter(FilesLocations.JSON_CHECKING_PASSWORD_FORMAT_DATA)) {
            file.write(checkingData.toJSONString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
