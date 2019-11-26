package Views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Controller.Listener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.*;
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

    @FXML
    private RadioButton landlordOption;

    @FXML
    private RadioButton renterOption;

    @FXML
    private Button signupButton;

    @FXML
    private ToggleGroup accountType;

    @FXML
    private TextField firstNameForm;

    @FXML
    private TextField lastNameForm;

    private Listener listener;

    @FXML
    void signup(ActionEvent event) throws IOException{
        if (usernameForm.getText().equals("") || passwordForm.getText().equals("") ||
                ((RadioButton) accountType.getSelectedToggle()).getText().equals(null) ||
                firstNameForm.getText().equals("") || lastNameForm.getText().equals("")) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("ERROR!");
            error.setContentText("Please enter name, username and password,\nand select an account type!");
            error.setHeaderText(null);
            error.showAndWait();
        } else {
            String response = listener.getListener().signup(
                    usernameForm.getText(),
                    passwordForm.getText(),
                    firstNameForm.getText(),
                    lastNameForm.getText(),
                    ((RadioButton) accountType.getSelectedToggle()).getText());
            if (response.equals("DONE")) {
                Alert success = new Alert(Alert.AlertType.INFORMATION);
                success.setTitle("Success!");
                success.setContentText("Successfully created an account!");
                success.setHeaderText(null);
                success.showAndWait();
            } else if (response.equals("ERROR")) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error!");
                error.setContentText("Failed to create an account.\nThe email may already be in use\nPlease try again!");
                error.setHeaderText(null);
                error.showAndWait();
            }
        }
    }


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
        assert accountType != null : "fx:id=\"accountType\" was not injected: check your FXML file 'ProperTeaRentals.fxml'.";
        assert firstNameForm != null : "fx:id=\"firstNameForm\" was not injected: check your FXML file 'ProperTeaRentals.fxml'.";
        assert guestButton != null : "fx:id=\"guestButton\" was not injected: check your FXML file 'ProperTeaRentals.fxml'.";
        assert landlordOption != null : "fx:id=\"landlordOption\" was not injected: check your FXML file 'ProperTeaRentals.fxml'.";
        assert lastNameForm != null : "fx:id=\"lastNameForm\" was not injected: check your FXML file 'ProperTeaRentals.fxml'.";
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'ProperTeaRentals.fxml'.";
        assert passwordForm != null : "fx:id=\"passwordForm\" was not injected: check your FXML file 'ProperTeaRentals.fxml'.";
        assert renterOption != null : "fx:id=\"renterOption\" was not injected: check your FXML file 'ProperTeaRentals.fxml'.";
        assert signupButton != null : "fx:id=\"signupButton\" was not injected: check your FXML file 'ProperTeaRentals.fxml'.";
        assert usernameForm != null : "fx:id=\"usernameForm\" was not injected: check your FXML file 'ProperTeaRentals.fxml'.";

        // Initialize your logic here: all @FXML variables will have been injected

    }
}
