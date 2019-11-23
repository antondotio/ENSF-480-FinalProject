package Entity;

public class Listing {
	int landlordAccountId;
    Property property;
    Date listingStart;
    Date listingEnd;
    double paymentFee;
    String status;
	int listingIDnumber;

    public Listing(int landlordAccountId, Property property, double paymentFee) {
		this.landlordAccountId = landlordAccountId;
        this.property = property;
        this.paymentFee = paymentFee;
        this.status = "suspended";
    }

	public Property getProperty() {
		return property;
	}

	public Date getListingStart() {
		return listingStart;
	}

	public void setListingStart(Date listingStart) {
		this.listingStart = listingStart;
	}

	public Date getListingEnd() {
		return listingEnd;
	}

	public void setListingEnd(Date listingEnd) {
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
}