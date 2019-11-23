package Controller;

import java.util.ArrayList;
import Entity.Listing;
import Entity.SearchCriteria;
import Systems.DatabaseSystem;

public class RegisteredRenterController extends RenterController {
    public RegisteredRenterController(DatabaseSystem db) {
        super(db);
    }

    public ArrayList<Listing> getListings(int renterId, String type, Integer beds,
                                          Double baths, Boolean furnished, String quadrant) {
        SearchCriteria sc = new SearchCriteria(type, beds, baths, furnished, quadrant, renterId);
        db.insertSearchCriteria(sc);
        return super.getListings(type, beds, baths, furnished, quadrant);
    }
}
