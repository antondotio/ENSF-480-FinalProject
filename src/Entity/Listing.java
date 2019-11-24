package Entity;

import java.time.LocalDate;

public class Listing {
	int landlordAccountId;
    Property property;
	LocalDate listingStart;
	LocalDate listingEnd;
    double paymentFee;
    String status;
	int listingIDnumber;
	LocalDate listingAddedDate;
	boolean feePaid;

	//	constructor with all the data the RENTER needs
    public Listing(int landlordAccountId, Property property, double paymentFee, String status, int listingIDnumber) {
		this.landlordAccountId = landlordAccountId;
        this.property = property;
        this.paymentFee = paymentFee;
        this.status = status;
        this.listingIDnumber = listingIDnumber;
    }

    //	constructor with all the data the MANAGER needs
	public Listing(int landlordAccountId, Property property, LocalDate listingStart, LocalDate listingEnd, double paymentFee,
				   String status, int listingIDnumber, LocalDate listingAddedDate, boolean feePaid) {
		this.landlordAccountId = landlordAccountId;
		this.property = property;
		this.listingStart = listingStart;
		this.listingEnd = listingEnd;
		this.paymentFee = paymentFee;
		this.status = status;
		this.listingIDnumber = listingIDnumber;
		this.listingAddedDate = listingAddedDate;
		this.feePaid = feePaid;
	}

	public Property getProperty() {
		return property;
	}

	public LocalDate getListingStart() {
		return listingStart;
	}

	public void setListingStart(LocalDate listingStart) {
		this.listingStart = listingStart;
	}

	public LocalDate getListingEnd() {
		return listingEnd;
	}

	public void setListingEnd(LocalDate listingEnd) {
		this.listingEnd = listingEnd;
	}

	public double getPaymentFee() {
		return paymentFee;
	}

	public void setPaymentFee(double paymentFee) {
		this.paymentFee = paymentFee;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getListingIDnumber() {
		return listingIDnumber;
	}

	public void setListingIDnumber(int listingIDnumber) {
		this.listingIDnumber = listingIDnumber;
	}

	public int getLandlordAccountId() {
		return landlordAccountId;
	}

	public void setLandlordAccountId(int landlordAccountId) {
		this.landlordAccountId = landlordAccountId;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public LocalDate getListingAddedDate() {
		return listingAddedDate;
	}

	public void setListingAddedDate(LocalDate listingAddedDate) {
		this.listingAddedDate = listingAddedDate;
	}

	public boolean isFeePaid() {
		return feePaid;
	}
}