package Entity;

import java.time.LocalDate;

public class Payment {
    LocalDate date;
    double amount;
    int listingId;
    int landlordId;

    public Payment(LocalDate date, double amount, int listingId, int landlordId) {
        this.date = date;
        this.amount = amount;
        this.listingId = listingId;
        this.landlordId = landlordId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getListingId() {
        return listingId;
    }

    public void setListingId(int listingId) {
        this.listingId = listingId;
    }

    public int getLandlordId() {
        return landlordId;
    }

    public void setLandlordId(int landlordId) {
        this.landlordId = landlordId;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "date=" + date +
                ", amount=" + amount +
                ", listingId=" + listingId +
                ", landlordId=" + landlordId +
                '}';
    }
}