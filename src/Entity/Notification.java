package Entity;

public class Notification {
    Listing listing;
    Date date;

    public Notification(Listing listing, Date date) {
        this.listing = listing;
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