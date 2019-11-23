package Controller;

import Systems.DatabaseSystem;

public class ManagerController {
    private DatabaseSystem db;
    private ListingController listingController;

    public ManagerController(DatabaseSystem db) {
        this.db = db;
    }

    public boolean updateListingState(int listingId, String newState) {
        return listingController.updateListingState(listingId, newState);
    }

    public void setListingController(ListingController listingController) {
        this.listingController = listingController;
    }
}
