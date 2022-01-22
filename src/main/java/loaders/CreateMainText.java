package loaders;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class CreateMainText {
    public static Text create(String textValue) {
        Text pageInfo = new Text(34, 44, textValue);
        pageInfo.setFont(Font.font("Calibri", FontWeight.BOLD, 25));
        pageInfo.setFill(Color.rgb(60, 86, 188));
        pageInfo.setWrappingWidth(930);
        pageInfo.setTextAlignment(TextAlignment.LEFT);

        return pageInfo;
    }
}
