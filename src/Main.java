import javafx.application.Application;
import javafx.stage.Stage;
import Systems.DatabaseSystem;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{}


    public static void main(String[] args) {
        DatabaseSystem db = new DatabaseSystem();
        launch(args);
    }
}
