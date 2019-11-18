package Entity;

public class Listing {
    Property property;
    Date listingStart;
    Date listingEnd;
    double paymentFee;
    String status;
    String description;
    int listingIDnumber;

    public Listing(Property property, Date listingStart, Date listingEnd, double paymentFee, String status,
            String description, int listingIDnumber) {
        this.property = property;
        this.listingStart = listingStart;
        this.listingEnd = listingEnd;
        this.paymentFee = paymentFee;
        this.status = status;
        this.description = description;
        this.listingIDnumber = listingIDnumber;
    }

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getListingIDnumber() {
		return listingIDnumber;
	}

	public void setListingIDnumber(int listingIDnumber) {
		this.listingIDnumber = listingIDnumber;
	}
}