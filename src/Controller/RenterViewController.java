/**
 * Sample Skeleton for "RenterView.fxml" Controller Class
 * You can copy and paste this code into your favorite IDE
 **/

package Controller;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import Entity.Listing;
import Entity.Date;
import Entity.Address;


public class RenterViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="RenterViewGUI"
    private AnchorPane RenterViewGUI; // Value injected by FXMLLoader

    @FXML // fx:id="listingTable"
    private TableView<Listing> listingTable; // Value injected by FXMLLoader

    @FXML // fx:id="addressColumn"
    private TableColumn<Listing, Address> addressColumn; // Value injected by FXMLLoader

    @FXML // fx:id="furnishedColumn"
    private TableColumn<Listing, String> furnishedColumn; // Value injected by FXMLLoader

    @FXML // fx:id="bathColumn"
    private TableColumn<Listing, Integer> bathColumn; // Value injected by FXMLLoader

    @FXML // fx:id="bedColumn"
    private TableColumn<Listing, Integer> bedColumn; // Value injected by FXMLLoader

    @FXML // fx:id="quadrantColumn"
    private TableColumn<Listing, String> quadrantColumn; // Value injected by FXMLLoader

    @FXML // fx:id="typeColumn"
    private TableColumn<Listing, String> typeColumn; // Value injected by FXMLLoader

    @FXML // fx: id="priceColumn
    private TableColumn<Listing, Double> priceColumn;

    @FXML // fx:id="searchButton"
    private Button searchButton; // Value injected by FXMLLoader

    @FXML // fx:id="updateButton"
    private Button updateButton; // Value injected by FXMLLoader

    @FXML // fx:id="idColumn"
    private TableColumn<Listing, Integer> idColumn; // Value injected by FXMLLoader

    @FXML // fx:id="listingEndColumn"
    private TableColumn<Listing, Date> listingEndColumn; // Value injected by FXMLLoader

    @FXML // fx:id="listingStartColumn"
    private TableColumn<Listing, Date> listingStartColumn; // Value injected by FXMLLoader

    @FXML // fx:id="statusColumn"
    private TableColumn<Listing, String> statusColumn; // Value injected by FXMLLoader

    @FXML // fx:id="apartmentType"
    private CheckMenuItem apartmentType; // Value injected by FXMLLoader

    @FXML // fx:id="attachedType"
    private CheckMenuItem attachedType; // Value injected by FXMLLoader

    @FXML // fx:id="basementType"
    private CheckMenuItem basementType; // Value injected by FXMLLoader

    @FXML // fx:id="condoType"
    private CheckMenuItem condoType; // Value injected by FXMLLoader

    @FXML // fx:id="detachedType"
    private CheckMenuItem detachedType; // Value injected by FXMLLoader

    @FXML // fx:id="townhouseType"
    private CheckMenuItem townhouseType; // Value injected by FXMLLoader

    @FXML // fx:id="homeType"
    private MenuButton homeType; // Value injected by FXMLLoader

    @FXML // fx:id="northEast"
    private CheckBox northEast; // Value injected by FXMLLoader

    @FXML // fx:id="northWest"
    private CheckBox northWest; // Value injected by FXMLLoader

    @FXML // fx:id="southEast"
    private CheckBox southEast; // Value injected by FXMLLoader

    @FXML // fx:id="southWest"
    private CheckBox southWest; // Value injected by FXMLLoader

    @FXML // fx:id="furnished"
    private ToggleGroup furnished; // Value injected by FXMLLoader

    @FXML // fx:id="yesFurnished"
    private RadioButton yesFurnished; // Value injected by FXMLLoader

    @FXML // fx:id="notFurnished"
    private RadioButton notFurnished; // Value injected by FXMLLoader

    @FXML // fx:id="numOfBath"
    private TextField numOfBath; // Value injected by FXMLLoader

    @FXML // fx:id="numOfBed"
    private TextField numOfBed; // Value injected by FXMLLoader

    private Listener listener;

    // Handler for Button[fx:id="searchButton"] onAction
    @FXML
    void search(ActionEvent event) {
        // handle the event here
    }

    // Handler for Button[fx:id="updateButton"] onAction
    @FXML
    void update(ActionEvent event) {
        // handle the event here
        listingTable.setItems(getListing());
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert RenterViewGUI != null : "fx:id=\"RenterViewGUI\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert addressColumn != null : "fx:id=\"addressColumn\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert apartmentType != null : "fx:id=\"apartmentType\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert attachedType != null : "fx:id=\"attachedType\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert basementType != null : "fx:id=\"basementType\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert bathColumn != null : "fx:id=\"bathColumn\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert bedColumn != null : "fx:id=\"bedColumn\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert condoType != null : "fx:id=\"condoType\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert detachedType != null : "fx:id=\"detachedType\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert furnishedColumn != null : "fx:id=\"furnishedColumn\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert homeType != null : "fx:id=\"homeType\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert idColumn != null : "fx:id=\"idColumn\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert listingEndColumn != null : "fx:id=\"listingEndColumn\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert listingStartColumn != null : "fx:id=\"listingStartColumn\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert listingTable != null : "fx:id=\"listingTable\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert northEast != null : "fx:id=\"northEast\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert northWest != null : "fx:id=\"northWest\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert notFurnished != null : "fx:id=\"notFurnished\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert numOfBath != null : "fx:id=\"numOfBath\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert numOfBed != null : "fx:id=\"numOfBed\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert priceColumn != null : "fx:id=\"priceColumn\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert quadrantColumn != null : "fx:id=\"quadrantColumn\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert searchButton != null : "fx:id=\"searchButton\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert southEast != null : "fx:id=\"southEast\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert southWest != null : "fx:id=\"southWest\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert townhouseType != null : "fx:id=\"townhouseType\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert typeColumn != null : "fx:id=\"typeColumn\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert updateButton != null : "fx:id=\"updateButton\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert yesFurnished != null : "fx:id=\"yesFurnished\" was not injected: check your FXML file 'RenterView.fxml'.";

        // Initialize your logic here: all @FXML variables will have been injected

        addressColumn.setCellValueFactory(new PropertyValueFactory<Listing, Address>("address"));
        bathColumn.setCellValueFactory(new PropertyValueFactory<Listing, Integer>("numOfBathrooms"));
        bedColumn.setCellValueFactory(new PropertyValueFactory<Listing, Integer>("numOfBedrooms"));
        furnishedColumn.setCellValueFactory(new PropertyValueFactory<Listing, String>("furnished"));
        quadrantColumn.setCellValueFactory(new PropertyValueFactory<Listing, String>("quadrant"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Listing, String>("type"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Listing, Double>("price"));
        listingEndColumn.setCellValueFactory(new PropertyValueFactory<Listing, Date>("listingStart"));
        listingStartColumn.setCellValueFactory(new PropertyValueFactory<Listing, Date>("listingEnd"));
        idColumn.setCellValueFactory(new PropertyValueFactory<Listing, Integer>("listingID"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Listing, String>("status"));

        listingTable.setItems(getListings());
    }

    public ObservableList<Listing> getListings() {
        ArrayList<Listing> arrayListing = listener.getListener().getListings();
        ObservableList<Listing> listings = FXCollections.observableArrayList(arrayListing);
        return listings;
    }

}
