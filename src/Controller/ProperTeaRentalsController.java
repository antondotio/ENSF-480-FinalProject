package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ProperTeaRentalsController {
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="guestButton"
    private Button guestButton; // Value injected by FXMLLoader

    @FXML // fx:id="loginButton"
    private Button loginButton; // Value injected by FXMLLoader

    @FXML // fx:id="passwordForm"
    private PasswordField passwordForm; // Value injected by FXMLLoader

    @FXML // fx:id="usernameForm"
    private TextField usernameForm; // Value injected by FXMLLoader

    private Listener listener;

    // Handler for Button[fx:id="guestButton"] onAction
    @FXML
    void guestLogin(ActionEvent event) throws IOException {
        // handle the event here
        Parent renterViewParent = FXMLLoader.load(getClass().getResource("RenterView.fxml"));
        Scene renterViewScene = new Scene(renterViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(renterViewScene);
        window.show();
    }

    // Handler for Button[fx:id="loginButton"] onAction
    @FXML
    void login(ActionEvent event) throws IOException {
        // handle the event here
        String type = listener.getListener().loginCommand(usernameForm.getText(), passwordForm.getText());
        if(type.equals("RENTER")){
            Parent registeredRenterViewParent = FXMLLoader.load(getClass().getResource("RegisteredRenterView.fxml"));
            Scene registeredRenterViewScene = new Scene(registeredRenterViewParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(registeredRenterViewScene);
            window.show();
        } else if (type.equals("LANDLORD")){
            Parent landlordViewParent = FXMLLoader.load(getClass().getResource("LandlordView.fxml"));
            Scene landlordViewScene = new Scene(landlordViewParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(landlordViewScene);
            window.show();

        } else if (type.equals("MANAGER")){
            Parent managerViewParent = FXMLLoader.load(getClass().getResource("ManagerView.fxml"));
            Scene managerViewScene = new Scene(managerViewParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(managerViewScene);
            window.show();
        } else if (type.equals("ERROR")){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("ERROR!");
            error.setContentText("Wrong username or password!");
            error.setHeaderText(null);
            error.showAndWait();
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert guestButton != null : "fx:id=\"guestButton\" was not injected: check your FXML file 'ProperTeaRentals.fxml'.";
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'ProperTeaRentals.fxml'.";
        assert passwordForm != null : "fx:id=\"passwordForm\" was not injected: check your FXML file 'ProperTeaRentals.fxml'.";
        assert usernameForm != null : "fx:id=\"usernameForm\" was not injected: check your FXML file 'ProperTeaRentals.fxml'.";

        // Initialize your logic here: all @FXML variables will have been injected

    }
}
