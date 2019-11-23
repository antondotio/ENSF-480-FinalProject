package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;


public class LandlordViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ToggleGroup furnished;

    @FXML
    private Button logoutButton;


    @FXML
    void logout(ActionEvent event) {
    }

    @FXML
    void initialize() {
        assert furnished != null : "fx:id=\"furnished\" was not injected: check your FXML file 'LandlordView.fxml'.";
        assert logoutButton != null : "fx:id=\"logoutButton\" was not injected: check your FXML file 'LandlordView.fxml'.";


    }

}
