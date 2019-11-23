package Entity;

import java.util.ArrayList;

public class LandlordAccount extends Account {

    ArrayList<Listing> myListings;

    public LandlordAccount(Name name, int accountID, String email) {
        super(name, accountID, email);
        myListings = new ArrayList<Listing>();
    }

    //send property to controller to request a listing to be made by a manager
    public void addListing(Property property)
    {
        
    }

    public void suspendListing(int listingID)
    {
        
    }
}