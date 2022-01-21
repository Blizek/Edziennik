package loaders;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class CreateLine {
    public static void create(AnchorPane paneToAdd, int YPosition) {
        Line line = new Line();
        line.setLayoutX(135);
        line.setLayoutY(YPosition);
        line.setStartX(-100);
        line.setStartY(0);
        line.setEndX(817);
        line.setEndY(0);
        line.setStroke(Color.rgb(154, 154, 154));
        line.setStrokeWidth(2);
        paneToAdd.getChildren().add(line);
    }
}
