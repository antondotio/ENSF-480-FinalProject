package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ProperTeaRentalsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordForm;

    @FXML
    private TextField usernameForm;


    @FXML
    void login(ActionEvent event) {
    }

    @FXML
    void initialize() {
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'ProperTeaRentals.fxml'.";
        assert passwordForm != null : "fx:id=\"passwordForm\" was not injected: check your FXML file 'ProperTeaRentals.fxml'.";
        assert usernameForm != null : "fx:id=\"usernameForm\" was not injected: check your FXML file 'ProperTeaRentals.fxml'.";


    }

}
