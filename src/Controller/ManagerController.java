package Controller;

import Systems.DatabaseSystem;

public class ManagerController {
    private DatabaseSystem db;
    private ListingController listingController;

    public ManagerController(DatabaseSystem db) {
        this.db = db;
    }

    public boolean updateListingState(int listingId, String newState) {
        boolean result = listingController.updateListingState(listingId, newState);
        if (newState.equals("Active") && result) {
            //  set paid to false, because manager is now activating listing
        }
    }

    public void setListingController(ListingController listingController) {
        this.listingController = listingController;
    }
}
