package Controller;

import Systems.DatabaseSystem;

//  handles backend behaviour regarding changing/updating/creating listings
public class ListingController {
    private DatabaseSystem db;

    public ListingController(DatabaseSystem db) {
        this.db = db;
    }

    public boolean updateListingState(int listingId, String newState) {
        return db.updateListingState(listingId, newState);
    }

    public boolean updatePaid(int listingId, boolean newState) {
        return db.updatePaid(listingId, newState);
    }

    public boolean updateListingFees(int listingId, int newFee, int newFeePeriodInDays) {
        return db.updateListingFees(listingId, newFee, newFeePeriodInDays);
    }
}