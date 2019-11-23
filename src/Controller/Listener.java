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

    public String updateListing() {
        //  needs manager GUI
        return null;
    }

    public String getSingleListing() {
        // needs manager GUI
        return null;
    }

    public String getSummary() {
        //  needs manager GUI
        return null;
    }

    public String changeState(String listingID, String newState) {
        return client.changeListingState(listingID, newState);
    }
}