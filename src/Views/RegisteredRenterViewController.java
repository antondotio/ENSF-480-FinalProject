package Views;

import java.net.URL;
import java.util.ResourceBundle;

import Controller.Listener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


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
    private Button getSubscriptionButton;

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
    private TextField subscriptionID;

    @FXML
    private RadioButton sw;

    @FXML
    private RadioButton townType;

    @FXML
    private ToggleGroup type;

    @FXML
    private Button unsubscribeButton;

    @FXML
    private RadioButton yesFurnished;

    @FXML
    private TextField emailForm;

    private Listener listener;

    @FXML
    void emailLandlord(ActionEvent event) {
        if(emailListingID.getText().equals("") || emailForm.getText().equals("")) {
            Alert noID = new Alert(Alert.AlertType.ERROR);
            noID.setTitle("Error");
            noID.setContentText("Please add a listing ID and an email!");
            noID.setHeaderText(null);
            noID.showAndWait();
        } else {
            String response = listener.getListener().sendEmail(emailForm.getText(), emailLandlordID.getText(), emailMessage.getText());
            if (response.equals("DONE")) {
                Alert success = new Alert(Alert.AlertType.INFORMATION);
                success.setTitle("Success!");
                success.setContentText("Email has been sent to landlord!");
                success.setHeaderText(null);
                success.showAndWait();
                emailLandlordID.setText("");
                emailMessage.setText("");
            } else if (response.equals("ERROR")) {
                Alert email = new Alert(Alert.AlertType.INFORMATION);
                email.setTitle("Sending Email");
                email.setContentText("The email was not sent.\nPlease try again!");
                email.setHeaderText(null);
                email.showAndWait();

            }
        }
    }

    @FXML
    void getSubscriptions(ActionEvent event) {
        String subscriptions = listener.getListener().getSearchCriteria();
        String table = ("Subscription ID\t|\tQuadrant\t\t|\tHouse Type\t|\tBedrooms\t|\tBathrooms\t|\tFurnished\t\t|\n" +
                            "-----------------------------------------------------------------------------------------------------------------------------------------\n" +
                            subscriptions);
        listingTable.setWrapText(false);
        listingTable.setEditable(true);
        listingTable.setText(table);
        listingTable.setEditable(false);
    }

    @FXML
    void search(ActionEvent event) {
        String listings = listener.getListener().getListings(
                ((RadioButton) type.getSelectedToggle()).getText(),
                numBeds.getText(),
                numBath.getText(),
                ((RadioButton) furnished.getSelectedToggle()).getText(),
                ((RadioButton) quad.getSelectedToggle()).getText());
        getListings(listings);
    }

    @FXML
    void subscribe(ActionEvent event) {
        String response = listener.getListener().subscribe(
                ((RadioButton) type.getSelectedToggle()).getText(),
                numBeds.getText(),
                numBath.getText(),
                ((RadioButton) furnished.getSelectedToggle()).getText(),
                ((RadioButton) quad.getSelectedToggle()).getText());
        if (response.equals("DONE")) {
            Alert success = new Alert(Alert.AlertType.INFORMATION);
            success.setTitle("Success!");
            success.setContentText("Successfully subscribed to search criteria!");
            success.setHeaderText(null);
            success.showAndWait();
        } else if (response.equals("ERROR")) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error!");
            error.setContentText("Failed to subscribe to search criteria.\nPlease try again!");
            error.setHeaderText(null);
            error.showAndWait();
        }
        getSubscriptions(event);
    }

    @FXML
    void unsubscribe(ActionEvent event) {
        String response = listener.getListener().unsubscribe(subscriptionID.getText());
        if (response.equals("DONE")) {
            Alert success = new Alert(Alert.AlertType.INFORMATION);
            success.setTitle("Success!");
            success.setContentText("Successfully unsubscribed to search criteria!");
            success.setHeaderText(null);
            success.showAndWait();
        } else if (response.equals("ERROR")) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error!");
            error.setContentText("Failed to unsubscribe to search criteria.\nPlease try again!");
            error.setHeaderText(null);
            error.showAndWait();
        }
        getSubscriptions(event);
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
        assert getSubscriptionButton != null : "fx:id=\"getSubscriptionButton\" was not injected: check your FXML file 'RegisteredRenterView.fxml'.";
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
        assert subscriptionID != null : "fx:id=\"subscriptionID\" was not injected: check your FXML file 'RegisteredRenterView.fxml'.";
        assert sw != null : "fx:id=\"sw\" was not injected: check your FXML file 'RegisteredRenterView.fxml'.";
        assert townType != null : "fx:id=\"townType\" was not injected: check your FXML file 'RegisteredRenterView.fxml'.";
        assert type != null : "fx:id=\"type\" was not injected: check your FXML file 'RegisteredRenterView.fxml'.";
        assert unsubscribeButton != null : "fx:id=\"unsubscribeButton\" was not injected: check your FXML file 'RegisteredRenterView.fxml'.";
        assert yesFurnished != null : "fx:id=\"yesFurnished\" was not injected: check your FXML file 'RegisteredRenterView.fxml'.";

        String listingsNotifs = listener.getListener().getNotifications();
        if(!listingsNotifs.equals("")) {
            Stage notif = new Stage();
            notif.initModality(Modality.APPLICATION_MODAL);
            notif.setTitle("NOTIFICATION");
            String table = ("New Listings for your search criteria have arrived!\n\n" +
                    "Listing ID\t\t|\t\t\t\tAddress\t\t\t\t\t|\tQuadrant\t\t|\tHouse Type\t|\tBedrooms\t|\tBathrooms\t|\tFurnished\t\t|\n" +
                    "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                    listingsNotifs);
            Label label = new Label(table);
            Button button1 = new Button("OK");
            button1.setOnAction(e -> notif.close());
            VBox layout = new VBox(10);
            layout.getChildren().addAll(label, button1);
            layout.setAlignment(Pos.CENTER);
            Scene scene1 = new Scene(layout, 900, 200);
            notif.setScene(scene1);
            notif.show();
        }
    }

    public void getListings(String listings){
        String table =
                ("Listing ID\t\t|\t\t\t\tAddress\t\t\t\t\t\t|\tQuadrant\t\t|\tHouse Type\t|\tBedrooms\t|\tBathrooms\t|\tFurnished\t\t|\n" +
                        "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                        listings);
        listingTable.setWrapText(false);
        listingTable.setEditable(true);
        listingTable.setText(table);
        listingTable.setEditable(false);
    }

}
