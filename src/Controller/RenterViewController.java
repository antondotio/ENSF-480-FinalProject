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
    private RadioButton anyQuad;

    @FXML
    private RadioButton anyType;

    @FXML
    private RadioButton apartmentType;

    @FXML
    private RadioButton attachedType;

    @FXML
    private RadioButton detachedType;

    @FXML
    private Button emailButton;

    @FXML
    private TextField emailListingID;

    @FXML
    private TextArea emailMessage;

    @FXML
    private ToggleGroup furnished;

    @FXML
    private TextArea listingTable;

    @FXML
    private RadioButton ne;

    @FXML
    private RadioButton notFurnished;

    @FXML
    private TextField numOfBath;

    @FXML
    private TextField numOfBed;

    @FXML
    private RadioButton nw;

    @FXML
    private ToggleGroup quad;

    @FXML
    private RadioButton se;

    @FXML
    private Button searchButton;

    @FXML
    private RadioButton sw;

    @FXML
    private RadioButton townType;

    @FXML
    private ToggleGroup type;

    @FXML
    private Button updateButton;

    @FXML
    private RadioButton yesFurnished;

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
                ((RadioButton) type.getSelectedToggle()).getText(),
                numOfBed.getText(),
                numOfBath.getText(),
                ((RadioButton) furnished.getSelectedToggle()).getText(),
                ((RadioButton) quad.getSelectedToggle()).getText());
        getListings(listings);
    }

    @FXML
    void update(ActionEvent event) {
        search(event);
    }

    @FXML
    void initialize() {
        assert RenterViewGUI != null : "fx:id=\"RenterViewGUI\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert anyFurnished != null : "fx:id=\"anyFurnished\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert anyQuad != null : "fx:id=\"anyQuad\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert anyType != null : "fx:id=\"anyType\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert apartmentType != null : "fx:id=\"apartmentType\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert attachedType != null : "fx:id=\"attachedType\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert detachedType != null : "fx:id=\"detachedType\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert emailButton != null : "fx:id=\"emailButton\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert emailListingID != null : "fx:id=\"emailListingID\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert emailMessage != null : "fx:id=\"emailMessage\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert furnished != null : "fx:id=\"furnished\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert listingTable != null : "fx:id=\"listingTable\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert ne != null : "fx:id=\"ne\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert notFurnished != null : "fx:id=\"notFurnished\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert numOfBath != null : "fx:id=\"numOfBath\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert numOfBed != null : "fx:id=\"numOfBed\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert nw != null : "fx:id=\"nw\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert quad != null : "fx:id=\"quad\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert se != null : "fx:id=\"se\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert searchButton != null : "fx:id=\"searchButton\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert sw != null : "fx:id=\"sw\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert townType != null : "fx:id=\"townType\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert type != null : "fx:id=\"type\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert updateButton != null : "fx:id=\"updateButton\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert yesFurnished != null : "fx:id=\"yesFurnished\" was not injected: check your FXML file 'RenterView.fxml'.";

    }

    public void getListings(String listings){
        String table =
                ("Listing ID\t\t|\t\t\t\tAddress\t\t\t\t\t|\tQuadrant\t\t|\tHouse Type\t|\tBedrooms\t|\tBathrooms\t|\tFurnished\t\t|\n" +
                        "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                        listings);
        listingTable.setWrapText(false);
        listingTable.setEditable(true);
        listingTable.setText(table);
        listingTable.setEditable(false);
    }
}
