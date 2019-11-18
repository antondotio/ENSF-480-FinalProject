package Entity;

import java.util.ArrayList;

public class RegisteredRenterAccount extends Account {

    ArrayList<Notification> notification;
    ArrayList<SearchCriteria> searchCriteria;

    public RegisteredRenterAccount(Name name, String password, int accountID, String email) {
        super(name, password, accountID, email);
        notification = new ArrayList<Notification>();
        searchCriteria = new ArrayList<SearchCriteria>();
    }
}