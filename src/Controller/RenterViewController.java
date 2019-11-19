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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import Entity.Listing;
import Entity.Date;


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
    private TableColumn<Listing, String> addressColumn; // Value injected by FXMLLoader

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
        assert bathColumn != null : "fx:id=\"bathColumn\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert bedColumn != null : "fx:id=\"bedColumn\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert furnishedColumn != null : "fx:id=\"furnishedColumn\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert listingTable != null : "fx:id=\"listingTable\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert priceColumn != null : "fx:id=\"priceColumn\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert quadrantColumn != null : "fx:id=\"quadrantColumn\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert searchButton != null : "fx:id=\"searchButton\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert typeColumn != null : "fx:id=\"typeColumn\" was not injected: check your FXML file 'RenterView.fxml'.";
        assert updateButton != null : "fx:id=\"updateButton\" was not injected: check your FXML file 'RenterView.fxml'.";

        // Initialize your logic here: all @FXML variables will have been injected

        addressColumn.setCellValueFactory(new PropertyValueFactory<Listing, String>("address"));
        bathColumn.setCellValueFactory(new PropertyValueFactory<Listing, Integer>("numOfBathrooms"));
        bedColumn.setCellValueFactory(new PropertyValueFactory<Listing, Integer>("numOfBedrooms"));
        furnishedColumn.setCellValueFactory(new PropertyValueFactory<Listing, String>("furnished"));
        quadrantColumn.setCellValueFactory(new PropertyValueFactory<Listing, String>("quadrant"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Listing, String>("type"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Listing, Double>("price"));

        listingTable.setItems(getListing());
    }

    public ObservableList<Listing> getListing() {
        ArrayList<Listing> arrayListing = listener.getListener().performAction("GET/LISTINGS");
        ObservableList<Listing> listings = FXCollections.observableArrayList(arrayListing);
        return listings;
    }

}
