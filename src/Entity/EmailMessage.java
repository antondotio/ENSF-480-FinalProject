package Entity;

import java.time.LocalDate;

public class EmailMessage {
    LocalDate date;
    String message;
    String sender;
    String receiver;

    public EmailMessage(LocalDate date, String message, String sender, String receiver) {
        this.date = date;
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
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

    public String getReceiver() {
        return receiver;
    }
}