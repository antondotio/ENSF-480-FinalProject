package Entity;

public class EmailMessage {
    Date date;
    String message;
    String sender;
    String receiver;

    public EmailMessage(Date date, String message, String sender, String receiver) {
        this.date = date;
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}