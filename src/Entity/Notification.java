package Entity;

public class Notification {
    Listing listing;
    RegisteredRenterAccount renter;

    public Notification(RegisteredRenterAccount renter, Date date) {
        this.renter = renter;
        this.date = date;
    }

    public Listing getListing() {
        return listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}