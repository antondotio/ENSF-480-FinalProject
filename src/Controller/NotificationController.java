package Controller;

import Entity.Listing;
import Systems.DatabaseSystem;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class NotificationController implements Observer {
    private DatabaseSystem db;

    public NotificationController(DatabaseSystem db, Subject s) {
        this.db = db;
        s.register(this);
    }

    public ArrayList<Listing> getNotifications(int renterId) {
       return db.getNotifications(renterId);
    }

    public void update(Listing listing) {
        db.notifyUsers(listing);
    }
}
