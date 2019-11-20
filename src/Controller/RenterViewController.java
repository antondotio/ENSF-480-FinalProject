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


public class RenterViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane RenterViewGUI;

    @FXML
    private TableColumn<String, String> addressColumn;

    @FXML
    private CheckMenuItem apartmentType;

    @FXML
    private CheckMenuItem attachedType;

    @FXML
    private CheckMenuItem basementType;

    @FXML
    private TableColumn<String, String> bathColumn;

    @FXML
    private TableColumn<String, String> bedColumn;

    @FXML
    private CheckMenuItem condoType;

    @FXML
    private CheckMenuItem detachedType;

    @FXML
    private Button emailButton;

    @FXML
    private ToggleGroup furnished;

    @FXML
    private TableColumn<String, String> furnishedColumn;

    @FXML
    private MenuButton homeType;

    @FXML
    private TableColumn<String, String> idColumn;

    @FXML
    private TableColumn<String, String> listingEndColumn;

    @FXML
    private TableColumn<String, String> listingStartColumn;

    @FXML
    private TableView<String> listingTable;

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
    private TableColumn<String, String> priceColumn;

    @FXML
    private TableColumn<String, String> quadrantColumn;

    @FXML
    private Button searchButton;

    @FXML
    private CheckBox southEast;

    @FXML
    private CheckBox southWest;

    @FXML
    private CheckMenuItem townhouseType;

    @FXML
    private TableColumn<String, String> typeColumn;

    @FXML
    private Button updateButton;

    @FXML
    private RadioButton yesFurnished;

    private Listener listener;


    @FXML
    void emailLandlord(ActionEvent event) {
    }

    @FXML
    void search(ActionEvent event) {
    }

    @FXML
    void update(ActionEvent event) {
        listingTable.setItems(getListings());
    }

    @FXML
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
        assert emailButton != null : "fx:id=\"emailButton\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert furnished != null : "fx:id=\"furnished\" was not injected: check your FXML file 'RenterView.fxml'.";
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

        addressColumn.setCellValueFactory(new PropertyValueFactory<String, String>("address"));
        bathColumn.setCellValueFactory(new PropertyValueFactory<String, String>("numOfBathrooms"));
        bedColumn.setCellValueFactory(new PropertyValueFactory<String, String>("numOfBedrooms"));
        furnishedColumn.setCellValueFactory(new PropertyValueFactory<String, String>("furnished"));
        quadrantColumn.setCellValueFactory(new PropertyValueFactory<String, String>("quadrant"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<String, String>("type"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<String, String>("price"));
        listingEndColumn.setCellValueFactory(new PropertyValueFactory<String, String>("listingStart"));
        listingStartColumn.setCellValueFactory(new PropertyValueFactory<String, String>("listingEnd"));
        idColumn.setCellValueFactory(new PropertyValueFactory<String, String>("listingID"));

        listingTable.setItems(getListings());
    }

    public ObservableList<String> getListings() {
        ArrayList<String> arrayListing = listener.getListener().getListings();
        ObservableList<String> listings = FXCollections.observableArrayList(arrayListing);
        return listings;
    }

}
