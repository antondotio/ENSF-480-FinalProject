package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;


public class RenterViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane RenterViewGUI;

    @FXML
    private RadioButton anyFurnished;

    @FXML
    private CheckMenuItem apartmentType;

    @FXML
    private CheckMenuItem attachedType;

    @FXML
    private CheckMenuItem detachedType;

    @FXML
    private Button emailButton;

    @FXML
    private TextField emailListingID;

    @FXML
    private TextArea emailMessage;

    @FXML
    private ToggleGroup furnished;

    @FXML
    private MenuButton homeType;

    @FXML
    private CheckBox northEast;

    @FXML
    private CheckBox northWest;

    @FXML
    private RadioButton notFurnished;

    @FXML
    private TextField numOfBath;

    @FXML
    private TextField numOfBed;

    @FXML
    private Button searchButton;

    @FXML
    private CheckBox southEast;

    @FXML
    private CheckBox southWest;

    @FXML
    private CheckMenuItem townhouseType;

    @FXML
    private Button updateButton;

    @FXML
    private RadioButton yesFurnished;

    @FXML
    private TextArea listingTable;

    private Listener listener;

    @FXML
    void emailLandlord(ActionEvent event) {
        if(emailListingID.getText().equals("")) {
            Alert noID = new Alert(Alert.AlertType.ERROR);
            noID.setTitle("Error");
            noID.setContentText("Please add a listing ID!");
            noID.setHeaderText(null);
            noID.showAndWait();
        } else {
            Alert email = new Alert(Alert.AlertType.INFORMATION);
            email.setTitle("Sending Email");
            email.setContentText("If the Listing ID you inputted is valid,\n an email has been sent to the landlord.");
            email.setHeaderText(null);
            email.showAndWait();
            emailListingID.setText("");
            emailMessage.setText("");
        }
    }

    @FXML
    void search(ActionEvent event) {
        String listings = listener.getListener().getListings(
                apartmentType.isSelected(),
                attachedType.isSelected(),
                detachedType.isSelected(),
                townhouseType.isSelected(),
                numOfBed.getText(),
                numOfBath.getText(),
                ((RadioButton) furnished.getSelectedToggle()).getText(),
                northEast.isSelected(),
                northWest.isSelected(),
                southEast.isSelected(),
                southWest.isSelected());
        getListing(listings);
    }

    @FXML
    void update(ActionEvent event) {
        search(event);
    }

    @FXML
    void initialize() {
        assert RenterViewGUI != null : "fx:id=\"RenterViewGUI\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert anyFurnished != null : "fx:id=\"anyFurnished\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert apartmentType != null : "fx:id=\"apartmentType\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert attachedType != null : "fx:id=\"attachedType\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert detachedType != null : "fx:id=\"detachedType\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert emailButton != null : "fx:id=\"emailButton\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert emailListingID != null : "fx:id=\"emailListingID\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert emailMessage != null : "fx:id=\"emailMessage\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert furnished != null : "fx:id=\"furnished\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert homeType != null : "fx:id=\"homeType\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert northEast != null : "fx:id=\"northEast\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert northWest != null : "fx:id=\"northWest\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert notFurnished != null : "fx:id=\"notFurnished\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert numOfBath != null : "fx:id=\"numOfBath\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert numOfBed != null : "fx:id=\"numOfBed\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert searchButton != null : "fx:id=\"searchButton\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert southEast != null : "fx:id=\"southEast\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert southWest != null : "fx:id=\"southWest\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert townhouseType != null : "fx:id=\"townhouseType\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert updateButton != null : "fx:id=\"updateButton\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert yesFurnished != null : "fx:id=\"yesFurnished\" was not injected: check your FXML file 'RenterView.fxml'.";
    }

    public void getListing(String listings){
        listingTable.setEditable(true);
        listingTable.setText(listings);
        listingTable.setEditable(false);
    }
}
