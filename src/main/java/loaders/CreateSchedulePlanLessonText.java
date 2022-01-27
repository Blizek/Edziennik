package loaders;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CreateSchedulePlanLessonText {
    public static void createText(int XPosition, String textValue, AnchorPane paneToAdd) {
        Text text = new Text(XPosition, 42, textValue);
        text.setFont(Font.font("Calibri", 25));
        text.setFill(Color.rgb(60, 86, 188));
        paneToAdd.getChildren().add(text);
    }
}
