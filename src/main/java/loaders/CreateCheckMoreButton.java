package loaders;

import com.jfoenix.controls.JFXButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class CreateCheckMoreButton {
    public static JFXButton createButton(int YPosition) {
        JFXButton checkMore = new JFXButton();

        checkMore.setLayoutX(74);
        checkMore.setLayoutY(YPosition + 15);
        checkMore.setMinWidth(331);

        checkMore.setText("Check more");
        checkMore.setFont(Font.font("Calibri", FontWeight.BOLD, 25));
        checkMore.setTextFill(Color.rgb(255, 255, 255));
        checkMore.setTextAlignment(TextAlignment.CENTER);

        return checkMore;
    }
}
