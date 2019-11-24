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
        return listingController.updateListingState(listingId, newState);
    }

    public void setListingController(ListingController listingController) {
        this.listingController = listingController;
    }

    public ArrayList<Listing> getListings(int landlordId) {
        return db.getLandlordListings(landlordId);
    }
}
