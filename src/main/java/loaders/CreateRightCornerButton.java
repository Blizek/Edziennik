package loaders;

import com.jfoenix.controls.JFXButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CreateRightCornerButton {
    public static JFXButton create(int YPosition, String buttonText) {
        JFXButton button = new JFXButton();
        button.setLayoutX(771);
        button.setLayoutY(YPosition);
        button.setPrefSize(200, 37);
        button.setText(buttonText);
        button.setFont(Font.font("Calibri", 20));
        button.setTextFill(Color.rgb(255, 255, 255));
        button.setStyle("-fx-background-color: #3c56bc; -fx-background-radius: 10; -fx-border-radius: 10;");

        return button;
    }
}
