package Controller;

import Entity.Listing;
import Systems.DatabaseSystem;

public class NotificationController {
    private DatabaseSystem db;

    public NotificationController(DatabaseSystem db) {
        this.db = db;
    }

    public void handleNewListing(Listing listing) {
        db.notifyUsers(listing);
    }
}
