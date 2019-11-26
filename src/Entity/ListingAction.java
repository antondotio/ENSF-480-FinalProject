package Entity;

import java.time.LocalDate;

public class ListingAction {
    private int listingId;
    private LocalDate startDate;
    private LocalDate endDate;

    public ListingAction(int listingId, LocalDate startDate, LocalDate endDate) {
        this.listingId = listingId;
        this.startDate = startDate;
        this.endDate = endDate;
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
