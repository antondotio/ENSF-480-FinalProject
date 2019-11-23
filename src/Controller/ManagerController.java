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
            //  TODO: investigate case of manager setting to active without wanting to change paid state
            return listingController.updatePaid(listingId, false);
        }
        return result;
    }

    public void setListingController(ListingController listingController) {
        this.listingController = listingController;
    }
}
