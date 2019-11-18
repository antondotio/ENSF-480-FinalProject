package Entity;

import java.util.ArrayList;

public class Summary {
    Date startDate;
    Date endDate;
    int totalListed;
    int totalRented;
    int totalActive;
    ArrayList<Name> landlordNames;
    ArrayList<Integer> propertyID;
    ArrayList<Address> propertyAddresses;

    public Summary(Date startDate, Date endDate, int totalListed, int totalRented, int totalActive) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalListed = totalListed;
        this.totalRented = totalRented;
        this.totalActive = totalActive;
        landlordNames = new ArrayList<Name>();
        propertyID = new ArrayList<Integer>();
        propertyAddresses = new ArrayList<Address>();
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getTotalListed() {
        return totalListed;
    }

    public void setTotalListed(int totalListed) {
        this.totalListed = totalListed;
    }

    public int getTotalRented() {
        return totalRented;
    }

    public void setTotalRented(int totalRented) {
        this.totalRented = totalRented;
    }

    public int getTotalActive() {
        return totalActive;
    }

    public void setTotalActive(int totalActive) {
        this.totalActive = totalActive;
    }

    public ArrayList<Name> getLandlordNames() {
        return landlordNames;
    }

    public ArrayList<Integer> getPropertyID() {
        return propertyID;
    }

    public ArrayList<Address> getPropertyAddresses() {
        return propertyAddresses;
    }

    //  TODO: Adding listing info to summay, maybe add ref to listings landlord to make adding easier
}