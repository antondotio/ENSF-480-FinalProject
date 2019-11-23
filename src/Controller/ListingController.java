package Controller;

import Systems.DatabaseSystem;
import Entity.Listing;
import Entity.Payment;

//  handles backend behaviour regarding changing/updating/creating listings
public class ListingController {
    private DatabaseSystem db;

    public ListingController(DatabaseSystem db) {
        this.db = db;
    }

    public boolean updateListingState(int listingId, String newState) {
        return db.updateListingState(listingId, newState);
    }

    public boolean updateListingFees(int listingId, int newFee, int newFeePeriodInDays) {
        return db.updateListingFees(listingId, newFee, newFeePeriodInDays);
    }

    public boolean activateListing(int listingId) {
        return db.activateListing(listingId);
    }

    public Payment pay(int listingId) {
        //  mark as paid
        return db.pay(listingId);
    }
}