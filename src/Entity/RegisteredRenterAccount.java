package Entity;

import java.util.ArrayList;

public class RegisteredRenterAccount extends Account {

    ArrayList<Notification> notification;
    ArrayList<SearchCriteria> searchCriteria;

    public RegisteredRenterAccount(Name name, int accountID, String email) {
        super(name, accountID, email);
        notification = new ArrayList<Notification>();
        //  todo: fetch notifications
        searchCriteria = new ArrayList<SearchCriteria>();
    }
}