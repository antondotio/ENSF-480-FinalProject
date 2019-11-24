package Controller;

import java.util.ArrayList;

public class Listener {
    
    private Client client;
    private static Listener listener = null;

    private Listener() {
        listener = this;
    }

    static public Listener getListener()
    {
        if(listener == null)
            listener = new Listener();
        return listener;
    }

    public void setClient(Client c)
    {
        client = c;
    }

    public String getListings(String type, String beds, String baths, String furnished, String quad) {
        return client.getListings(type, beds, baths, furnished, quad);
    }

    public String loginCommand(String username, String password) {
        return client.login(username, password);
    }

    public String postListing(String type, String bedrooms, String baths, String furnished,
    String quad, String street, String city, String country, String postalCode) {
        return client.postListing(type, bedrooms, baths, furnished, quad, street, city, country, postalCode);
    }

    public String getLandlordListings() {
        return client.getLandlordListings();
    }

    public String changeState(String listingID, String newState) {
        return client.changeListingState(listingID, newState);
    }

    public String payFee(String listingID) {
        return client.payFee(listingID);
    }

    public String updateListingFees(String listingID, String newFee, String newPeriod) {
        return client.updateListingFees(listingID, newFee, newPeriod);
    }

    public String getSummary(String startDate, String endDate) {
        return client.getSummary(startDate, endDate);
    }

    public String getRenters() {
        return client.getRenters();
    }

    public String getLandlords() {
        return client.getLandlords();
    }

    public String getProperties() {
        return client.getProperties();
    }

    public String sendEmail(String listingID, String message) {
        return client.sendEmail(listingID, message);
    }
}