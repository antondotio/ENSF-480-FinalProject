package Controller;

import Entity.Account;
import Entity.RentalAction;
import Entity.Listing;
import Systems.DatabaseSystem;

import java.time.LocalDate;
import java.util.ArrayList;

public class ManagerController {
    private DatabaseSystem db;
    private ListingController listingController;

    public ManagerController(DatabaseSystem db) {
        this.db = db;
    }

    public boolean updateListingState(int listingId, String oldState, String newState) {
        return listingController.updateListingState(listingId, oldState, newState);
    }

    public String[] getSummary(LocalDate startDate, LocalDate endDate) {
        ArrayList<String> result = new ArrayList<>();
        int numHousesListedInPeriod = db.countHousesListedBetween(startDate, endDate);
        result.add("Total number of houses listed between " + printPeriodString(startDate, endDate) + numHousesListedInPeriod);

        ArrayList<RentalAction> housesRentedInPeriod = db.getHousesRentedBetween(startDate, endDate);
        result.add("Total number of houses rented between " + printPeriodString(startDate, endDate) + housesRentedInPeriod.size());
        result.add("Houses rented between: " + printPeriodString(startDate, endDate));
        for (RentalAction r : housesRentedInPeriod) {
            result.add(r.toString());
        }

        int numActiveListingsInPeriod = db.countActiveListingsBetween(startDate, endDate);
        result.add("Total number of active listings between " + printPeriodString(startDate, endDate) + numActiveListingsInPeriod);

        String[] resultArray = new String[result.size()];
        for (int i = 0; i < resultArray.length; i++) {
            resultArray[i] = result.get(i);
        }
        return resultArray;
    }

    public String printPeriodString(LocalDate startDate, LocalDate endDate) {
        return startDate.toString() + " and " + endDate.toString() + ": ";
    }

    public void setListingController(ListingController listingController) {
        this.listingController = listingController;
    }

    public ArrayList<Listing> getListings() {
        return db.getAllListings();
    }

    public ArrayList<Account> getUsersOfType(String userType) {
        return db.getUsersOfType(userType);
    }
}
