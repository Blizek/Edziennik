package routings;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import locations.FilesLocations;

import java.io.IOException;

public class DeleteNoteMain {
    public static Stage stage = new Stage();

    public void runThis() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/fxml/DeleteNoteScreen.fxml"));

        AnchorPane anchorPane = loader.load();

        Scene scene =new Scene(anchorPane);
        stage.setScene(scene);
        stage.setTitle("Delete note");
        stage.setResizable(false);
        stage.getIcons().add(new Image(FilesLocations.LOGO));
        stage.setAlwaysOnTop(true);

        stage.show();
    }

    public void closeStageByDeletingNote() {
        stage.close();
    }
}
