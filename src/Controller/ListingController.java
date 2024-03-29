package Controller;

import Entity.Address;
import Entity.Property;
import Systems.DatabaseSystem;
import Entity.Listing;
import Entity.Payment;
import Entity.Constants;

import java.util.ArrayList;

//  handles backend behaviour regarding changing/updating/creating listings
public class ListingController implements Subject {
    private DatabaseSystem db;
    private ArrayList<Observer> observers;

    public ListingController(DatabaseSystem db) {
        this.db = db;
        observers = new ArrayList<>();
    }

    public boolean updateListingState(int listingId, String oldState, String newState) {
        if (oldState == null || newState.equals(oldState)) {
            return false;   //  can't change to old state
        }

        boolean result = true;
        //  end rental if needed
        if (oldState.equals("Rented")) {
            result = db.endRental(listingId);
        }

        result =  db.updateListingState(listingId, newState) && result; // update listing state

        //  handle other things we need to track based on state
        if (newState.equals("Rented") && result) {
            return db.startRental(listingId);
        }
        if (newState.equals("Active")) {
            return activateListing(listingId);
        }
        return result;
    }

    public String checkListingState(int listingId) {
        return db.checkListingState(listingId);
    }

    public boolean updateListingFees(int listingId, int newFee, int newFeePeriodInDays) {
        return db.updateListingFees(listingId, newFee, newFeePeriodInDays);
    }

    public boolean activateListing(int listingId) {
        Listing activatedListing = db.activateListing(listingId);
        //  send listing to notification observer
        notifyAllObservers(activatedListing);

        return activatedListing != null;    //  if non-null, then success
    }

    public boolean postListing(int accountId, String type, int bedrooms, double baths, boolean furnished,
                               String quad, String street, String city, String country, String postalCode) {
        Listing listing = new Listing(accountId, new Property(type, bedrooms, baths, furnished, quad,
                new Address(street, city, country, postalCode), -1), Constants.DEFAULT_FEE, Constants.DEFAULT_STATUS, -1);
        listing.setFeePeriod(Constants.DEFAULT_FEEPERIOD);
        return db.postListing(listing);
    }

    public void register(Observer o) {
        observers.add(o);
    }

    public void unregister(Observer o) {
        observers.remove(o);
    }

    public void notifyAllObservers(Listing l) {
        for (Observer o : observers) {
            o.update(l);
        }
    }

    public Payment pay(int listingId) {
        //  mark as paid
        return db.pay(listingId);
    }
}