package Controller;

import Entity.Listing;
import Systems.DatabaseSystem;

import java.util.ArrayList;

public class LandlordController {
    private DatabaseSystem db;
    private ListingController listingController;
    public LandlordController(DatabaseSystem db) {
        this.db = db;
    }

    public boolean updateListingState(int listingId, String newState) {
        if (newState.equals("Active")) {
            return false;   //  landlord cannot change state to active, only managers can
        }
        // TODO: there's a bug, the above fires even if previous state is say, rented
        //  landlords shouldn't be able to go suspended->active, but they should be able to go from rented to active...
        /*
        * How about we add a new state called registered
        And only the manager can change from registered to active
        But landlord can changed it into anything after it's been activated?
        * */
        return listingController.updateListingState(listingId, newState);
    }

    public void setListingController(ListingController listingController) {
        this.listingController = listingController;
    }

    public ArrayList<Listing> getListings(int landlordId) {
        return db.getLandlordListings(landlordId);
    }
}
