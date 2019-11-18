package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ProperTeaRentalsController {
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="loginButton"
    private Button loginButton; // Value injected by FXMLLoader

    @FXML // fx:id="passwordForm"
    private PasswordField passwordForm; // Value injected by FXMLLoader

    @FXML // fx:id="usernameForm"
    private TextField usernameForm; // Value injected by FXMLLoader


    // Handler for Button[fx:id="loginButton"] onAction
    @FXML
    void login(ActionEvent event) {
        // handle the event here
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'ProperTeaRentals.fxml'.";
        assert passwordForm != null : "fx:id=\"passwordForm\" was not injected: check your FXML file 'ProperTeaRentals.fxml'.";
        assert usernameForm != null : "fx:id=\"usernameForm\" was not injected: check your FXML file 'ProperTeaRentals.fxml'.";

        // Initialize your logic here: all @FXML variables will have been injected

    }
}
