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