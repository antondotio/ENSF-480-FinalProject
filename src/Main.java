import javafx.application.Application;
import javafx.stage.Stage;
import Systems.DatabaseSystem;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Controller/ProperTeaRentals.fxml"));

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        DatabaseSystem db = new DatabaseSystem();
        launch(args);
    }
}
