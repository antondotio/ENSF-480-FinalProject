package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class LandlordViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField address;

    @FXML
    private TextField city;

    @FXML
    private TextField country;

    @FXML
    private ToggleGroup furnished;

    @FXML
    private TextArea landlordProperties;

    @FXML
    private RadioButton notFurnished;

    @FXML
    private TextField numBath;

    @FXML
    private TextField numBed;

    @FXML
    private TextField payID;

    @FXML
    private Button paymentButton;

    @FXML
    private TextField postalCode;

    @FXML
    private TextField quad;

    @FXML
    private Button registerButton;

    @FXML
    private TextField type;

    @FXML
    private Button updateButton;

    @FXML
    private Button fetchListingsButton;

    @FXML
    private TextField updateID;

    @FXML
    private TextField updateState;

    @FXML
    private RadioButton yesFurnished;

    private Listener listener;

    @FXML
    void makePayment(ActionEvent event) {
        String response = listener.getListener().payFee(payID.getText());
        // TODO need to maybe add a new window for simulating paying or something idk
        // TODO also do the DONE ERROR checking
        getMyListings();
    }

    @FXML
    void registerListing(ActionEvent event) {
        String response = listener.getListener().postListing(
                type.getText(),
                numBed.getText(),
                numBath.getText(),
                ((RadioButton) furnished.getSelectedToggle()).getText(),
                quad.getText(),
                address.getText(),
                city.getText(),
                country.getText(),
                postalCode.getText());

        if (response.equals("DONE")) {
            Alert success = new Alert(Alert.AlertType.INFORMATION);
            success.setTitle("Success!");
            success.setContentText("Listing has been registered!");
            success.setHeaderText(null);
            success.showAndWait();
        } else if (response.equals("ERROR")) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error!");
            error.setContentText("Error registering listing.\nPlease try again!");
            error.setHeaderText(null);
            error.showAndWait();
        }
        getMyListings();
    }

    @FXML
    void changeState(ActionEvent event) {
        String response = listener.getListener().changeState(updateID.getText(), updateState.getText());
        if (response.equals("DONE")) {
            Alert success = new Alert(Alert.AlertType.INFORMATION);
            success.setTitle("Success!");
            success.setContentText("State has been changed!");
            success.setHeaderText(null);
            success.showAndWait();
        } else if (response.equals("ERROR")) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error!");
            error.setContentText("State has not been changed.\nPlease try again!");
            error.setHeaderText(null);
            error.showAndWait();
        }
        getMyListings();
    }

    @FXML
    void fetchListings(ActionEvent event) {
        getMyListings();
    }

    @FXML
    void initialize() {
        assert address != null : "fx:id=\"address\" was not injected: check your FXML file 'LandlordView.fxml'.";
        assert city != null : "fx:id=\"city\" was not injected: check your FXML file 'LandlordView.fxml'.";
        assert fetchListingsButton != null : "fx:id=\"fetchListingsButton\" was not injected: check your FXML file 'LandlordView.fxml'.";
        assert country != null : "fx:id=\"country\" was not injected: check your FXML file 'LandlordView.fxml'.";
        assert furnished != null : "fx:id=\"furnished\" was not injected: check your FXML file 'LandlordView.fxml'.";
        assert landlordProperties != null : "fx:id=\"landlordProperties\" was not injected: check your FXML file 'LandlordView.fxml'.";
        assert notFurnished != null : "fx:id=\"notFurnished\" was not injected: check your FXML file 'LandlordView.fxml'.";
        assert numBath != null : "fx:id=\"numBath\" was not injected: check your FXML file 'LandlordView.fxml'.";
        assert numBed != null : "fx:id=\"numBed\" was not injected: check your FXML file 'LandlordView.fxml'.";
        assert payID != null : "fx:id=\"payID\" was not injected: check your FXML file 'LandlordView.fxml'.";
        assert paymentButton != null : "fx:id=\"paymentButton\" was not injected: check your FXML file 'LandlordView.fxml'.";
        assert postalCode != null : "fx:id=\"postalCode\" was not injected: check your FXML file 'LandlordView.fxml'.";
        assert quad != null : "fx:id=\"quad\" was not injected: check your FXML file 'LandlordView.fxml'.";
        assert registerButton != null : "fx:id=\"registerButton\" was not injected: check your FXML file 'LandlordView.fxml'.";
        assert type != null : "fx:id=\"type\" was not injected: check your FXML file 'LandlordView.fxml'.";
        assert updateButton != null : "fx:id=\"updateButton\" was not injected: check your FXML file 'LandlordView.fxml'.";
        assert updateID != null : "fx:id=\"updateID\" was not injected: check your FXML file 'LandlordView.fxml'.";
        assert updateState != null : "fx:id=\"updateState\" was not injected: check your FXML file 'LandlordView.fxml'.";
        assert yesFurnished != null : "fx:id=\"yesFurnished\" was not injected: check your FXML file 'LandlordView.fxml'.";


    }

    public void getMyListings() {
        String myListings = listener.getListener().getLandlordListings();
        String table =
                ("Listing ID\t\t|\tListing Start\t|\tListing End\t|\tState\t|\tFee\t\t|\tPaid\t\t|\t\t\tAddress\t\t\t\t\t|\tQuadrant\t\t|\tHouse Type\t|\tBedrooms\t|\tBathrooms\t|\tFurnished\t\t|\n" +
                        "-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                        myListings);
        landlordProperties.setWrapText(false);
        landlordProperties.setEditable(true);
        landlordProperties.setText(table);
        landlordProperties.setEditable(false);
    }

}