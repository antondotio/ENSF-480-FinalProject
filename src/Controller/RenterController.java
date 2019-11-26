package Controller;

import Entity.EmailMessage;
import Entity.Listing;
import Entity.SearchCriteria;
import Systems.DatabaseSystem;
import Systems.LandlordEmailSystem;

import java.time.LocalDate;
import java.util.ArrayList;

public class RenterController {
    protected DatabaseSystem db;
    private LandlordEmailSystem landlordEmailSystem;
    public RenterController(DatabaseSystem db) {
        this.db = db;
    }

    public ArrayList<Listing> getListings(String type, Integer beds,
                                          Double baths, Boolean furnished, String quadrant) {
        SearchCriteria sc = new SearchCriteria(type, beds, baths, furnished, quadrant, -1);
        return db.getListings(sc);
    }

    public void setLandlordEmailSystem(LandlordEmailSystem landlordEmailSystem) {
        this.landlordEmailSystem = landlordEmailSystem;
    }

    public boolean sendEmail(String renterEmail, int listingId, String message) {
        Integer landlordId = db.getLandlordId(listingId);
        if (landlordId == null) {
            return false;
        }
        landlordEmailSystem.sendEmail(new EmailMessage(LocalDate.now(), message, renterEmail, landlordId));
        return true;
    }
}
