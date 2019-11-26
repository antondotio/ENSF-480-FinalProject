

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.fxml.FXMLLoader;
import Controller.Client;

import static Controller.Listener.getListener;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Client client = new Client("localhost", 5050);
        getListener().setClient(client);
        Parent root = FXMLLoader.load(getClass().getResource("Views/ProperTeaRentals.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
