package loaders;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CreateArrows {
    public static ImageView create(double XPosition, String iconURL) {
        ImageView arrow = new ImageView();
        arrow.setLayoutX(XPosition);
        arrow.setLayoutY(6);
        arrow.setFitWidth(50);
        arrow.setFitHeight(50);
        arrow.setImage(new Image(iconURL));

        return arrow;
    }
}
