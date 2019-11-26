package Entity;

import java.time.LocalDate;

public class RentalAction {
    private int listingId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Name landlordName;
    private Address address;

    public RentalAction(int listingId, Name landlordName, Address address, LocalDate startDate, LocalDate endDate) {
        this.listingId = listingId;
        this.landlordName = landlordName;
        this.address = address;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Landlord Name: " + landlordName.toString() + "\t" + "Listing ID Number: " + listingId + "\t" + "Address: " + address.toString();
    }

    public Name getLandlordName() {
        return landlordName;
    }

    public void setLandlordName(Name landlordName) {
        this.landlordName = landlordName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getListingId() {
        return listingId;
    }

    public void setListingId(int listingId) {
        this.listingId = listingId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
