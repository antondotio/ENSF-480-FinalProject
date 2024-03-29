package Controller;

import java.util.ArrayList;
import Entity.Listing;
import Entity.SearchCriteria;
import Systems.DatabaseSystem;

public class RegisteredRenterController extends RenterController {
    public RegisteredRenterController(DatabaseSystem db) {
        super(db);
    }

    public boolean subscribe(int renterId, String type, Integer beds, Double baths, Boolean furnished,
            String quadrant) {
        SearchCriteria sc = new SearchCriteria(type, beds, baths, furnished, quadrant, renterId);
        return db.insertSearchCriteria(sc);
    }

    public boolean unsubscribe(int searchID) {
        return db.deleteSearchCriteria(searchID);

    }

    public ArrayList<SearchCriteria> getSearchCriteria(int renterId) {
        return db.getSearchCriteria(renterId);
    }
}