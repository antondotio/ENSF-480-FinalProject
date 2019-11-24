package Controller;

import Entity.Address;
import Entity.Property;
import Systems.DatabaseSystem;
import Entity.Listing;
import Entity.Payment;
import Entity.Constants;

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
        //  TODO: get listing back and send to notification controller
//        Listing activatedListing = db.activateListing(listingId);
//        //  send listing to notification controller now
//        return activatedListing != null;    //  if non-null, then success
    }

    public boolean postListing(int accountId, String type, int bedrooms, double baths, boolean furnished,
                               String quad, String street, String city, String country, String postalCode) {
        Listing listing = new Listing(accountId, new Property(type, bedrooms, baths, furnished, quad,
                new Address(street, city, country, postalCode), -1), Constants.DEFAULT_FEE, Constants.DEFAULT_STATUS, -1);
        listing.setFeePeriod(Constants.DEFAULT_FEEPERIOD);
        return db.postListing(listing);
    }

    public Payment pay(int listingId) {
        //  mark as paid
        return db.pay(listingId);
    }
}