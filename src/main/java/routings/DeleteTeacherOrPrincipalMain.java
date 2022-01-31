package routings;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import locations.FilesLocations;

import java.io.IOException;

public class DeleteTeacherOrPrincipalMain {
    public static Stage stage = new Stage();

    public void runThis() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/fxml/DeleteTeacherOrPrincipalScreen.fxml"));

        AnchorPane anchorPane = loader.load();

        Scene scene =new Scene(anchorPane);
        stage.setScene(scene);
        stage.setTitle("Delete teacher/principal");
        stage.setResizable(false);
        stage.getIcons().add(new Image(FilesLocations.LOGO));
        stage.setAlwaysOnTop(true);

        stage.show();
    }

    public void closeStageByDeleting() {
        stage.close();
    }
}
