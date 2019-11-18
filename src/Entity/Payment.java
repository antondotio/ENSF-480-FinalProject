package Entity;

public class Payment {
    Date date;
    double amount;
    int paymentID;

    public Payment(Date date, double amount, int paymentID) {
        this.date = date;
        this.amount = amount;
        this.paymentID = paymentID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    @Override
    public String toString() {
        return "Payment [amount=" + amount + ", date=" + date + ", paymentID=" + paymentID + "]";
    }
}