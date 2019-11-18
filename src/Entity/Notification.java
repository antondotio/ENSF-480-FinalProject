package Entity;

public class Notification {
    Listing listing;
    RegisteredRenterAccount renter;

    public Notification(RegisteredRenterAccount renter, Listing listing) {
        this.renter = renter;
        this.listing = listing;
    }

    public Listing getListing() {
        return listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }

    public RegisteredRenterAccount getRenter() {
        return renter;
    }

    public void setRenter(RegisteredRenterAccount renter) {
        this.renter = renter;
    }
}