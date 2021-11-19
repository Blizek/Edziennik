import DAO.DAOAdmin;
import database.DBConnection;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Admin;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        DBConnection.getConnection();
        DAOAdmin test = new DAOAdmin();
        test.save(new Admin(0, 0, "Test", "test"));
    }
}
