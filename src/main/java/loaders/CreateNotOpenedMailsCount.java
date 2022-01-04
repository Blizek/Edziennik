package loaders;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class CreateNotOpenedMailsCount {
    public static void createNotOpenedMailsCount(int count, AnchorPane paneToAddCircle) {
        Circle informationCircle = new Circle();

        informationCircle.setFill(Color.rgb(255, 179, 0));
        informationCircle.setRadius(33);
        informationCircle.setLayoutX(530);
        informationCircle.setLayoutY(46);
        informationCircle.setStroke(Color.rgb(0, 0, 0));
        informationCircle.setStrokeWidth(3);

        Text countText = new Text(505, 53, "");
        countText.setWrappingWidth(50.2);
        countText.setTextAlignment(TextAlignment.CENTER);
        countText.setFont(Font.font("Calibri", FontWeight.BOLD, 30));
        if (count > 9) countText.setText("9+");
        else countText.setText(Integer.toString(count));

        paneToAddCircle.getChildren().add(informationCircle);
        paneToAddCircle.getChildren().add(countText);
    }
}
