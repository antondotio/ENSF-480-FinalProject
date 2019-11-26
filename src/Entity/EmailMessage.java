package Entity;

import java.time.LocalDate;

public class EmailMessage {
    LocalDate date;
    String message;
    String sender;
    int landlordId;

    public EmailMessage(LocalDate date, String message, String sender, int landlordId) {
        this.date = date;
        this.message = message;
        this.sender = sender;
        this.landlordId = landlordId;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }
    
    public String getSender() {
        return sender;
    }

    public int getReceiver() {
        return landlordId;
    }
}