package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class ManagerViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField endDate;

    @FXML
    private TextArea infoTable;

    @FXML
    private Button landlordButton;

    @FXML
    private AnchorPane managerView;

    @FXML
    private Button propertyButton;

    @FXML
    private Button renterButton;

    @FXML
    private Button requestSummaryButton;

    @FXML
    private TextField startDate;

    @FXML
    private TextField updateFee;

    @FXML
    private Button updateFeePeriodButton;

    @FXML
    private TextField updateID;

    @FXML
    private TextField updatePeriod;

    @FXML
    private TextField updateState;

    @FXML
    private Button updateStateButton;

    private Listener listener;

    @FXML
    void changeState(ActionEvent event) {
        if(updateID.getText().equals("") || updateState.getText().equals("")) {
            Alert noID = new Alert(Alert.AlertType.ERROR);
            noID.setTitle("Error");
            noID.setContentText("Please enter both an ID and a new state!");
            noID.setHeaderText(null);
            noID.showAndWait();
        } else {
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
        }

        displayListings(event);
    }

    @FXML
    void displayLandlords(ActionEvent event) {
        String landlords = listener.getListener().getAllLandlords();
        String landlordsTable =
                ("User ID\t|\t\tName\t\t|\t\tEmail Address\t\t|\n" +
                        "------------------------------------------------------------------------\n" +
                        landlords);
        setTable(landlordsTable);
    }

    @FXML
    void displayListings(ActionEvent event) {
        String listings = listener.getListener().getAllListings();
        String listingsTable =
                ("Listing ID\t\t|\tListing Start\t|\tListing End\t|\tState\t|\tFee\t\t|\tPaid\t\t|\tFee Period\t|\t\t\t\tAddress\t\t\t\t\t\t|\tQuadrant\t\t|\tHouse Type\t|\tBedrooms\t|\tBathrooms\t|\tFurnished\t\t|\n" +
                        "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                        listings);
        setTable(listingsTable);
    }

    @FXML
    void displayRenters(ActionEvent event) {
        String renters = listener.getListener().getAllRenters();
        String rentersTable =
                ("User ID\t|\t\tName\t\t|\t\tEmail Address\t\t|\n" +
                        "------------------------------------------------------------------------\n" +
                        renters);
        setTable(rentersTable);
    }

    @FXML
    void requestSummary(ActionEvent event) {
    // TODO need to do dis :3...
    }

    @FXML
    void updateFeePeriod(ActionEvent event) {
        String response = listener.getListener().updateListingFees(updateID.getText(), updateFee.getText(), updatePeriod.getText());
        if (response.equals("DONE")) {
            Alert success = new Alert(Alert.AlertType.INFORMATION);
            success.setTitle("Success!");
            success.setContentText("Fee and period has been changed!");
            success.setHeaderText(null);
            success.showAndWait();
        } else if (response.equals("ERROR")) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error!");
            error.setContentText("Fee and period has not been changed.\nPlease try again!");
            error.setHeaderText(null);
            error.showAndWait();
        }
        displayListings(event);
    }

    @FXML
    void initialize() {
        assert endDate != null : "fx:id=\"endDate\" was not injected: check your FXML file 'ManagerView.fxml'.";
        assert infoTable != null : "fx:id=\"infoTable\" was not injected: check your FXML file 'ManagerView.fxml'.";
        assert landlordButton != null : "fx:id=\"landlordButton\" was not injected: check your FXML file 'ManagerView.fxml'.";
        assert managerView != null : "fx:id=\"managerView\" was not injected: check your FXML file 'ManagerView.fxml'.";
        assert propertyButton != null : "fx:id=\"propertyButton\" was not injected: check your FXML file 'ManagerView.fxml'.";
        assert renterButton != null : "fx:id=\"renterButton\" was not injected: check your FXML file 'ManagerView.fxml'.";
        assert requestSummaryButton != null : "fx:id=\"requestSummaryButton\" was not injected: check your FXML file 'ManagerView.fxml'.";
        assert startDate != null : "fx:id=\"startDate\" was not injected: check your FXML file 'ManagerView.fxml'.";
        assert updateFee != null : "fx:id=\"updateFee\" was not injected: check your FXML file 'ManagerView.fxml'.";
        assert updateFeePeriodButton != null : "fx:id=\"updateFeePeriodButton\" was not injected: check your FXML file 'ManagerView.fxml'.";
        assert updateID != null : "fx:id=\"updateID\" was not injected: check your FXML file 'ManagerView.fxml'.";
        assert updatePeriod != null : "fx:id=\"updatePeriod\" was not injected: check your FXML file 'ManagerView.fxml'.";
        assert updateState != null : "fx:id=\"updateState\" was not injected: check your FXML file 'ManagerView.fxml'.";
        assert updateStateButton != null : "fx:id=\"updateStateButton\" was not injected: check your FXML file 'ManagerView.fxml'.";


    }

    public void setTable(String table){
        infoTable.setWrapText(false);
        infoTable.setEditable(true);
        infoTable.setText(table);
        infoTable.setEditable(false);
    }

}
