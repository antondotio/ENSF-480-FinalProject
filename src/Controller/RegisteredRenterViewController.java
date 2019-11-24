package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;


public class RegisteredRenterViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private Button emailLandlordButton;

    @FXML
    private TextField emailLandlordID;

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
    private TextField numBath;

    @FXML
    private TextField numBeds;

    @FXML
    private RadioButton nw;

    @FXML
    private ToggleGroup quad;

    @FXML
    private RadioButton se;

    @FXML
    private Button searchButton;

    @FXML
    private Button subscribeButton;

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


    @FXML
    void emailLandlord(ActionEvent event) {
    }

    @FXML
    void search(ActionEvent event) {
    }

    @FXML
    void subscribe(ActionEvent event) {
    }

    @FXML
    void update(ActionEvent event) {
    }

    @FXML
    void initialize() {
        assert anyFurnished != null : "fx:id=\"anyFurnished\" was not injected: check your FXML file 'RegisteredRenterView.fxml'.";
        assert anyQuad != null : "fx:id=\"anyQuad\" was not injected: check your FXML file 'RegisteredRenterView.fxml'.";
        assert anyType != null : "fx:id=\"anyType\" was not injected: check your FXML file 'RegisteredRenterView.fxml'.";
        assert apartmentType != null : "fx:id=\"apartmentType\" was not injected: check your FXML file 'RegisteredRenterView.fxml'.";
        assert attachedType != null : "fx:id=\"attachedType\" was not injected: check your FXML file 'RegisteredRenterView.fxml'.";
        assert detachedType != null : "fx:id=\"detachedType\" was not injected: check your FXML file 'RegisteredRenterView.fxml'.";
        assert emailLandlordButton != null : "fx:id=\"emailLandlordButton\" was not injected: check your FXML file 'RegisteredRenterView.fxml'.";
        assert emailLandlordID != null : "fx:id=\"emailLandlordID\" was not injected: check your FXML file 'RegisteredRenterView.fxml'.";
        assert emailMessage != null : "fx:id=\"emailMessage\" was not injected: check your FXML file 'RegisteredRenterView.fxml'.";
        assert furnished != null : "fx:id=\"furnished\" was not injected: check your FXML file 'RegisteredRenterView.fxml'.";
        assert listingTable != null : "fx:id=\"listingTable\" was not injected: check your FXML file 'RegisteredRenterView.fxml'.";
        assert ne != null : "fx:id=\"ne\" was not injected: check your FXML file 'RegisteredRenterView.fxml'.";
        assert notFurnished != null : "fx:id=\"notFurnished\" was not injected: check your FXML file 'RegisteredRenterView.fxml'.";
        assert numBath != null : "fx:id=\"numBath\" was not injected: check your FXML file 'RegisteredRenterView.fxml'.";
        assert numBeds != null : "fx:id=\"numBeds\" was not injected: check your FXML file 'RegisteredRenterView.fxml'.";
        assert nw != null : "fx:id=\"nw\" was not injected: check your FXML file 'RegisteredRenterView.fxml'.";
        assert quad != null : "fx:id=\"quad\" was not injected: check your FXML file 'RegisteredRenterView.fxml'.";
        assert se != null : "fx:id=\"se\" was not injected: check your FXML file 'RegisteredRenterView.fxml'.";
        assert searchButton != null : "fx:id=\"searchButton\" was not injected: check your FXML file 'RegisteredRenterView.fxml'.";
        assert subscribeButton != null : "fx:id=\"subscribeButton\" was not injected: check your FXML file 'RegisteredRenterView.fxml'.";
        assert sw != null : "fx:id=\"sw\" was not injected: check your FXML file 'RegisteredRenterView.fxml'.";
        assert townType != null : "fx:id=\"townType\" was not injected: check your FXML file 'RegisteredRenterView.fxml'.";
        assert type != null : "fx:id=\"type\" was not injected: check your FXML file 'RegisteredRenterView.fxml'.";
        assert updateButton != null : "fx:id=\"updateButton\" was not injected: check your FXML file 'RegisteredRenterView.fxml'.";
        assert yesFurnished != null : "fx:id=\"yesFurnished\" was not injected: check your FXML file 'RegisteredRenterView.fxml'.";


    }

}
