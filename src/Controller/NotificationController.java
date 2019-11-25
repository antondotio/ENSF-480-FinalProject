package Controller;

import Entity.Listing;
import Systems.DatabaseSystem;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class NotificationController {
    private DatabaseSystem db;

    public NotificationController(DatabaseSystem db) {
        this.db = db;
    }

    public void handleNewListing(Listing listing) {
        db.notifyUsers(listing);
    }

    public ArrayList<Listing> getNotifications(int renterId) {
       return db.getNotifications(renterId);
    }
}
