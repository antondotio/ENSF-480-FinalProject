package Controller;

import Entity.Listing;
import Entity.SearchCriteria;
import Systems.DatabaseSystem;
import Systems.LandlordEmailSystem;

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

    public void sendEmail()
}
