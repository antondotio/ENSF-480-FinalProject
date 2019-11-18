package Entity;

import java.util.ArrayList;

public class LandlordAccount extends Account {

    ArrayList<Listing> myListings;

    public LandlordAccount(Name name, int accountID, String email) {
        super(name, accountID, email);
        myListings = new ArrayList<Listing>();
    }

    public void addListing(Listing newListing)
    {
        myListings.add(newListing);
    }

    public void removeListing(int listingID)
    {
        for(Listing l : myListings)
        {
            if(l.getListingIDnumber() == listingID)
            {
                myListings.remove(l);
                break;
            }
        }
    }
}