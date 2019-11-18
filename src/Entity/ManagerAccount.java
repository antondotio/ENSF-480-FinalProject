package Entity;

import java.util.ArrayList;

public class ManagerAccount extends Account {

    ArrayList<LandlordAccount> landlords;
    ArrayList<RegisteredRenterAccount> renters;

    public ManagerAccount(Name name, int accountID, String email) {
        super(name, accountID, email);
        landlords = new ArrayList<LandlordAccount>();
        renters = new ArrayList<RegisteredRenterAccount>();
    }
}